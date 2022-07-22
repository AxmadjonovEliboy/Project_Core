package uz.axmadjonov.coreproject.service.base;

import org.springframework.http.ResponseEntity;
import uz.axmadjonov.coreproject.criteria.BaseCriteria;
import uz.axmadjonov.coreproject.dto.auth.AuthDto;
import uz.axmadjonov.coreproject.dto.base.BaseDto;
import uz.axmadjonov.coreproject.response.DataDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Axmadjonov Eliboy on Tue. 16:01. 05/07/22
 * @project CoreProject
 */
public interface GenericService<
        D extends BaseDto,
        K extends Serializable,
        CR extends BaseCriteria
        > {


    ResponseEntity<DataDto<AuthDto>> get(K id);

    ResponseEntity<DataDto<List<AuthDto>>> getAll();

    ResponseEntity<DataDto<List<AuthDto>>> getAll(CR criteria);
}
