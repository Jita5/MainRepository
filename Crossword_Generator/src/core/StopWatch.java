package core;

import java.util.concurrent.TimeUnit;

public class StopWatch {
	long starts;

    public static StopWatch start() {
        return new StopWatch();
    }

    private StopWatch() {
        reset();
    }

    public StopWatch reset() {
        starts = System.currentTimeMillis();
        return this;
    }

    public long time() {
        long ends = System.currentTimeMillis();
        return ends - starts;
    }

    public long time(TimeUnit unit) {
        return unit.convert(time(), TimeUnit.MILLISECONDS);
    }
}
