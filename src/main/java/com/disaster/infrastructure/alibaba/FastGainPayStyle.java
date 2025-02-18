package com.disaster.infrastructure.alibaba;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 场景描述： 用户在支付宝拥有多种支付方式（余额、红包、余额宝等，每种支付工具分布在不同系统），
 * 每种支付方式通过调用远程服务获取可用性。在外部资源环境不变情况下，
 * 请设计程序以最短【响应时间】获得尽可能多的可用支付方式列表。
 * 补充：假定支付方式可用性咨询接口统一为
 *
 * @author disaster
 * @version 1.0
 */
public class FastGainPayStyle {

    public static void main(String[] args) {
        ArrayList<String> allPaymentList = new ArrayList<>();
        allPaymentList.add("balance");
        allPaymentList.add("Red packet");
        allPaymentList.add("Yu Er Bao");
        System.out.println(filterDisablePayment("1", allPaymentList,2000));
    }


    /**
     * 过滤不可用支付方式类型
     *
     * @param userId         the user id
     * @param allPaymentList the all payment list
     * @return 可用支付方式类型列表 list
     */
    public static List<String> filterDisablePayment(String userId, List<String> allPaymentList, long timeOut) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, allPaymentList.size(), 60L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());
        ArrayList<String> resultList = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();
        AtomicInteger count = new AtomicInteger(0);
        long startTime = System.currentTimeMillis();
        for (String payment : allPaymentList) {
            threadPoolExecutor.execute(() -> {
                try {
                    boolean enabled = PaymentRemoteService.isEnabled(userId, payment);
                    if (enabled) {
                        while (lock.tryLock()) {
                            resultList.add(payment);
                            lock.unlock();
                            break;
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    count.incrementAndGet();
                }
            });
        }
        while (count.get() != allPaymentList.size() && System.currentTimeMillis() - startTime < timeOut) {

        }
        threadPoolExecutor.shutdownNow();
        return resultList;
    }

    /**
     * The type Consult result.
     */
    public class ConsultResult {

        /**
         * 咨询结果是否可用
         */
        private boolean isEnable;

        /**
         * 错误码
         */
        private String errorCode;

    }

    /**
     * The type Payment remote serivce.
     */
    public static class PaymentRemoteService {
        /**
         * Is enabled boolean.
         *
         * @param userId      the user id
         * @param paymentType the payment type
         * @return the boolean
         */
        public static boolean isEnabled(String userId, String paymentType) {
            return true;
        }
    }
}


