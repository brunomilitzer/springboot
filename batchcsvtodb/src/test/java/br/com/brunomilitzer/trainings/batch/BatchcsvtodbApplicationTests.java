package br.com.brunomilitzer.trainings.batch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchcsvtodbApplicationTests {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @Test
    void testBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        this.launcher.run(this.job, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
    }

}
