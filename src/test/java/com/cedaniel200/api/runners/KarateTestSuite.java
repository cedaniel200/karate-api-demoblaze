package com.cedaniel200.api.runners;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KarateTestSuite {

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:com")
                .outputCucumberJson(true)
                .outputHtmlReport(true)
                .parallel(5);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
