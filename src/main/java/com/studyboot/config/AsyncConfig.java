package com.studyboot.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.*;

@Component
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    MeterRegistry meterRegistry;

    public AsyncConfig(MeterRegistry meterRegistry) {
        this.meterRegistry=meterRegistry;
    }

    @Override
    public Executor getAsyncExecutor(){
        ExecutorService threadPool = new ThreadPoolExecutor(4,10,100,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(5),
                getThreadFactory(),
                ((r, executor) -> {
                    System.out.println("a task got rejected");
                })
                );
        return ExecutorServiceMetrics.monitor(meterRegistry,threadPool,"custom-thread-pool",new ArrayList<>());
    }

    private ThreadFactory getThreadFactory(){
        return (r)->{
            Thread t = new Thread(r);
            t.setName("custom-thread");
            return t;
        };
    }
}
