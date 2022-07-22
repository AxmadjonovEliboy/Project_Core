package uz.axmadjonov.coreproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Axmadjonov Eliboy on Mon. 17:27. 04/07/22
 * @project CoreProject
 */
@Getter
@AllArgsConstructor
public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");


    private final String name;

    public static Gender getByName(String genderName) {
        if (Objects.isNull(genderName)) return Gender.OTHER;
        for (Gender value : Gender.values()) {
            if (value.name.equalsIgnoreCase(genderName)) return value;
        }
        return Gender.OTHER;
    }
}
