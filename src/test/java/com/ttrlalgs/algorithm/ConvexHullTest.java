package com.ttrlalgs.algorithm;

import com.ttrlalgs.structure.Point;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ConvexHullTest {

    @Test
    public void findConvexHull() {
        List<Point> rectangle = List.of(Point.of(5, 0), Point.of(5, 5), Point.of(0, 5), Point.of(0, 0));

        List<Point> points = new ArrayList<>(rectangle);
        points.add(Point.of(4, 4));
        points.add(Point.of(1, 1));
        points.add(Point.of(0, 2)); // part of rectangle side. Should be removed as well

        List<Point> convexHull = ConvexHull.findConvexHull(points);
        assertThat(convexHull).containsOnlyElementsOf(rectangle);
    }
}