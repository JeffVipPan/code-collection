package cn.mrdear.collection.util;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;

public class TraceIdGeneratorTest {

    @org.junit.Test
    public void generateTraceId() {
        Set<String> result = new HashSet<>(10000);
        int count = 0;
        while (count++ < 10000) {
            String traceId = TraceIdGenerator.generateTraceId();
            result.add(traceId);
        }
        Assert.assertEquals(count - 1, result.size());
    }

}