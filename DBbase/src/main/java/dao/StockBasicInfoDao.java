package dao;

import config.Msg;
import model.StockBasicInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.SessionFactoryUtil;
import util.StockUtil;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public class StockBasicInfoDao {
    private static BaseDao baseDao;

    public StockBasicInfoDao(){
        baseDao = new BaseDao();
    }

    //获取所有的StockBasicInfo列表
    public List<StockBasicInfo> getAllStocksBasicInfo() {
        try{
            List<StockBasicInfo> basicInfos = (List<StockBasicInfo>) baseDao.getAll(StockBasicInfo.class);
            return basicInfos;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<StockBasicInfo>();
        }
    }

    public List<String> getAllCodes(){
        List<String> codes = new ArrayList<String>();

        try {
            List<Object> lists = baseDao.execSql("select "+StringUtil.CODE+" from "+ StringUtil.STOCKS_INFO+";");
            for(Object object : lists){
               codes.add(StockUtil.getCode(object.toString()));
            }
            return codes;
        }catch (Exception e){
            e.printStackTrace();
            return codes;
        }

    }
    //提供一个完整的股票代码返回股票的基本信息对象
    //完整股票代码有两种形式 sh600000 或者 600000
    public StockBasicInfo getStockBasicInfo(String code){
        //如果没有查询到的话就返回一个null
        code = StockUtil.getCode(code);
        StockBasicInfo stockBasicInfo = null;
        try {
            stockBasicInfo = (StockBasicInfo) baseDao.findByCode(StockBasicInfo.class,code);
            return stockBasicInfo;
        }catch (Exception e){
            e.printStackTrace();
            return stockBasicInfo;
        }

    }


     public Msg updateAll(List<StockBasicInfo> stockBasicInfos){
         List<Object> objects = new ArrayList<Object>();
         for(StockBasicInfo stockBasicInfo : stockBasicInfos){
             objects.add((Object)stockBasicInfo);
         }
        return  baseDao.updateAll(objects.iterator());
    }

    public Msg updateAll(Iterator<StockBasicInfo> stockBasicInfoIterator){
        List<Object> objects = new ArrayList<Object>();
        while (stockBasicInfoIterator.hasNext()){
            objects.add(stockBasicInfoIterator.next());
        }
        return  baseDao.updateAll(objects.iterator());
    }



    public Msg update(StockBasicInfo stockBasicInfo){
        return baseDao.update(stockBasicInfo);
    }
    //后面的方法不一定会有用

//    public Msg saveAll(List<StockBasicInfo> stockBasicInfos){
//        return  baseDao.save(stockBasicInfos);
//    }
//
//    public Msg save(StockBasicInfo stockBasicInfo){
//        return baseDao.save(stockBasicInfo);
//    }
    /**
     * 根据部分的输入就可以匹配到符合部分股票代码输入的相关股票
     * @param partOfCode
     * @return
     */
    public List<StockBasicInfo> getStocksBasicInfoByCode(String partOfCode){
        List<StockBasicInfo> stockBasicInfos = new ArrayList<StockBasicInfo>();

        try {
            List<Object[]> lists = baseDao.execSqlQuery("select * from "+ StringUtil.STOCKS_INFO+" where code like \'%"+partOfCode+"%\'");
            for(Object[] objects : lists){
                StockBasicInfo temp = new StockBasicInfo(objects[0].toString(),objects[1].toString(),objects[2].toString()
                        ,objects[3].toString(),Double.parseDouble(objects[4].toString()),
                        Double.parseDouble(objects[5].toString()),Double.parseDouble(objects[6].toString()),Double.parseDouble(objects[7].toString()),
                        Double.parseDouble(objects[8].toString()),Double.parseDouble(objects[9].toString()),Double.parseDouble(objects[10].toString()),
                        Double.parseDouble(objects[11].toString()),Double.parseDouble(objects[12].toString()),Double.parseDouble(objects[13].toString()),
                        Double.parseDouble(objects[14].toString()),new Date(((java.sql.Date)objects[15]).getTime()));
                stockBasicInfos.add(temp);
            }
            return stockBasicInfos;
        }catch (Exception e){
            e.printStackTrace();
            return stockBasicInfos;
        }

    }

    /**
     * 输入购票企业的名称 返回该国内公司的基本现状
     * @param partOfName
     * @return
     */
    public List<StockBasicInfo> getStocksBasicInfoByName(String partOfName){
        List<StockBasicInfo> stockBasicInfos = new ArrayList<StockBasicInfo>();

        try {
            List<Object[]> lists = baseDao.execSqlQuery("select * from StocksInfo where name like \'%"+partOfName+"%\'");
            for(Object[] objects : lists){
                StockBasicInfo temp = new StockBasicInfo(objects[0].toString(),objects[1].toString(),objects[2].toString()
                        ,objects[3].toString(),Double.parseDouble(objects[4].toString()),
                        Double.parseDouble(objects[5].toString()),Double.parseDouble(objects[6].toString()),Double.parseDouble(objects[7].toString()),
                        Double.parseDouble(objects[8].toString()),Double.parseDouble(objects[9].toString()),Double.parseDouble(objects[10].toString()),
                        Double.parseDouble(objects[11].toString()),Double.parseDouble(objects[12].toString()),Double.parseDouble(objects[13].toString()),
                        Double.parseDouble(objects[14].toString()),new Date(((java.sql.Date)objects[15]).getTime()));
                stockBasicInfos.add(temp);
            }
            return stockBasicInfos;
        }catch (Exception e){
            e.printStackTrace();
            return stockBasicInfos;
        }

    }

    //输入地区 返回复合条件的股票基本信息
    public List<StockBasicInfo> getStocksBasicInfoByArea(String area){
        List<StockBasicInfo> stockBasicInfos = new ArrayList<StockBasicInfo>();

        try {
            stockBasicInfos = (List<StockBasicInfo>) baseDao.findByProperty(StockBasicInfo.class,"area",area);
            return stockBasicInfos;
        }catch (Exception e){
            e.printStackTrace();
            return stockBasicInfos;
        }

    }


}
