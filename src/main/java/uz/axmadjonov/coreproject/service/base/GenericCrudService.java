package uz.axmadjonov.coreproject.service.base;

import org.springframework.http.ResponseEntity;
import uz.axmadjonov.coreproject.criteria.BaseCriteria;
import uz.axmadjonov.coreproject.dto.base.BaseDto;
import uz.axmadjonov.coreproject.dto.base.GenericDto;
import uz.axmadjonov.coreproject.entity.base.BaseEntity;
import uz.axmadjonov.coreproject.response.DataDto;

import java.io.Serializable;

/**
 * @author Axmadjonov Eliboy on Tue. 16:02. 05/07/22
 * @project CoreProject
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDto,
        UD extends GenericDto,
        CD extends BaseDto,
        K extends Serializable,
        CR extends BaseCriteria
        > extends GenericService<D, K, CR> {


    ResponseEntity<DataDto<K>> create(CD dto);

    ResponseEntity<DataDto<K>> update(UD dto);

    ResponseEntity<DataDto<K>> delete(K dto);
}
