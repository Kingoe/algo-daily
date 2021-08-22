import org.junit.Test;
import utils.RateLimitUtils;

import java.time.LocalTime;
import java.util.Random;

public class LimitTest {

    @Test
    public void testHd() throws Exception {
        while (true) {
            // 任意3秒内，只允许10次通过
            System.out.println(LocalTime.now().toString() + RateLimitUtils.isGo("ListId", 10, 3000L));
            // 睡眠0-10秒
            Thread.sleep(1000 * new Random().nextInt(2));
        }
    }
}
