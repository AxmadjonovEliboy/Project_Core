package uz.axmadjonov.coreproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Axmadjonov Eliboy on Mon. 17:32. 04/07/22
 * @project CoreProject
 */
@Getter
@AllArgsConstructor
public enum Region {
    TOSHKENT("Toshkent"),
    FARGONA("Farg`ona"),
    NAMANGAN("Namangan"),
    ANDIJON("Andijon"),
    SIRDARYO("Sirdaryo"),
    JIZZAX("Jizzax"),
    SAMARQAND("Samarqand"),
    BUXORO("Buxoro"),
    QASHQADARYO("Qashqadaryo"),
    SURXONDARYO("Surxondaryo"),
    NAVOIY("Navoiy"),
    XORAZM("Xorazm");

    private final String name;

    public static Region getByName(String regionName) {
        if (Objects.isNull(regionName)) return null;
        for (Region value : Region.values()) {
            if (value.getName().equalsIgnoreCase(regionName)) return value;
        }
        return null;
    }


}
