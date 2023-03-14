package bg.softuni.blacklistinterceptor.service;

import org.springframework.stereotype.Service;

@Service
public class BlacklistService {

    public boolean isBlackListed(String ipAddress) {
        // TODO: Please be more realistic
        // e.g. create repository where the admin may manage blacklisted IP-s
        return true;
    }
}
