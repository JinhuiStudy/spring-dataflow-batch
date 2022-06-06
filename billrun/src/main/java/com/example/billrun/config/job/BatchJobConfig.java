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

    // [1]
    // 배치 job을 실행할 때 발생하는 주요 이슈는 Job이 재시작할 때의 동작과 관련 있다.
    // job을 실행할 때 특정 JobInstance의 JobExecution이 이미 존재한다면, ‘재시작’으로 간주한다.
    // 모든 잡이 중단된 지점부터 재시작할 수 있다면 이상적이겠지만, 불가능할 때가 있다. 이런 경우에는 개발자가
    // 직접 새 JobInstance를 생성해야 한다. 물론 스프링 배치를 사용하면 문제가 조금 쉬워지는데,
    // restartable 프로퍼티 값을 ‘false’로 설정하면 절대 Job을 재실행하지 않고 항상 새 JobInstance로 실행한다:

    // [2]
    // afterJob 메소드는 job의 성공 여부와 상관없이 호출된다는 점에 주의해라.
    // 성공/실패 여부는 JobExecution을 통해 알 수 있다.
    // 인터페이스와 동일한 어노테이션도 지원한다.
    // @BeforeJob
    // @AfterJob

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
//                .preventRestart() // 1. 재시작 방지
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