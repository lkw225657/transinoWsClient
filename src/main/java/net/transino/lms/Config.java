package net.transino.lms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @since 5.0
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ws")
public class Config {
    private String wsdlLocation;
    private String namespaceURI;
    private String localPart;
    private String wsdlLocation2;
    private String namespaceURI2;
    private String localPart2;
}