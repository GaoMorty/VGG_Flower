package edu.cczu.config;

import edu.cczu.utils.QCloudCosUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gao
 * @date 2023-05-08
 */
@Configuration
public class QCloudCosUtilsConfig {
    @ConfigurationProperties(prefix = "qcloud")
    @Bean
    public QCloudCosUtils qcloudCosUtils() {
        return new QCloudCosUtils();
    }
}
