package com.example.billrun.config.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SimpleStepConfig {
    private final StepBuilderFactory stepBuilderFactory;
    @Bean
    public Step playerLoad() {
        return stepBuilderFactory.get("playerLoad")
                .tasklet((contribution, chunkContext) -> {
                    log.info("======> Player Load");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step gameLoad() {
        return stepBuilderFactory.get("gameLoad")
                .tasklet((contribution, chunkContext) -> {
                    log.info("======> Game Load");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step playerSummarization() {
        return stepBuilderFactory.get("playerSummarization")
                .tasklet((contribution, chunkContext) -> {
                    log.info("======> Player Summarization");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
