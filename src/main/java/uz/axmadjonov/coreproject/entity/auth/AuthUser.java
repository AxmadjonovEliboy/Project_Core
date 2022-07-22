package uz.axmadjonov.coreproject.entity.auth;

import lombok.*;
import uz.axmadjonov.coreproject.entity.address.Address;
import uz.axmadjonov.coreproject.entity.base.Auditable;
import uz.axmadjonov.coreproject.enums.Gender;
import uz.axmadjonov.coreproject.enums.Role;

import javax.persistence.*;

/**
 * @author Axmadjonov Eliboy on Thu. 23:25. 30/06/22
 * @project CoreProject
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class AuthUser extends Auditable {

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "path_image")
    private String pathImage;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

    private Boolean status;
}
