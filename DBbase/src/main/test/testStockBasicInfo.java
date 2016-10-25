import model.StockBasicInfo;
import org.junit.Test;
import service.StocksBasicInfoService;
import service.StocksBasicInfoServiceImpl;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public class testStockBasicInfo {

    private  static StocksBasicInfoService service = new StocksBasicInfoServiceImpl();
    @Test
    public void testUpdateAll(){
        service.updateAllBasicInfo();
    }
}
