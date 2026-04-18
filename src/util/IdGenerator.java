package src.util;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final AtomicInteger USER_ID = new AtomicInteger(1000);
    private static final AtomicInteger MEAL_ID = new AtomicInteger(2000);
    private static final AtomicInteger ORDER_ID = new AtomicInteger(3000);
    private static final AtomicInteger PAYMENT_ID = new AtomicInteger(4000);
    private static final AtomicInteger OFFER_ID = new AtomicInteger(5000);
    private static final AtomicInteger REWARD_ID = new AtomicInteger(6000);

    private IdGenerator() {
    }

    public static String nextUserId() {
        return "U" + USER_ID.incrementAndGet();
    }

    public static String nextMealId() {
        return "M" + MEAL_ID.incrementAndGet();
    }

    public static String nextOrderId() {
        return "O" + ORDER_ID.incrementAndGet();
    }

    public static String nextPaymentId() {
        return "P" + PAYMENT_ID.incrementAndGet();
    }

    public static String nextOfferId() {
        return "OF" + OFFER_ID.incrementAndGet();
    }

    public static String nextRewardId() {
        return "R" + REWARD_ID.incrementAndGet();
    }
}
