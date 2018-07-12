package com.ttrlalgs.algorithm.integration;

import com.ttrlalgs.structure.Point;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MonteCarloTest {

    @Test
    public void integration() {
        double area = MonteCarlo.integration(Point.of(-3, -3), Point.of(3, 3), 100_000_000, p ->
                Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2)) <= 1 // Pythagorean distance less then radius.
        );
        System.out.println("Expected: " + Math.PI);
        System.out.println("Calculated: " + area);

        double error = Math.abs(100 - area / (Math.PI) * 100);
        System.out.println("Error: " + error + "%");
        assertThat(error).isLessThan(.1);
    }
}