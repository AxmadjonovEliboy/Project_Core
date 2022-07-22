package uz.axmadjonov.coreproject.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Axmadjonov Eliboy on Tue. 15:53. 05/07/22
 * @project CoreProject
 */
@Getter
@Setter
@Builder
public class SessionDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long refreshTokenExpire;
    private Long issuedAt;
    private Long expiresIn;
}
