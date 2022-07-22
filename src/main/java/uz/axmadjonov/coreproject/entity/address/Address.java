package uz.axmadjonov.coreproject.entity.address;

import lombok.*;
import uz.axmadjonov.coreproject.entity.base.Auditable;
import uz.axmadjonov.coreproject.enums.Region;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Axmadjonov Eliboy on Mon. 17:25. 04/07/22
 * @project CoreProject
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address extends Auditable {

    @Enumerated(EnumType.STRING)
    private Region region;

    private String description;

}
