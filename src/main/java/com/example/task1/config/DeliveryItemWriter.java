package com.example.task1.config;

import com.example.task1.entity.Delivery;
import com.example.task1.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeliveryItemWriter implements ItemWriter<Delivery> {

   @Autowired
   private DeliveryRepository deliveryRepository;
    @Override
    public void write(Chunk<? extends Delivery> chunk) throws Exception {
        log.info("writing delivery info");
        deliveryRepository.saveAll(chunk);
    }
}
