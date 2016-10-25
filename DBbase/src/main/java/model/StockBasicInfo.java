package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 王栋 on 2016/10/24 0024.
 */
@Entity
@Table(name="StocksBasicInfo")
public class StockBasicInfo {
    //股票的基本信息
    private String code;//股票代码
    private String name;//股票公司名称
    private String industry;//股票的类型
    private String area;//股票的公司创建地
    private double pe;//市盈率
    private double outstanding;//流通股本
    private double totals;//总股本(万)
    private double totalAssets;//总资产(万)
    private double liquidAssets;//流动资产
    private double fixedAssets;//固定资产
    private double reserved;//公积金
    private double reservedPerShare;//每股公积金
    private double eps;//每股收益
    private double bvps;//每股净资
    private double pb;//市净率
    private Date timeToMarket;//股票的上市日期

    public StockBasicInfo() {
    }

    public StockBasicInfo(String code, String name, String industry, String area, double pe, double outstanding, double totals, double totalAssets, double liquidAssets, double fixedAssets, double reserved, double reservedPerShare, double eps, double bvps, double pb, Date timeToMarket) {
        this.code = code;
        this.name = name;
        this.industry = industry;
        this.area = area;
        this.pe = pe;
        this.outstanding = outstanding;
        this.totals = totals;
        this.totalAssets = totalAssets;
        this.liquidAssets = liquidAssets;
        this.fixedAssets = fixedAssets;
        this.reserved = reserved;
        this.reservedPerShare = reservedPerShare;
        this.eps = eps;
        this.bvps = bvps;
        this.pb = pb;
        this.timeToMarket = timeToMarket;
    }

    @Id
    @Column(length=10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(length=20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length=15)
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Column(length=20)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getPe() {
        return pe;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public double getTotals() {
        return totals;
    }

    public void setTotals(double totals) {
        this.totals = totals;
    }

    public double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public double getLiquidAssets() {
        return liquidAssets;
    }

    public void setLiquidAssets(double liquidAssets) {
        this.liquidAssets = liquidAssets;
    }

    public double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public double getReserved() {
        return reserved;
    }

    public void setReserved(double reserved) {
        this.reserved = reserved;
    }

    public double getReservedPerShare() {
        return reservedPerShare;
    }

    public void setReservedPerShare(double reservedPerShare) {
        this.reservedPerShare = reservedPerShare;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getBvps() {
        return bvps;
    }

    public void setBvps(double bvps) {
        this.bvps = bvps;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public Date getTimeToMarket() {
        return timeToMarket;
    }

    public void setTimeToMarket(Date timeToMarket) {
        this.timeToMarket = timeToMarket;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("StockBasicInfo{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", industry='").append(industry).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", pe=").append(pe);
        sb.append(", outstanding=").append(outstanding);
        sb.append(", totals=").append(totals);
        sb.append(", totalAssets=").append(totalAssets);
        sb.append(", liquidAssets=").append(liquidAssets);
        sb.append(", fixedAssets=").append(fixedAssets);
        sb.append(", reserved=").append(reserved);
        sb.append(", reservedPerShare=").append(reservedPerShare);
        sb.append(", eps=").append(eps);
        sb.append(", bvps=").append(bvps);
        sb.append(", pb=").append(pb);
        sb.append(", timeToMarket=").append(timeToMarket);
        sb.append('}');
        return sb.toString();
    }
}
