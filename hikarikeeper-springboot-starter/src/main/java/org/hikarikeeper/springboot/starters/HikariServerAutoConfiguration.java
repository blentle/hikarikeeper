package org.hikarikeeper.springboot.starters;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({HikariServer.class})
@EnableConfigurationProperties(HikariServerProperties.class)
public class HikariServerAutoConfiguration {
    //todo:

}
