package uz.axmadjonov.coreproject.dto.auth;

import lombok.*;

/**
 * @author Axmadjonov Eliboy on Tue. 16:24. 05/07/22
 * @project CoreProject
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginDto {
    public String phoneNumber;
    public String password;
}
