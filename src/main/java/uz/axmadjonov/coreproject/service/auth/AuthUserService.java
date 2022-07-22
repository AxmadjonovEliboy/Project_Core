package uz.axmadjonov.coreproject.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.axmadjonov.coreproject.criteria.AuthCriteria;
import uz.axmadjonov.coreproject.dto.address.AddressDto;
import uz.axmadjonov.coreproject.dto.auth.*;
import uz.axmadjonov.coreproject.entity.address.Address;
import uz.axmadjonov.coreproject.entity.auth.AuthUser;
import uz.axmadjonov.coreproject.enums.Gender;
import uz.axmadjonov.coreproject.enums.Region;
import uz.axmadjonov.coreproject.enums.Role;
import uz.axmadjonov.coreproject.mapper.AuthMapper;
import uz.axmadjonov.coreproject.repository.address.AddressRepository;
import uz.axmadjonov.coreproject.repository.auth.AuthRepository;
import uz.axmadjonov.coreproject.response.AppErrorDto;
import uz.axmadjonov.coreproject.response.DataDto;
import uz.axmadjonov.coreproject.service.base.AbstractService;
import uz.axmadjonov.coreproject.service.base.BaseService;
import uz.axmadjonov.coreproject.service.base.GenericCrudService;
import uz.axmadjonov.coreproject.utils.JwtUtils;
import uz.axmadjonov.coreproject.utils.ServerProperties;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Axmadjonov Eliboy on Tue. 15:38. 05/07/22
 */
@Slf4j
@Service
public class AuthUserService extends AbstractService<AuthRepository, AuthMapper> implements GenericCrudService<AuthUser, AuthDto, AuthUpdateDto, AuthCreateDto, Long, AuthCriteria>, BaseService, UserDetailsService {
    private final ObjectMapper objectMapper;
    private final ServerProperties serverProperties;
    private final PasswordEncoder encoder;
    private final AddressRepository addressRepository;

    public AuthUserService(AuthRepository repository,
                           AuthMapper mapper,
                           ObjectMapper objectMapper,
                           ServerProperties serverProperties, PasswordEncoder encoder, AddressRepository addressRepository) {
        super(repository, mapper);
        this.objectMapper = objectMapper;
        this.serverProperties = serverProperties;
        this.encoder = encoder;
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<DataDto<SessionDto>> login(AuthLoginDto dto) {
        try {

            HttpClient httpclient = HttpClientBuilder.create().build();

            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("data")) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return ResponseEntity.ok(new DataDto<>(sessionDto));
            }
            return ResponseEntity.ok(
                    new DataDto<>(new AppErrorDto(
                            "bad Request 1",
                            "/auth/login",
                            HttpStatus.BAD_REQUEST)));

        } catch (IOException e) {

            return ResponseEntity.ok(new DataDto<>(new AppErrorDto("bad request 2", "", HttpStatus.INTERNAL_SERVER_ERROR)));
        }

    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                DecodedJWT decodedJWT = JwtUtils.verifier().verify(refresh_token);
                String username = decodedJWT.getSubject();
                AuthUser authUser = this.getUserByUsername(username);
                UserDetails user = User.builder().
                        username(authUser.getPhoneNumber())
                        .password(authUser.getPassword())
                        .authorities(new SimpleGrantedAuthority(authUser.getRole().name()))
                        .build();
                Date expiryForAccessToken = JwtUtils.getExpireDate();

                Date expiryForRefreshToken = JwtUtils.getExpireDateForRefreshToken();
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(expiryForAccessToken)
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(JwtUtils.getAlgorithm());
                SessionDto sessionDto = SessionDto.builder()
                        .accessToken(accessToken)
                        .expiresIn(expiryForAccessToken.getTime())
                        .refreshToken(refresh_token)
                        .refreshTokenExpire(expiryForRefreshToken.getTime())
                        .tokenType("JWT")
                        .issuedAt(System.currentTimeMillis())
                        .build();
                return ResponseEntity.ok(new DataDto<>(sessionDto));
            } catch (Exception exception) {
                log.info("error : {}", exception.getMessage());
                return ResponseEntity.ok(new DataDto<>(new AppErrorDto("Bed request ", "api/v1/refresh/token", HttpStatus.BAD_REQUEST)));
            }
        } else {
            throw new RuntimeException("Bed request");
        }
    }

    public ResponseEntity<DataDto<SessionDto>> register(AuthRegisterDto dto) {

        Optional<AuthUser> phoneNumberDeletedFalse = repository.findPhoneNumberOrEmailDeletedFalse(dto.getPhoneNumber(), dto.getEmail());
        if (phoneNumberDeletedFalse.isPresent()) {
            return ResponseEntity.ok(new DataDto<>(new AppErrorDto("User already exist", "/auth/register", HttpStatus.ALREADY_REPORTED)));
        }

        Region region = Region.getByName(dto.getAddressCreateDto().getRegion());
        if (Objects.isNull(region))
            return ResponseEntity.ok(new DataDto<>(new AppErrorDto("Bad request", "/auth/register", HttpStatus.INTERNAL_SERVER_ERROR)));
        Address address = Address.builder()
                .region(region)
                .description(dto.getAddressCreateDto().getDescription())
                .build();

        Address savedAddress = addressRepository.save(address);

        AuthUser authUser = AuthUser.builder()
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .gender(Gender.getByName(dto.getGender()))
                .address(savedAddress)
                .role(Role.USER)
                .status(true)
                .build();
        AuthUser savedUser = repository.save(authUser);
        AuthLoginDto authLoginDto = AuthLoginDto.builder()
                .phoneNumber(savedUser.getPhoneNumber())
                .password(dto.getPassword())
                .build();
        return this.login(authLoginDto);
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(AuthCreateDto dto) {

        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(AuthUpdateDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<Long>> delete(Long dto) {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<AuthDto>> get(Long id) {
        if (Objects.isNull(id))
            return ResponseEntity.ok(new DataDto<>(new AppErrorDto("Id is null", "/auth/get", HttpStatus.BAD_REQUEST)));
        Optional<AuthUser> authUser = repository.findByIdAndIsDeletedFalse(id);
        if (authUser.isEmpty())
            return ResponseEntity.ok(new DataDto<>(new AppErrorDto("Not found", "/auth/get", HttpStatus.NOT_FOUND)));
//        AuthDto authDto = mapper.toDto(authUser.get());
        AuthUser user = authUser.get();
        AddressDto addressDto = new AddressDto(
                user.getAddress().getId(),
                user.getAddress().getRegion().getName(),
                user.getAddress().getDescription());

        AuthDto authDto = new AuthDto(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole().getName(),
                user.getGender().getName(),
                addressDto);
        return ResponseEntity.ok(new DataDto<>(authDto));
    }

    @Override
    public ResponseEntity<DataDto<List<AuthDto>>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<DataDto<List<AuthDto>>> getAll(AuthCriteria criteria) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> user = repository.findPhoneNumberDeletedFalse(username);
        if (user.isPresent()) {
            AuthUser authUser = user.get();
            return User.builder().
                    username(authUser.getPhoneNumber())
                    .password(authUser.getPassword())
                    .authorities(new SimpleGrantedAuthority(authUser.getRole().name()))
                    .build();
        } else {
            throw new UsernameNotFoundException("Username not found : " + username);
        }
    }

    public AuthUser getUserByUsername(String username) {
        return repository.findPhoneNumberDeletedFalse(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
