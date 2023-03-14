package bg.softuni.blacklistinterceptor.config;

import bg.softuni.blacklistinterceptor.web.IpBlacklistInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    private final IpBlacklistInterceptor ipBlacklistInterceptor;

    public ApplicationConfiguration(IpBlacklistInterceptor ipBlacklistInterceptor) {
        this.ipBlacklistInterceptor = ipBlacklistInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlacklistInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
