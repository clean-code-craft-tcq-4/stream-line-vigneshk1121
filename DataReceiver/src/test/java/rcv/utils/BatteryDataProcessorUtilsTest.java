package rcv.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BatteryDataProcessorUtilsTest {

    @Test
    public void testGetSMA() {
        List<Float> data = Arrays.asList(5f, 5f, 1f, 1f, 1f);
        float sma = BatteryDataProcessorUtils.getSMA(data, 3);
        assertEquals(1f, sma, 0);
    }

    @Test
    public void testGetSMA2() {
        List<Float> data = Arrays.asList(5f, 1f, 1f, 1f);
        float sma = BatteryDataProcessorUtils.getSMA(data, 4);
        assertEquals(2f, sma, 0);
    }
}
