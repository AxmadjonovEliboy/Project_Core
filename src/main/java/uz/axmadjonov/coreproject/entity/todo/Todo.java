package uz.axmadjonov.coreproject.entity.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.axmadjonov.coreproject.entity.auth.AuthUser;
import uz.axmadjonov.coreproject.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @author Axmadjonov Eliboy on Mon. 18:45. 04/07/22
 * @project CoreProject
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo extends Auditable {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    private LocalDate expire;

    @Column(columnDefinition = "boolean default false")
    private Boolean done;

    @ManyToOne
    private AuthUser owner;

}
