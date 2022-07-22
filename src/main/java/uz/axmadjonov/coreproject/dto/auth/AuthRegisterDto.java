package uz.axmadjonov.coreproject.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.axmadjonov.coreproject.dto.address.AddressCreateDto;

import javax.validation.constraints.NotNull;

/**
 * @author Axmadjonov Eliboy on Thu. 12:04. 21/07/22
 */
@Getter
@Setter
@Builder
@ToString
public class AuthRegisterDto {
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String gender;
    @NotNull
    private AddressCreateDto addressCreateDto;
}
