package com.example.billrun.config.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Slf4j
@EnableTask
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job billJob(Step billStep) {
        return jobBuilderFactory.get("billJob")
                .incrementer(new RunIdIncrementer())
                .start(billStep)
                .build();
    }


    @Bean
    @AfterJob()
    public Job footballJob(Step playerLoad, Step gameLoad, Step playerSummarization) {

        return this.jobBuilderFactory.get("footballJob")
//                .preventRestart() // 재시작 방지
                .listener(new sampleListener())
//                .listener(new JobLoggerListener())
                .start(playerLoad)
                .next(gameLoad)
                .next(playerSummarization)
                .build();
    }

    static class sampleListener  implements JobExecutionListener {

        @Override
        public void beforeJob(JobExecution jobExecution) {

        }

        @Override
        public void afterJob(JobExecution jobExecution) {
            log.info("======> After Job");

            if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
                log.info("======> Job Success");
            } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                log.error("======> Job Failure");
            }
        }
    }

    static class JobLoggerListener{

        @BeforeJob
        public void beforeJob(JobExecution jobExecution) {
            log.info("======> Before Job");
        }

        @AfterJob
        public void afterJob(JobExecution jobExecution) {

            log.info("======> After Job");

            if (jobExecution.getStatus() == BatchStatus.COMPLETED ) {
                log.info("======> Job Success");
            } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
                log.error("======> Job Failure");
            }
        }
    }



}