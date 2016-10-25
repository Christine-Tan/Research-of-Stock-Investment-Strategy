package service;

import model.StockBasicInfo;

import java.util.List;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public interface StocksBasicInfoService {
    //获取所有的股票的基本信息 A股
    public List<StockBasicInfo> getAllStockBasicInfo();
    //更新所有的股票的基本信息
    public void updateAllBasicInfo();
    //更新个股的基本信息
    public void updateBasicInfo(StockBasicInfo stockBasicInfo);
    //获取个股的基本信息 根据code
    public StockBasicInfo getStockBasicInfo(String code);
}
