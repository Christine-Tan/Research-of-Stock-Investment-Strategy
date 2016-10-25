package dao;

import model.KInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import util.DateUtil;
import util.JdbcUtil;
import util.StockUtil;
import util.StringUtil;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public class KInfoDao {
    //散列成10个表
    private static final int nums = 10;
    private static final String RECORD = "record";

    //考虑了一下还是使用JDBC 更新基本信息太慢了……而且我不太会用hibernate散列
    public KInfoDao() {
        Connection connection = JdbcUtil.getInstance().getConnection();

        //懒得判断到底有没有这张表了直接就是创建表
        try {
            connection.setAutoCommit(false);
            for (int i = 0; i < nums; i++) {
                Statement statement = connection.createStatement();
                statement.executeUpdate(createTableSQL(i));
            }
            connection.commit();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    private String createTableSQL(int i){
        StringBuilder sb = new StringBuilder();
        sb.append("create table ");
        sb.append(StringUtil.STOCKS_K_INFO+i);
        sb.append("(");
        sb.append(StringUtil.CODE).append(" varchar(8),");
        sb.append(StringUtil.DATE).append(" datetime,");
        sb.append(StringUtil.OPEN).append(" double,");
        sb.append(StringUtil.HIGH).append(" double,");
        sb.append(StringUtil.CLOSE).append(" double,");
        sb.append(StringUtil.LOW).append(" double,");
        sb.append(StringUtil.VOLUME).append(" double,");
        sb.append(StringUtil.PRICE_CHANGE).append(" double,");
        sb.append(StringUtil.P_CHANGE).append(" double,");
        sb.append(StringUtil.MA5).append(" double,");
        sb.append(StringUtil.MA10).append(" double,");
        sb.append(StringUtil.MA20).append(" double,");
        sb.append(StringUtil.V_MA5).append(" double,");
        sb.append(StringUtil.V_MA10).append(" double,");
        sb.append(StringUtil.V_MA20).append(" double,");
        sb.append(StringUtil.TURNOVER).append(" double,");
        sb.append("primary key("+StringUtil.CODE+","+StringUtil.DATE+")");
        sb.append(");");
        return sb.toString();
    }


    //更新数据
    public void updateAllKdata(){
        StockBasicInfoDao stockBasicInfoDao = new StockBasicInfoDao();
        List<String> codes = stockBasicInfoDao.getAllCodes();
        for (String code:codes){
            updateKdata(code);
        }
    }

    private void updateKdata(String code){
        code = StockUtil.getCode(code);
        Connection connection = JdbcUtil.getInstance().getConnection();
        try {
            URL url = new URL("http://api.finance.ifeng.com/akdaily/?code="+code+"&type=last");
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String content = reader.readLine();
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jArray = jsonObject.getJSONArray(RECORD);
            for (int i = 0 ; i < jArray.length() ;i++){
                JSONArray jsonArray = jArray.getJSONArray(i);
                KInfo kInfo = new KInfo(code, DateUtil.getDay(jsonArray.getString(0)),
                        Double.parseDouble(jsonArray.getString(1).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(2).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(3).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(4).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(5).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(6).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(7).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(8).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(9).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(10).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(11).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(12).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(13).replaceAll(",","")),
                        Double.parseDouble(jsonArray.getString(14).replaceAll(",","")));
                save(kInfo,connection);
            }
            connection.commit();
            System.out.println(code+"存入K线数据");

        } catch (MalformedURLException e) {
            e.printStackTrace();

            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }catch (Exception e){
            return;
        }
    }

    private void save(KInfo kInfo,Connection connection) throws SQLException {
        String sql = "insert into "+StringUtil.STOCKS_K_INFO+
                (Integer.parseInt(kInfo.getCode().substring(2)))%10
                +" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,kInfo.getCode());
        statement.setDate(2,new java.sql.Date(kInfo.getDate().getTime()));
        statement.setDouble(3,kInfo.getOpen());
        statement.setDouble(4,kInfo.getHigh());
        statement.setDouble(5,kInfo.getClose());
        statement.setDouble(6,kInfo.getLow());
        statement.setDouble(7,kInfo.getVolume());
        statement.setDouble(8,kInfo.getPrice_change());
        statement.setDouble(9,kInfo.getP_change());
        statement.setDouble(10,kInfo.getMa5());
        statement.setDouble(11,kInfo.getMa10());
        statement.setDouble(12,kInfo.getMa20());
        statement.setDouble(13,kInfo.getV_ma5());
        statement.setDouble(14,kInfo.getV_ma5());
        statement.setDouble(15,kInfo.getV_ma5());
        statement.setDouble(16,kInfo.getTurnover());

        statement.executeUpdate();

    }

}
