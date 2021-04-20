package cn.mrdear.collection.util;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author Jeff Pan
 * @since 2019/10/6
 */
public class RetryUtils {

    /**
     * 最大重试秒数
     */
    private static final int MAX_WAIT_SECONDS = 5;


    /**
     * 重试执行一个任务
     * @param work 任务本身
     * @param retryTimes 重试最大次数
     * @param ignoreExceptions 忽略的异常
     */
    public static <V> V retry(Callable<V> work, int retryTimes, Set<Class> ignoreExceptions) throws Exception {
        int retry = 0;

        while (true) {
            try {
                return work.call();
            } catch (Exception e) {
                // 遇到忽略的异常则直接抛出去
                if (null != ignoreExceptions && ignoreExceptions.contains(e.getClass())) {
                    throw e;
                }
                // 重试结束
                if (retry >= retryTimes) {
                    throw e;
                }

                // 其他异常重试
                int sleepSec = Math.min(1 << retry++, MAX_WAIT_SECONDS);
                sleep(sleepSec * 1000L);
            }
        }
    }


    private static void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
