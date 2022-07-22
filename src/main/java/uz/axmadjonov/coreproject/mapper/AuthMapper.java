package uz.axmadjonov.coreproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.axmadjonov.coreproject.dto.auth.AuthCreateDto;
import uz.axmadjonov.coreproject.dto.auth.AuthDto;
import uz.axmadjonov.coreproject.dto.auth.AuthUpdateDto;
import uz.axmadjonov.coreproject.entity.auth.AuthUser;

/**
 * @author Axmadjonov Eliboy on Tue. 16:21. 05/07/22
 * @project CoreProject
 */
@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthMapper extends GenericMapper<AuthUser, AuthDto, AuthUpdateDto, AuthCreateDto> {

    AuthUser formUpdate(AuthUpdateDto dto, @MappingTarget AuthUser authUser);
}
