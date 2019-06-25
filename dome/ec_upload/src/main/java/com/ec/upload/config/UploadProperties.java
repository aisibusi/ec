package com.ec.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "ec.upload")
public class UploadProperties {
    private List<String> allowTypes;
    private String baseUrl;
}
