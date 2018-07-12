package com.ttrlalgs.algorithm;

import com.ttrlalgs.algorithm.integration.AdaptiveMidpointIntegration;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AdaptiveMidpointIntegrationTest {

    @Test
    public void integration() {
        double area = AdaptiveMidpointIntegration.integration(0, 1, .1, p -> Math.sqrt(1 - Math.pow(p, 2)));
        System.out.println("Expected: " + Math.PI / 4);
        System.out.println("Estimated: " + area);

        double error = 100 - area / (Math.PI / 4) * 100;
        System.out.println("Error: " + (error + "%"));
        assertThat(error).isLessThan(.1);
    }
}