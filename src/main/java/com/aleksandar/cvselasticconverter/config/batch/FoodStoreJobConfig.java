package com.aleksandar.cvselasticconverter.config.batch;

import com.aleksandar.cvselasticconverter.model.dto.FoodStore;
import com.aleksandar.cvselasticconverter.model.es.FoodStoreDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class FoodStoreJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importFoodStores(){
        return jobBuilderFactory.get("importFoodStores")
                .incrementer(new RunIdIncrementer())
                .start(fromFileIntoDataBase())
                .build();
    }

    @Bean
    public Step fromFileIntoDataBase(){
        return stepBuilderFactory.get("fromFileIntoDatabase")
                .<FoodStore,FoodStoreDoc>chunk(1000)
                .reader(foodStoresFileReader())
                .processor(processor())
                .writer(foodStoreWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public ItemProcessor<FoodStore, FoodStoreDoc> processor() {
        return new FoodStoreDocProcessor();
    }

    @Bean
    public FlatFileItemReader<FoodStore> foodStoresFileReader(){
        return new FlatFileItemReaderBuilder<FoodStore>()
                .resource(new ClassPathResource("data/food-stores.csv"))
                .name("foodStoresFileReader")
                .delimited()
                .delimiter(",")
                .names(new String[]{"country","licenseNumber","establishmentType","entityName","dbaName","streetNumber","streetName","city","stateAbbreviation",
                        "zipCode","squareFootage","latitude","longitude"

                })
                .linesToSkip(1)
                .targetType(FoodStore.class)
                .build();
    }

    @Bean
    public FoodStoreRepositoryWriter<FoodStoreDoc> foodStoreWriter(){
        return new FoodStoreRepositoryWriter<FoodStoreDoc>();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("Thread N-> :");
        return executor;
    }
}
