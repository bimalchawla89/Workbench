package arch.wb.retail.util;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Executor that runs a task on a new background thread.
 */
public class DiskIOThreadExecutor implements Executor {

    private static final int THREAD_COUNT = 3;
    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {
        mDiskIO = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
