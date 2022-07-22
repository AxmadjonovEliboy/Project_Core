package uz.axmadjonov.coreproject.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.axmadjonov.coreproject.dto.base.BaseDto;
import uz.axmadjonov.coreproject.entity.address.Address;

/**
 * @author Axmadjonov Eliboy on Tue. 15:56. 05/07/22
 * @project CoreProject
 */
@Getter
@Setter
@Builder
public class AuthCreateDto implements BaseDto {

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private String gender;
    private Address address;
}
