package uz.axmadjonov.coreproject.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import uz.axmadjonov.coreproject.entity.auth.AuthUser;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Axmadjonov Eliboy on Thu. 10:28. 21/07/22
 */
@UtilityClass
public class SecurityUtils {

    private final String LOCALHOST_IPV4 = "192.168.43.97";
    private final String LOCALHOST_IPV6 = "fe80::99e6:973:3298:d34e";



    public Optional<AuthUser> getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            return Optional.of(authUser);
        }
        return Optional.empty();
    }

    public String getRequestIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("WL-Proxy-Client-IP");

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("Proxy-client-IP");

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || (LOCALHOST_IPV6.equals(ipAddress))) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ipAddress;
    }


}
