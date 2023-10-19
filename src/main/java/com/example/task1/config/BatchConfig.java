package com.example.task1.config;

import com.example.task1.entity.Delivery;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//.names(new String[]{"StopNo","TrackingId","Time(min)","Arrival","TimeWind","ActualCustomer","Postal","Signature","Customer","Latitude","Longitude","Place","Cubic(ft),Zone"})
@Configuration
public class BatchConfig {
    @Bean
    public Job deliveryReaderJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("deliveryReadJob",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep(jobRepository,platformTransactionManager))
                .build();

    }
    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("deliveryReadStep",jobRepository)
                .<Delivery,Delivery>chunk(20,platformTransactionManager)
                .reader(deliveryItemReader())
                .processor(deliveryItemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    public FlatFileItemReader<Delivery> deliveryItemReader(){
        FlatFileItemReader<Delivery> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/CSVData/plan_manifest_july_31__1_.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(2);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }
    private LineMapper<Delivery> lineMapper() {
        DefaultLineMapper<Delivery> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("StopNo","TrackingId","Time","Arrival","timeWindow","CustomerAddress","Postal","Signature","Customer","Latitude","Longitude","Place","CubicFt,Zone");

        BeanWrapperFieldSetMapper<Delivery> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Delivery.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
    @Bean
    public DeliveryItemProcessor deliveryItemProcessor(){
        return new DeliveryItemProcessor();
    }
    @Bean
    public ItemWriter<Delivery> itemWriter(){
        return new DeliveryItemWriter();
    }
}
