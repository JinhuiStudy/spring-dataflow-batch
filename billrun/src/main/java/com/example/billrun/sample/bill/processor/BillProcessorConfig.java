package com.example.billrun.sample.bill.processor;

import com.example.billrun.sample.bill.domain.Bill;
import com.example.billrun.sample.bill.domain.Usage;
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