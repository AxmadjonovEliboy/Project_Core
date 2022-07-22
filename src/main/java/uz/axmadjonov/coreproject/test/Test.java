package uz.axmadjonov.coreproject.test;


import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.axmadjonov.coreproject.dto.auth.AuthLoginDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Axmadjonov Eliboy on Mon. 14:27. 18/07/22
 */

@RestController
@RequestMapping
public class Test {

    /**
     * name request orqali qaysi ip manzildan murojat qilayotganini topish
     *
     * @param request
     * @return String
     */
//    public String getRequestIpAddress(HttpServletRequest request) {
//        String ipAddress = request.getHeader("X-Forwarded-For");
//        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
//            ipAddress = request.getHeader("WL-Proxy-Client-IP");
//
//        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress))
//            ipAddress = request.getHeader("Proxy-client-IP");
//
//        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
//            ipAddress = request.getRemoteAddr();
//            if ("local_host_Ip".equals(ipAddress) || ("local_host_1".equals(ipAddress))) {
//                try {
//                    InetAddress inetAddress = InetAddress.getLocalHost();
//                    ipAddress = inetAddress.getHostAddress();
//                } catch (UnknownHostException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        return ipAddress;
//    }

    /**
     * name httpServletRequest orqali User-agentni olish
     *
     * @param request
     * @return String
     */
    public String getRequestUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }


    /**
     * description swagger bilan to`liq configuration qilingan method
     *
     * @param httpServletRequest
     * @param loginDto
     * @return AuthLogin.class
     */
    @ApiOperation(value = "Authorization / Login", notes = "Sing in user a password")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Authorization / Login .", response = AuthLoginDto.class))
    @PostMapping(value = "PATH", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", value = "Authorization request  header ex. Bearer ****TOKEN**** ", required = true)
    public AuthLoginDto login(HttpServletRequest httpServletRequest,
                              @ApiParam(value = "Username and password to sign in <br/><b> username: Eliboy <br/>password : 123 <b/> ", required = true)
                              @Valid @RequestBody AuthLoginDto loginDto
    ) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
//        httpHeaders.set("fsdfads", "dflsdjf");

        return null;
    }

}