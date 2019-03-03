package com.practice.olegtojgildin.data.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public final class DatabaseExecutor implements Executor {

    private final ThreadPoolExecutor mThreadPoolExecutor;

    @Inject
    public DatabaseExecutor() {
        mThreadPoolExecutor = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        this.mThreadPoolExecutor.execute(command);
    }


    private static class JobThreadFactory implements ThreadFactory {
        private int count = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "request_db-" + count++);
        }
    }
}
