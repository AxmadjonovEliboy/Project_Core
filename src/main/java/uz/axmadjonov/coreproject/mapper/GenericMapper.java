package uz.axmadjonov.coreproject.mapper;

import uz.axmadjonov.coreproject.dto.base.BaseDto;
import uz.axmadjonov.coreproject.entity.base.BaseEntity;

import java.util.List;

/**
 * @author Axmadjonov Eliboy on Thu. 14:32. 21/07/22
 */
public interface GenericMapper<
        E extends BaseEntity,
        D extends BaseDto,
        UD extends BaseDto,
        CD extends BaseDto> extends BaseMapper {

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD dto);

    E fromUpdateDto(UD dto);

}
