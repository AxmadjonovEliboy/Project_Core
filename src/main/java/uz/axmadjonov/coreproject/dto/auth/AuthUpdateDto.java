package uz.axmadjonov.coreproject.dto.auth;

import uz.axmadjonov.coreproject.dto.base.GenericDto;

import javax.validation.constraints.NotBlank;

/**
 * @author Axmadjonov Eliboy on Tue. 15:56. 05/07/22
 * @project CoreProject
 */
public class AuthUpdateDto extends GenericDto {
    public AuthUpdateDto(@NotBlank Long di) {
        super(di);
    }
}
