package eu.macphail.amqclitoolset.metadata.boundary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app-metadata.properties")
public class AppMetadata {

    @Value("${name}")
    private String name;

    @Value("${version}")
    private String version;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
