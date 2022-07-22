package uz.axmadjonov.coreproject.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.axmadjonov.coreproject.dto.address.AddressDto;
import uz.axmadjonov.coreproject.dto.base.GenericDto;

/**
 * @author Axmadjonov Eliboy on Tue. 15:56. 05/07/22
 * @project CoreProject
 */
@Getter
@Setter
public class AuthDto extends GenericDto {

    private String fullName;
    private String email;

    private String phoneNumber;
    private String role;
    private String gender;
    private AddressDto address;

    public AuthDto( Long di, String fullName, String email, String phoneNumber, String role, String gender, AddressDto address) {
        super(di);
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.gender = gender;
        this.address = address;
    }

}
