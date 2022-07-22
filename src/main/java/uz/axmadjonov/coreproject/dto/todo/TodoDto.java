package uz.axmadjonov.coreproject.dto.todo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Axmadjonov Eliboy on Tue. 15:56. 05/07/22
 * @project CoreProject
 */
@Getter
@Setter
@SuperBuilder
public class TodoDto {

    private String name;
    private String description;
    private String ownerId;
    private String other;


}
