package com.atech.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
@Profile("local")
public class ApplicationConfig {

//    @Bean
//    public KafkaAdmin kafkaAdmin(){
//        return new KafkaAdmin();
//    }

    @Bean
    public NewTopic newTopic(){

      return TopicBuilder.name("library-events")
                .partitions(3)
                .replicas(3)
                .build();
    }
}
