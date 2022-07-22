package uz.axmadjonov.coreproject.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * @author Axmadjonov Eliboy on Tue. 16:02. 05/07/22
 * @project CoreProject
 */
@Getter
@AllArgsConstructor
public abstract class GenericDto implements BaseDto {

    @NotBlank
    private Long di;
}
