package service;

import dao.KInfoDao;
import model.KInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public class DailyKServiceImpl implements DailyKService {

    private static KInfoDao kInfoDao;

    public DailyKServiceImpl(){
        kInfoDao = new KInfoDao();
    }

    public void updateStocksKInfo() {
        kInfoDao.updateAllKdata();
    }

    public List<KInfo> getKData(String code, String start, String end) {
        return null;
    }

    public List<KInfo> getKData(String code, Date start, Date end) {
        return null;
    }

    public List<KInfo> getKData(String code, String start) {
        return null;
    }

    public List<KInfo> getKData(String code, Date start) {
        return null;
    }
}
