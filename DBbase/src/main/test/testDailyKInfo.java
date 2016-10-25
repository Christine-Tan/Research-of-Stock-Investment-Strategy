import org.junit.Test;
import service.DailyKService;
import service.DailyKServiceImpl;

/**
 * Created by 王栋 on 2016/10/25 0025.
 */
public class testDailyKInfo {
    DailyKServiceImpl dailyKService = new DailyKServiceImpl();

    @Test
    public void testUpdate(){
        dailyKService.updateStocksKInfo();
    }
}
