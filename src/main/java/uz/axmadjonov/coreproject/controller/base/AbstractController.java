package uz.axmadjonov.coreproject.controller.base;

import lombok.RequiredArgsConstructor;
import uz.axmadjonov.coreproject.service.base.BaseService;

/**
 * @author Axmadjonov Eliboy on Mon. 23:15. 04/07/22
 * @project CoreProject
 */
@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> implements BaseController {

    public final S service;
    protected final static String API = "api/";
    protected final static String VERSION = "v1/";

    protected final static String PATH = API + VERSION;


}
