package rcv.utils;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import rcv.models.Battery;

public class ReaderUtilsTest {

    @Test
    public void testGetBatterySocList() {
        Battery battery = new Battery(2.3f, 4.45f);
        List<Float> socList = ReaderUtils.getBatterySocList(Arrays.asList(battery));
        assertEquals(4.45f, socList.get(0), 0);
    }

    @Test
    public void testGetBatteryTempList() {
        Battery battery = new Battery(2.3f, 4.45f);
        List<Float> socList = ReaderUtils.getBatteryTempList(Arrays.asList(battery));
        assertEquals(2.3f, socList.get(0), 0);
    }

    @Test
    public void test() throws IOException {
        String sampleJson = "[{\"Temperature\":10,\"StateOfCharge\":6},{\"Temperature\":1,\"StateOfCharge\":0.16629264}]";
        InputStream stream = new ByteArrayInputStream((sampleJson).getBytes(StandardCharsets.UTF_8));
        InputStream stdin = System.in;
        System.setIn(stream);

        List<Battery> batteries = ReaderUtils.getBatteryData();
        System.setIn(stdin);// restore the stardard in

        assertEquals(10, batteries.get(0).getTemperature(), 0);
        assertEquals(6, batteries.get(0).getStateOfCharge(), 0);

    }
}
