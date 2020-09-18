package com.springapp.mvc.model;



import com.springapp.mvc.vspom.DateVspom;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.List;

/**
 * Created by kot on 05.07.17.
 */

@Entity
@Table(name = "report")
public class Report extends Model {

//    @Column(name = "period")
//    private String period;

//    суммарное расстояние
    @Column(name = "sumKmDistance")
    private int sumKmDistance;

//    сумма заправок
    @Column(name = "sumSumm")
    private Double sumSumm;

//    сумма зональная
    @Column(name = "sumZone")
    private Double sumZone;

    @OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE)
    private List<Day> days;

    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private BlockedReportData blockedReportData;

    @ManyToOne(optional = true)
    @JoinColumn(name = "auto_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Auto auto;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private User user;

    @Column(name = "isClosed")
    private boolean isClosed;

    @Column(name = "mobileWeeks")
    private int mobileWeeks;

    @Column(name = "mobile")
    private Double mobile;

    @Column(name = "mediumFuelPrice")
    private Double mediumFuelPrice;

    @Column(name = "year")
    private Integer year;

    @Transient
    private String month;

    @Column(name = "monthNumber")
    private Integer monthNumber;


//    @Transient
//    private YearMonth yearMonth;

    public Report() {
        super();
    }




    public String getPeriod() {
        return DateVspom.getPeriodFromYearMonth(this.getYearMonth());
    }


    public int getSumKmDistance() {
        if(this.isClosed()){
            BlockedReportData blockedReportData= this.getBlockedReportData();
            return blockedReportData.getSumKmDistance();
        }
        return sumKmDistance;
    }
    public void setSumKmDistance(int sumKmDistance) {
        this.sumKmDistance = sumKmDistance;
    }




    public Double getSumSumm() {
        if(this.isClosed()){
            BlockedReportData blockedReportData = this.getBlockedReportData();
            return blockedReportData.getSumSumm();
        }
        return sumSumm;
    }
    public void setSumSumm(Double sumSumm) {
        this.sumSumm = sumSumm;
    }

    public Double getSumZone() {
        if(this.isClosed()){
            BlockedReportData blockedReportData = this.getBlockedReportData();
            return blockedReportData.getSumZone();
        }
        return sumZone;
    }
    public void setSumZone(Double sumZone) {
        this.sumZone = sumZone;
    }



    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public BlockedReportData getBlockedReportData() {
        return blockedReportData;
    }

    public void setBlockedReportData(BlockedReportData blockedReportData) {
        this.blockedReportData = blockedReportData;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public int getMobileWeeks() {
        if(this.isClosed()){
            BlockedReportData blockedReportData = this.getBlockedReportData();
            return blockedReportData.getMobileWeeks();
        }
        return mobileWeeks;
    }

    public void setMobileWeeks(int mobileWeeks) {
        this.mobileWeeks = mobileWeeks;
    }

    public Double getMobile() {
        return mobile;
    }

    public void setMobile(Double mobile) {
        this.mobile = mobile;
    }


    public Double getMediumFuelPrice() {
        if(this.isClosed()){
            BlockedReportData blockedReportData= this.getBlockedReportData();
            return blockedReportData.getMediumFuelPrice();
        }
        return mediumFuelPrice;
    }

    public void setMediumFuelPrice(Double mediumFuelPrice) {
        this.mediumFuelPrice = mediumFuelPrice;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }





    public YearMonth getYearMonth() {
        YearMonth yearMonth;
        if(this.isClosed()){
            BlockedReportData blockedReportData= this.getBlockedReportData();
            yearMonth = YearMonth.of(blockedReportData.getReportYear(), blockedReportData.getReportMonthNumber());
        }
        else {
            yearMonth = YearMonth.of(year, monthNumber);
        }
        return yearMonth;
    }


    public BigDecimal getCompany() {
        if(this.getPriceForOneKm() == null){
            return new BigDecimal(0);
        }
        return new BigDecimal(this.getPriceForOneKm()).multiply(new BigDecimal(this.getSumKmDistance())).setScale(2, RoundingMode.HALF_UP);
    }




    public BigDecimal getCurrentNorm() {


        BigDecimal currentNorm = new BigDecimal("0");
//
    if(this.isClosed()){
        BlockedReportData blockedReportData = this.getBlockedReportData();
        YearMonth yearMonth = blockedReportData.getYearMonth();
        int monthNumber = yearMonth.getMonthValue();

        if(monthNumber > 3 && monthNumber < 10) {
            if(blockedReportData.getSummerNorm() != null)
                currentNorm = new BigDecimal(blockedReportData.getSummerNorm());
        }
        else {
            if(blockedReportData.getWinterNorm() != null)
                currentNorm =  new BigDecimal(blockedReportData.getWinterNorm());
        }
    }

        else {
        if(auto != null){
            if(monthNumber > 3 && monthNumber < 11) {
                if(auto.getSummerNorm() != null)
                    currentNorm = new BigDecimal(auto.getSummerNorm());
            }
            else {
                if(auto.getWinterNorm() != null)
                    currentNorm =  new BigDecimal(auto
                            .getWinterNorm());
            }
        }
    }
        return  currentNorm;
    }



    public Double getPriceForOneKm() {
        BigDecimal currentNorm = this.getCurrentNorm().setScale(2, RoundingMode.HALF_UP);
        BigDecimal mediumFuelPrice;

        if(this.getMediumFuelPrice() == null){
            return 0d;
        }
        mediumFuelPrice = new BigDecimal(this.getMediumFuelPrice());

//        1.15 - 15% на погрешность расхода по норме
        BigDecimal priceForOneKm = (currentNorm.divide(new BigDecimal("100.0"))).multiply(mediumFuelPrice).multiply(new BigDecimal("1.0")).setScale(2, RoundingMode.HALF_UP);
        return priceForOneKm.doubleValue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;

        Report report = (Report) o;

        if (getId() != report.getId()) return false;
        if (getSumKmDistance() != report.getSumKmDistance()) return false;
        if (isClosed() != report.isClosed()) return false;
        if (getMobileWeeks() != report.getMobileWeeks()) return false;
//        if (getPeriod() != null ? !getPeriod().equals(report.getPeriod()) : report.getPeriod() != null) return false;
        if (getSumSumm() != null ? !getSumSumm().equals(report.getSumSumm()) : report.getSumSumm() != null)
            return false;
        if (getSumZone() != null ? !getSumZone().equals(report.getSumZone()) : report.getSumZone() != null)
            return false;
        if (getDays() != null ? !getDays().equals(report.getDays()) : report.getDays() != null) return false;
        if (getBlockedReportData() != null ? !getBlockedReportData().equals(report.getBlockedReportData()) : report.getBlockedReportData() != null)
            return false;
        if (getAuto() != null ? !getAuto().equals(report.getAuto()) : report.getAuto() != null) return false;
        if (getUser() != null ? !getUser().equals(report.getUser()) : report.getUser() != null) return false;
        if (getMobile() != null ? !getMobile().equals(report.getMobile()) : report.getMobile() != null) return false;
        if (getMediumFuelPrice() != null ? !getMediumFuelPrice().equals(report.getMediumFuelPrice()) : report.getMediumFuelPrice() != null)
            return false;
        if (getYear() != null ? !getYear().equals(report.getYear()) : report.getYear() != null) return false;
        if (getMonth() != null ? !getMonth().equals(report.getMonth()) : report.getMonth() != null) return false;
        return !(getMonthNumber() != null ? !getMonthNumber().equals(report.getMonthNumber()) : report.getMonthNumber() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
//        result = 31 * result + (getPeriod() != null ? getPeriod().hashCode() : 0);
        result = 31 * result + getSumKmDistance();
        result = 31 * result + (getSumSumm() != null ? getSumSumm().hashCode() : 0);
        result = 31 * result + (getSumZone() != null ? getSumZone().hashCode() : 0);
        result = 31 * result + (getDays() != null ? getDays().hashCode() : 0);
        result = 31 * result + (getBlockedReportData() != null ? getBlockedReportData().hashCode() : 0);
        result = 31 * result + (getAuto() != null ? getAuto().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (isClosed() ? 1 : 0);
        result = 31 * result + getMobileWeeks();
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + (getMediumFuelPrice() != null ? getMediumFuelPrice().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getMonth() != null ? getMonth().hashCode() : 0);
        result = 31 * result + (getMonthNumber() != null ? getMonthNumber().hashCode() : 0);
        return result;
    }
}

