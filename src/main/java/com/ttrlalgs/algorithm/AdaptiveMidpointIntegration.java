package com.ttrlalgs.algorithm;

import java.util.function.UnaryOperator;

public class AdaptiveMidpointIntegration {

    /**
     * Adaptive integration function for any continuous {@param function} function.
     * @param accuracy - of integration calculation. Note, high accuracy could lead to StackOverflowError.
     * @param function - continuous on {@param startX} & {@param endX} function.
     * @return - calculated with {@param accuracy} area of {@param function} rounded by {@param startX} & {@param endX}.
     */
    public static double integration(double startX, double endX, double accuracy, UnaryOperator<Double> function) {
        double baseSegmentRange = (endX - startX) / 10;

        double area = 0;
        while (startX < endX) {
            double nextX = startX + baseSegmentRange;
            double roughArea = calculateArea(startX, nextX, function, baseSegmentRange);
            double v = baseSegmentRange / 2;
            double dividedArea = calculateArea(startX, startX + v, function, v) + calculateArea(startX + v, nextX, function, v);

            double calculatedAccuracy = getCalculatedAccuracy(roughArea, dividedArea);
            roughArea = dividedArea;
            while (calculatedAccuracy > accuracy) {
                dividedArea = integration(startX, nextX, accuracy, function);
                calculatedAccuracy = getCalculatedAccuracy(roughArea, dividedArea);
            }

            area += Double.isNaN(dividedArea) ? 0 : dividedArea;
            startX = nextX;
        }
        return area;
    }

    private static double getCalculatedAccuracy(double roughArea, double dividedArea) {
        return Math.abs(roughArea - dividedArea) / dividedArea;
    }

    private static double calculateArea(double startX, double endX, UnaryOperator<Double> function, double baseSegmentRange) {
        Double startY = function.apply(startX);
        Double endY = function.apply(endX);

        if (startY == null || endY == null) {
            throw new IllegalStateException("Function should be continuous on startX-endX range.");
        }

        return (baseSegmentRange * startY + baseSegmentRange * endY) / 2;
    }
}
