package com.example.billrun.config.processor;

import com.example.billrun.config.domain.Bill;
import com.example.billrun.config.domain.Usage;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BillProcessorConfig {

    @Bean
    ItemProcessor<Usage, Bill> billProcessor() {
        return new BillProcessor();
    }

}