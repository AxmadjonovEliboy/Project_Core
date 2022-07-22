package uz.axmadjonov.coreproject.dto.address;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.axmadjonov.coreproject.dto.base.GenericDto;

/**
 * @author Axmadjonov Eliboy on Fri. 10:26. 22/07/22
 */
@Getter
@Setter
public class AddressDto extends GenericDto {
    private String region;
    private String description;

    public AddressDto(Long id, String region, String description) {
        super(id);
        this.region = region;
        this.description = description;
    }
}
