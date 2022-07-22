package uz.axmadjonov.coreproject.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Axmadjonov Eliboy on Thu. 23:28. 30/06/22
 * @project CoreProject
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "create_at", columnDefinition = "TIMESTAMP default NOW()")
    private LocalDateTime createAt;

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updateAt;

    private Boolean deleted = false;

    private Boolean blocked = false;


}
