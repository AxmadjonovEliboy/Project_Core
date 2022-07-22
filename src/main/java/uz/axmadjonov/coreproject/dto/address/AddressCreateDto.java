package uz.axmadjonov.coreproject.dto.address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Axmadjonov Eliboy on Thu. 15:08. 21/07/22
 */
@Getter
@Setter
@Builder
@ToString
public class AddressCreateDto {

    private String region;
    private String description;

}
