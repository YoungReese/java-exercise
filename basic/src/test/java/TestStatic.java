import com.ly.test.StaticClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestStatic {

    @Test
    public void test() throws InterruptedException {
        System.out.println("静态内部类加载时间："+ StaticClass.InnerStaticClass.INNER_STATIC_DATE);
        TimeUnit.SECONDS.sleep(5);
    }
}