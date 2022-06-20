package com.example.billrun.sample.bill.processor;

import com.example.billrun.sample.bill.domain.Bill;
import com.example.billrun.sample.bill.domain.Usage;
import org.springframework.batch.item.ItemProcessor;


public class BillProcessor implements ItemProcessor<Usage, Bill> {

    @Override
    public Bill process(Usage usage) {
        Double billAmount = usage.getDataUsage() * .001 + usage.getMinutes() * .01;
        return new Bill(
                usage.getId(), usage.getFirstName(), usage.getLastName(),
                usage.getDataUsage(), usage.getMinutes(), billAmount
        );
    }
}