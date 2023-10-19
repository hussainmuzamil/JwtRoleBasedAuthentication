package com.example.task1.config;

import com.example.task1.entity.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j

public class DeliveryItemProcessor implements ItemProcessor<Delivery,Delivery> {
    @Override
    public Delivery process(Delivery item) throws Exception {
        log.info("Processing item{}",item);
        item.setStopNo(item.getStopNo());
        return item;
    }
}
