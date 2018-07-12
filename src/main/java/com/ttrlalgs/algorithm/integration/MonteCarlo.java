package com.ttrlalgs.algorithm.integration;

import com.google.common.base.Preconditions;
import com.ttrlalgs.structure.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MonteCarlo {

    /**
     * MonteCarlo algorithm implementation.
     * @param leftBottom - bottom left point of area to be checked
     * @param rightTop - right top point of area to be checked
     * @param numberOfDotsPerUnit - the number of attempts will be made for each unit.
     * @param function
     * @return
     */
    public static double integration(Point leftBottom, Point rightTop, int numberOfDotsPerUnit, Function<Point, Boolean> function) {

        Preconditions.checkArgument(numberOfDotsPerUnit > 0, "Can't be less or equal to 0");

        double lowDouble = leftBottom.getY() > leftBottom.getX() ? leftBottom.getX() : leftBottom.getY();
        double highDouble = rightTop.getY() < rightTop.getX() ? rightTop.getX() : rightTop.getY();

        double area = (rightTop.getX() - leftBottom.getX()) * (rightTop.getY() - leftBottom.getY());
        long limit = Math.round(area * numberOfDotsPerUnit);

        List<CompletableFuture<Long>> futures = limitsList(limit).stream()
                .map(l -> CompletableFuture.supplyAsync(() -> findIntersections(function, lowDouble, highDouble, l)))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        long sum = futures.stream().map(CompletableFuture::join).mapToLong(Long::longValue).sum();

//        AtomicLong totalDots = new AtomicLong();
//        AtomicLong catchedDots = new AtomicLong();

//        findIntersections(function, random, lowDouble, highDouble, limit, totalDots, catchedDots);

        return area * sum / limit;
    }

    private static List<Long> limitsList(long totalLimit) {
        int threshold = 10_000_000;
        long l = totalLimit / threshold;
        List<Long> limits = new ArrayList<>();
        for (long i = 0; i < l; i++) {
            limits.add(10_000_000L);
        }
        if (totalLimit % threshold != 0) {
            limits.add(totalLimit - l * threshold);
        }
        return limits;
    }

    private static Long findIntersections(Function<Point, Boolean> function, double lowDouble, double highDouble, long limit) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long catchedDots = 0;
//        long attempts = 0;
        while (limit-- > 0) {
//            totalDots.addAndGet(1L);
            double x = random.nextDouble(lowDouble, highDouble);
            double y = random.nextDouble(lowDouble, highDouble);
            boolean isInArea = function.apply(Point.of(x, y));

            if (isInArea)
                catchedDots++;
        }
        return catchedDots;
    }
}
