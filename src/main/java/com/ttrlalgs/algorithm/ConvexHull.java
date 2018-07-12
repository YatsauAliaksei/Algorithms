package com.ttrlalgs.algorithm;

import com.ttrlalgs.structure.Point;

import java.util.*;

public class ConvexHull {

    /**
     * So called Gift wrapping function
     * @return - points in order to create convex hull.
     */
    public static List<Point> findConvexHull(List<Point> points) {
        Point basePoint = points.stream() // lowest point
                .min(Comparator.comparingDouble(Point::getY)).orElseThrow();

        List<Point> pointStorage = new ArrayList<>(points);
        pointStorage.remove(basePoint);

        pointStorage.sort((p1, p2) -> {
            double p1Atan = getPolarAngleBetween2Points(basePoint, p1);
            double p2Atan = getPolarAngleBetween2Points(basePoint, p2);
            return Double.compare(p1Atan, p2Atan);
        });

        List<Point> convexHullPoints = new ArrayList<>();
        convexHullPoints.add(basePoint);
        Map<Point, Double> pointToPolarAngle = new HashMap<>();
        pointToPolarAngle.put(basePoint, .0);

        for (Point point : pointStorage) {
            ListIterator<Point> listIterator = convexHullPoints.listIterator(convexHullPoints.size());
            while (listIterator.hasPrevious()) {
                Point previous = listIterator.previous();
                Double previousPolarAngle = pointToPolarAngle.get(previous);
                double curPointPolarAngle = getPolarAngleBetween2Points(previous, point);

                if (curPointPolarAngle < 0)
                    curPointPolarAngle += Math.PI * 2;

                if (previousPolarAngle >= curPointPolarAngle) {
                    listIterator.remove();
                    pointToPolarAngle.remove(previous);
                } else {
                    convexHullPoints.add(point);
                    pointToPolarAngle.put(point, curPointPolarAngle);
                    break;
                }
            }
        }

        return convexHullPoints;
    }

    private static double getPolarAngleBetween2Points(Point basePoint, Point point) {
        return Math.atan2(point.getY() - basePoint.getY(), point.getX() - basePoint.getX());
    }
}
