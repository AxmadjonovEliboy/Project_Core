<h1 style="color: indigo">Log configuration</h1>
<h5>
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
<h4 style="color: orangered">logback.xml</h4>
    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>%-40(%d %p) %-100.100([%t] %C): %msg%n%throwable</Pattern>
        </layout>
    </appender>

    <appender name="File" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/app-logback.log</file>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <Pattern>%-40(%d %p) %-100.100([%t] %C): %msg%n%throwable</Pattern>
        </encoder>

        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/archived/app-%d{yyyy-MM-dd}.%i-logback.log.zip</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>1MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="File" />
        <appender-ref ref="Console" />+
    </root>

    <logger name="uz.axmadjonov.coreproject" level="debug" additivity="false">
        <appender-ref ref="File" />
        <appender-ref ref="Console" />
    </logger>

</configuration>
</h5>

<h1 style="color: lawngreen">Spring Security Configuration</h1>
<h5 style="color: darkmagenta">
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST = {
            "/api/v1/refresh/token",
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/swagger-ui/**",
            "/api/docs/**",
    };

    private final PasswordEncoder passwordEncoder;
    private final AuthUserService authUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest().authenticated();

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
</h5>


<h1>Cors Configuration</h1>
<p>
@Configuration
    public class CorsConfig {
        @Bean
        public FilterRegistrationBean customCorsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOriginPattern("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", config);
            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
            bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return bean;
        }
    }

</p>

# Captcha configuration v1
<p>
<dependency>
    <groupId>com.github.cage</groupId>
    <artifactId>cage</artifactId>
    <version>1.0</version>
</dependency>
</p>

<h5>
public class Captcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unique_id")
    private String uniqueId;
    private String token;
}
</h5>

<h4>
  @GetMapping(value = "/get")
    public void get(@RequestParam("uniqueId") String uniqueId, HttpServletResponse resp) throws IOException {

        GCage gCage = new GCage();
        resp.setContentType("image/" + gCage.getFormat());
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Progma", "no-cache");
        resp.setDateHeader("Max-Age", 0);

        String token = gCage.getTokenGenerator().next().substring(0, 7);
        Captcha captcha = repository.getByUniqueId(uniqueId).orElse(new Captcha());
        captcha.setUniqueId(uniqueId);
        captcha.setToken(token);
        repository.save(captcha);
        gCage.draw(token, resp.getOutputStream());

    }

</h4>




