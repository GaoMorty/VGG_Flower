package edu.cczu.config;

import edu.cczu.utils.QCloudCosUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 大只
 * @date 2022-11-09
 */
@Configuration
public class QCloudCosUtilsConfig {
    @ConfigurationProperties(prefix = "qcloud")
    @Bean
    public QCloudCosUtils qcloudCosUtils() {
        return new QCloudCosUtils();
    }
}
