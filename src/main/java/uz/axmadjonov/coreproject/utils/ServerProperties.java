package uz.axmadjonov.coreproject.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Axmadjonov Eliboy on Wed. 12:32. 06/07/22
 * @project CoreProject
 */
@Getter
@Setter
@ConfigurationProperties(value = "service.prop")
public class ServerProperties {
    private String port;
    private String ip;
    private String url;
    private String protocol;

    public String getServerUrl() {
        return this.protocol + "://" + this.ip + ":" + this.port;
    }


}
