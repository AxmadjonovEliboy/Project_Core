package uz.axmadjonov.coreproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Axmadjonov Eliboy on Thu. 23:27. 30/06/22
 * @project CoreProject
 */
@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("admin"),
    USER("user"),
    EMPLOYEE("employee"),
    SUPPER_ADMIN("supper_admin");

    private final String name;

    public static Role getByName(String roleName) {
        if (Objects.isNull(roleName)) return Role.USER;
        for (Role value : Role.values()) {
            if (value.name.equalsIgnoreCase(roleName)) return value;
        }
        return Role.USER;
    }
}
