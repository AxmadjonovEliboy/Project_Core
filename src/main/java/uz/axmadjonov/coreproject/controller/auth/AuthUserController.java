package uz.axmadjonov.coreproject.controller.auth;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.axmadjonov.coreproject.controller.base.AbstractController;
import uz.axmadjonov.coreproject.criteria.AuthCriteria;
import uz.axmadjonov.coreproject.dto.auth.*;
import uz.axmadjonov.coreproject.response.DataDto;
import uz.axmadjonov.coreproject.service.auth.AuthUserService;
import uz.axmadjonov.coreproject.utils.SecurityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Axmadjonov Eliboy on Mon. 23:15. 04/07/22
 * @project CoreProject
 */

@RestController
@RequestMapping
@Slf4j
public class AuthUserController extends AbstractController<AuthUserService> {

    public AuthUserController(AuthUserService service) {
        super(service);
    }


    @ApiOperation(value = "Authorization / Login", notes = "Sing in user a password")
    @ApiResponses(value = @ApiResponse(code = 200, message = "Authorization / Login .", response = DataDto.class))
    @PostMapping(value = PATH + "auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", value = "Authorization request  header ex. Bearer ****TOKEN**** ", required = true)
    public ResponseEntity<DataDto<SessionDto>> login(HttpServletRequest httpServletRequest,
                                                     @ApiParam(value = "Username and password to sign in <br/><b> username: Eliboy <br/>password : 123 <b/> ", required = true)
                                                     @RequestBody AuthLoginDto dto) {
        String requestIpAddress = SecurityUtils.getRequestIpAddress(httpServletRequest);
        log.info("Authorization login phoneNumber : {}  , and request's ipAddress : {} ", dto.getPhoneNumber(), requestIpAddress);
        return service.login(dto);
    }

    @GetMapping(value = PATH + "refresh/token")
    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request) throws IOException {
        return service.refreshToken(request);

    }

    @PostMapping(value = PATH + "auth/create")
    public ResponseEntity<DataDto<Long>> create(HttpServletRequest httpServletRequest,
                                                @RequestBody AuthCreateDto dto) {
        log.info("AuthUserCreateDto :  {}  Request Ip Address : {} ", dto, SecurityUtils.getRequestIpAddress(httpServletRequest));
        return service.create(dto);
    }

    @PostMapping(value = PATH + "auth/register")
    public ResponseEntity<DataDto<SessionDto>> register(HttpServletRequest httpServletRequest,
                                                        @RequestBody AuthRegisterDto dto) {
        log.info("AuthUser RegisterDto :  {}  Request Ip Address : {} ", dto, SecurityUtils.getRequestIpAddress(httpServletRequest));
        return service.register(dto);
    }

    @GetMapping(value = PATH + "auth/get/{id}")
    public ResponseEntity<DataDto<AuthDto>> get(HttpServletRequest httpServletRequest,
                                                @PathVariable(name = "id") Long id) {
        log.info("User id : {} and Request Ip Address : {} ", id, SecurityUtils.getRequestIpAddress(httpServletRequest));
        return service.get(id);
    }

    @GetMapping(value = PATH + "auth/getAll")
    public ResponseEntity<DataDto<List<AuthDto>>> getAll() {
        return service.getAll();
    }

    @PostMapping(value = PATH + "auth/getByCriteria")
    public ResponseEntity<DataDto<List<AuthDto>>> getAll(AuthCriteria dto) {
        return service.getAll(dto);
    }


}
