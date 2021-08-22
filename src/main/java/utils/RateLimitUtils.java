package utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流算法集合
 */
public class RateLimitUtils {

    // 固定时间算法 start
    //初始时间
    private static long startTime = System.currentTimeMillis();
    //初始计数值
    private static final AtomicInteger ZERO = new AtomicInteger(0);
    //时间窗口限制
    private static final long interval = 1000;
    //限制通过请求
    private static int limit = 100;
    //请求计数
    private AtomicInteger requestCount = ZERO;
    // 固定时间算法 end

    /** 队列id和队列的映射关系，队列里面存储的是每一次通过时候的时间戳，这样可以使得程序里有多个限流队列 */
    private volatile static Map<String, List<Long>> MAP = new ConcurrentHashMap<>();


    /**
     * 固定时间窗口算法
     * <p>
     *     可以使用在分布式环境下，使用单点存储计数值，比如redis、并且设置自动过期时间，这时候就可以统计整个集群的流量，并且进行限流。
     *     缺点是不能处理临界问题，或者说限流策略不够平滑；
     *     可能在限流临界点来了双倍的流量，c超过系统的限制；
     *     计数器限流允许出现 2permitsPerSecond 的突发流量，可以使用*滑动窗口算法去优化。
     * </p>
     * @return
     */
    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        //在时间窗口内
        if (now < startTime + interval) {
            //判断是否超过最大请求
            if (requestCount.get() < limit) {
                requestCount.incrementAndGet();
                return true;
            }
            return false;
        } else {
            //新一轮时间窗口开启，超时重置
            startTime = now;
            requestCount = ZERO;
            return true;
        }
    }

    /**
     * 滑动时间窗口限流算法
     * 在指定时间窗口，指定限制次数内，是否允许通过
     *
     * @param listId   队列id
     * @param count   限制次数
     * @param timeWindow 时间窗口大小
     * @return 是否允许通过
     */
    public static synchronized boolean isGo(String listId, int count, long timeWindow) {
        // 获取当前时间
        long nowTime = System.currentTimeMillis();
        // 根据队列id，取出对应的限流队列，若没有则创建
        List<Long> list = MAP.computeIfAbsent(listId, k -> new LinkedList<>());
        // 如果队列还没满，则允许通过，并添加当前时间戳到队列开始位置
        if (list.size() < count) {
            list.add(0, nowTime);
            return true;
        }

        // 队列已满（达到限制次数），则获取队列中最早添加的时间戳
        Long farTime = list.get(count - 1);
        // 用当前时间戳 减去 最早添加的时间戳
        if (nowTime - farTime <= timeWindow) {
            // 若结果小于等于timeWindow，则说明在timeWindow内，通过的次数大于count
            // 不允许通过
            return false;
        } else {
            // 若结果大于timeWindow，则说明在timeWindow内，通过的次数小于等于count
            // 允许通过，并删除最早添加的时间戳，将当前时间添加到队列开始位置
            list.remove(count - 1);
            list.add(0, nowTime);
            return true;
        }
    }


}
