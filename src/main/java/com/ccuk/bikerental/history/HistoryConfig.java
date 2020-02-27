package com.ccuk.bikerental.history;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoryConfig {

    @Autowired
    public void configure(EventProcessingConfigurer config, ObjectMapper o) throws JsonMappingException {
        config.registerTrackingEventProcessor(
                "com.ccuk.bikerental.history",
                org.axonframework.config.Configuration::eventStore,
                c -> TrackingEventProcessorConfiguration
                        .forParallelProcessing(2)
                        .andBatchSize(200)
        );
    }
}
