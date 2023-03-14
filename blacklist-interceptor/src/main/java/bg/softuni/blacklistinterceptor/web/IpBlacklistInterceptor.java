package bg.softuni.blacklistinterceptor.web;

import bg.softuni.blacklistinterceptor.service.BlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import java.util.Locale;
import java.util.Map;

@Component
public class IpBlacklistInterceptor implements HandlerInterceptor {

    private final BlacklistService service;

    private final ThymeleafViewResolver thymeleafViewResolver;

    public IpBlacklistInterceptor(BlacklistService service, ThymeleafViewResolver thymeleafViewResolver) {
        this.service = service;
        this.thymeleafViewResolver = thymeleafViewResolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = getIpAddressFromRequest(request);

        if (service.isBlackListed(ip)) {
            View blockedView = thymeleafViewResolver.resolveViewName("blocked", Locale.getDefault());
            if (blockedView != null) {
                blockedView.render(Map.of(), request, response);
            }
            return false;
        }

        return true;
    }

    public String getIpAddressFromRequest(HttpServletRequest request) {
        String ipAddress = null;

        String xffHeader = request.getHeader("X-Forwarded-For");

        if (xffHeader != null && !xffHeader.equals("unknown")) {
            int commaIdx = xffHeader.indexOf(",");
            if (commaIdx > 0) {
                ipAddress = xffHeader.substring(0, commaIdx - 1);
            }
        }

        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }
}
