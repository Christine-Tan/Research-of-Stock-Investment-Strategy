package service;

import model.KInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public interface DailyKService {
    //更新所有的股票的K线数据 定时调用就好 没有必要每次都调用
    public void updateStocksKInfo();

    //获取特定时期的K线数据
    public List<KInfo> getKData(String code, String start, String end);
    public List<KInfo> getKData(String code, Date start, Date end);
    //只有开始时间的股票K线数据
    public List<KInfo> getKData(String code, String start);
    public List<KInfo> getKData(String code, Date start);
    //如果有其他需要的接口 到时候再添加

}
