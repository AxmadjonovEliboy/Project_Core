package uz.axmadjonov.coreproject.service.base;

import lombok.RequiredArgsConstructor;
import uz.axmadjonov.coreproject.mapper.BaseMapper;
import uz.axmadjonov.coreproject.repository.BaseRepository;

/**
 * @author Axmadjonov Eliboy on Tue. 15:57. 05/07/22
 * @project CoreProject
 */
@RequiredArgsConstructor
public abstract class AbstractService<R extends BaseRepository, M extends BaseMapper> {
    protected final R repository;
    protected final M mapper;
}
