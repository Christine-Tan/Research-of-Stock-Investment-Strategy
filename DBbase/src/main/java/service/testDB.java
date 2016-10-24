package service;

import dao.BaseDao;
import model.test;

/**
 * Created by Jiayiwu on 16/10/23.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */
public class testDB {

    public static void main(String agrs[]){
        new testDB().test();
    }

    private void test(){
        test a = new test(2,"StevenWu","测试类");
        BaseDao baseDao = new BaseDao();
        baseDao.save(a);
    }
}
