package service;

import dao.StockBasicInfoDao;
import model.StockBasicInfo;
import util.DateUtil;
import util.StockUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
public class StocksBasicInfoServiceImpl implements StocksBasicInfoService {

    private static StockBasicInfoDao stockBasicInfoDao;

    public StocksBasicInfoServiceImpl(){
        stockBasicInfoDao = new StockBasicInfoDao();
    }

    public List<StockBasicInfo> getAllStockBasicInfo() {
        List<StockBasicInfo> stockBasicInfos = new ArrayList<StockBasicInfo>();
        try{
        stockBasicInfos = stockBasicInfoDao.getAllStocksBasicInfo();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return stockBasicInfos;
        }
    }

    public void updateAllBasicInfo() {
        List<StockBasicInfo> stockBasicInfos = getAllBasicInfo();
        System.out.print(stockBasicInfos.size());
        stockBasicInfoDao.updateAll(stockBasicInfos.iterator());
    }

    public void updateBasicInfo(StockBasicInfo stockBasicInfo) {
        stockBasicInfoDao.update(stockBasicInfo);
    }

    public StockBasicInfo getStockBasicInfo(String code) {
        StockBasicInfo stockBasicInfo = stockBasicInfoDao.getStockBasicInfo(StockUtil.getCode(code));
        return stockBasicInfo;
    }

    /**
     * 从别的网站上获取所有A股的基本信息
     * @return
     */
    private List<StockBasicInfo> getAllBasicInfo(){
        getBasicInfo();//先将更新的文件存储到本地然后再读取//好像这样效率更高吧 不知道是因为网速问题还是啥
        List<StockBasicInfo> stockBasicInfos = new ArrayList<StockBasicInfo>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("stockInfo.csv");
            InputStreamReader inputReader = new InputStreamReader(inputStream,"GBK");
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                StockBasicInfo temp = getStockInfo(line);
                if (temp!=null){
                stockBasicInfos.add(temp);
                }

            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            return stockBasicInfos;
        }

    }






    //根据获取的每行信息 解析成股票的基本信息
    private static StockBasicInfo getStockInfo(String info){
        try {
            String[] infos = info.split(",");
            StockBasicInfo stockInfo = new StockBasicInfo();
            stockInfo.setCode(StockUtil.getCode(infos[0]));
            stockInfo.setName(infos[1]);
            stockInfo.setIndustry(infos[2]);
            stockInfo.setArea(infos[3]);
            stockInfo.setPe(Double.parseDouble(infos[4]));
            stockInfo.setOutstanding(Double.parseDouble(infos[5]));
            stockInfo.setTotals(Double.parseDouble(infos[6]));
            stockInfo.setTotalAssets(Double.parseDouble(infos[7]));
            stockInfo.setLiquidAssets(Double.parseDouble(infos[8]));
            stockInfo.setFixedAssets(Double.parseDouble(infos[9]));
            stockInfo.setReserved(Double.parseDouble(infos[10]));
            stockInfo.setReservedPerShare(Double.parseDouble(infos[11]));
            stockInfo.setEps(Double.parseDouble(infos[12]));
            stockInfo.setBvps(Double.parseDouble(infos[13]));
            stockInfo.setPb(Double.parseDouble(infos[14]));
            stockInfo.setTimeToMarket(DateUtil.getDate(infos[15]));
            System.out.println(stockInfo);
            return stockInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    private void getBasicInfo() {
        List<StockBasicInfo> stockBasicInfos = new ArrayList<StockBasicInfo>();
        try {
            URL url = new URL("http://218.244.146.57/static/all.csv");
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();

            InputStream is = urlcon.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
            String filePath = "stockInfo.csv";
            System.out.println(filePath);
            File file = getFile(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            byte[] buf = new byte[8 * 1024];
            int length = 0;

            while ((length = bufferedInputStream.read(buf)) != -1) {
                bufferedOutputStream.write(buf, 0, length);
            }

            bufferedInputStream.close();
            bufferedOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }


    private File getFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }
}
