package edu.itplus.bibliospring.backend.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.PropertyResolver;

@Configuration
@PropertySource("classpath:/Bibliospring.properties")
public class PropertyProvider {
    @Autowired
    private static PropertyResolver propertyResolver;

    public static String getProperty(String key) {
        return propertyResolver.getProperty(key);
    }
}
