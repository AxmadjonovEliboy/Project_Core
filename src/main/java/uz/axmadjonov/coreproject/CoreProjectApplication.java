package uz.axmadjonov.coreproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.axmadjonov.coreproject.entity.auth.AuthUser;
import uz.axmadjonov.coreproject.enums.Gender;
import uz.axmadjonov.coreproject.enums.Role;
import uz.axmadjonov.coreproject.repository.auth.AuthRepository;
import uz.axmadjonov.coreproject.utils.OpenApiProperties;
import uz.axmadjonov.coreproject.utils.ServerProperties;

@OpenAPIDefinition
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties({
        ServerProperties.class,
        OpenApiProperties.class

})
public class CoreProjectApplication {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CoreProjectApplication.class, args);
    }

    /**
     * fullName
     * email
     * password
     * phoneNumber
     * role
     * pathImage
     * gender
     * address
     * status
     *
     * @throws Exception
     */

//    @Bean
    public void run() throws Exception {
        CommandLineRunner runner1 = (a) -> {
            AuthUser user = AuthUser.builder()
                    .fullName("Axmadjonov Eliboy")
                    .email("jarvisJon@gmail.com")
                    .password(passwordEncoder.encode("jarvis"))
                    .phoneNumber("+998997777777")
                    .role(Role.ADMIN)
                    .pathImage(null)
                    .gender(Gender.MALE)
                    .status(true)
                    .build();
            authRepository.save(user);
        };
        runner1.run("s", "b");
    }
}
