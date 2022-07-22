package uz.axmadjonov.coreproject.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Axmadjonov Eliboy on Tue. 05:26. 19/07/22
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.info")
public class OpenApiProperties {
    private String title;
    private String description;
    private String version;
    private String termOfService;
    private String contactName;
    private String contactEmail;
    private String contactUrl;
    private String licenseName;
    private String licenseUrl;
}