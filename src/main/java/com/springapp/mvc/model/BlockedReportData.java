package com.springapp.mvc.model;

import com.springapp.mvc.vspom.DateVspom;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.YearMonth;

/**
 * Created by oem on 24.05.18.
 */
@Entity
@Table(name = "blockedReportData")
public class BlockedReportData extends Model{


//    От User
    @Column(name = "login")
    private String login;

    @Column(name = "name")
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "surname")
    private String surname;

    @Column(name = "post")
    private String post;

    @Column(name = "transponder")
    private String transponder;

    @Column(name = "accountancyType")
    private String accountancyType;

    //    коэффициент амортизации
    @Column(name = "amortizacia")
    private Double amortizacia;





//
//
////    От Auto
//    @Column(name = "brand")
//    private String brand;
//
//    @Column(name = "autoNumber")
//    private String number;
//
//    @Column(name = "year")
//    private Integer autoYear;
//
//    //    Тип кузова
//    @Column(name = "bodyType")
//    private String bodyType;
//
//    @Column(name = "engineVolume")
//    private Double engineVolume;
//
//    @Column(name = "enginePower")
//    private Integer enginePower;
//
//    @Column(name = "transmission")
//    private String transmission;
//
//    @Column(name = "fuelType")
//    private String fuelType;
//
//    @Column(name = "climateMachine")
//    private String climateMachine;select
//
//    @Column(name = "stsResource")
//    private String stsResource;
//
//    @Column(name = "osagoResource")
//    private String osagoResource;
//
//    @Column(name = "link")
//    private String link;
//
//    @Column(name = "linkNorm")
//    private Double linkNorm;

    @Column(name = "summerNorm")
    private Double summerNorm;

    @Column(name = "winterNorm")
    private Double winterNorm;




//    От Report
    @OneToOne(optional = true)
    @JoinColumn(name = "report_id")
    @LazyCollection(LazyCollectionOption.FALSE)
//    @Cascade(CascadeType.SAVE_UPDATE)
    private Report report;

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

    @Column(name = "mobileWeeks")
    private int mobileWeeks;

    @Column(name = "mobile")
    private Double mobile;

    @Column(name = "mediumFuelPrice")
    private Double mediumFuelPrice;

    @Column(name = "reportYear")
    private Integer reportYear;

    @Column(name = "reportMonth")
    private Integer reportMonthNumber;









//
//
////    для переноса
//    @Column(name = "auto")
//    private String auto;
//
//    public String getAuto() {
//        return auto;
//    }
//
//    public void setAuto(String auto) {
//        this.auto = auto;
//    }
//
//










    public BlockedReportData() {
        super();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTransponder() {
        return transponder;
    }

    public void setTransponder(String transponder) {
        this.transponder = transponder;
    }

    public String getAccountancyType() {
        return accountancyType;
    }

    public void setAccountancyType(String accountancyType) {
        this.accountancyType = accountancyType;
    }

    public Double getAmortizacia() {
        return amortizacia;
    }

    public void setAmortizacia(Double amortizacia) {
        this.amortizacia = amortizacia;
    }

//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
////    public String getModel() {
////        return model;
////    }
////
////    public void setModel(String model) {
////        this.model = model;
////    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public Integer getAutoYear() {
//        return autoYear;
//    }
//
//    public void setAutoYear(Integer autoYear) {
//        this.autoYear = autoYear;
//    }
//
//    public String getBodyType() {
//        return bodyType;
//    }
//
//    public void setBodyType(String bodyType) {
//        this.bodyType = bodyType;
//    }
//
//    public Double getEngineVolume() {
//        return engineVolume;
//    }
//
//    public void setEngineVolume(Double engineVolume) {
//        this.engineVolume = engineVolume;
//    }
//
//    public Integer getEnginePower() {
//        return enginePower;
//    }
//
//    public void setEnginePower(Integer enginePower) {
//        this.enginePower = enginePower;
//    }
//
//    public String getTransmission() {
//        return transmission;
//    }
//
//    public void setTransmission(String transmission) {
//        this.transmission = transmission;
//    }
//
//    public String getFuelType() {
//        return fuelType;
//    }
//
//    public void setFuelType(String fuelType) {
//        this.fuelType = fuelType;
//    }
//
//    public String getClimateMachine() {
//        return climateMachine;
//    }
//
//    public void setClimateMachine(String climateMachine) {
//        this.climateMachine = climateMachine;
//    }
//
//    public String getStsResource() {
//        return stsResource;
//    }
//
//    public void setStsResource(String stsResource) {
//        this.stsResource = stsResource;
//    }
//
//    public String getOsagoResource() {
//        return osagoResource;
//    }
//
//    public void setOsagoResource(String osagoResource) {
//        this.osagoResource = osagoResource;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public Double getLinkNorm() {
//        return linkNorm;
//    }
//
//    public void setLinkNorm(Double linkNorm) {
//        this.linkNorm = linkNorm;
//    }

    public Double getSummerNorm() {
        return summerNorm;
    }

    public void setSummerNorm(Double summerNorm) {
        this.summerNorm = summerNorm;
    }

    public Double getWinterNorm() {
        return winterNorm;
    }

    public void setWinterNorm(Double winterNorm) {
        this.winterNorm = winterNorm;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

//    public String getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(String period) {
//        this.period = period;
//    }

    public int getSumKmDistance() {
        return sumKmDistance;
    }

    public void setSumKmDistance(int sumKmDistance) {
        this.sumKmDistance = sumKmDistance;
    }

    public Double getSumSumm() {
        return sumSumm;
    }

    public void setSumSumm(Double sumSumm) {
        this.sumSumm = sumSumm;
    }

    public Double getSumZone() {
        return sumZone;
    }

    public void setSumZone(Double sumZone) {
        this.sumZone = sumZone;
    }

    public int getMobileWeeks() {
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
        return mediumFuelPrice;
    }

    public void setMediumFuelPrice(Double mediumFuelPrice) {
        this.mediumFuelPrice = mediumFuelPrice;
    }


    public Integer getReportYear() {
        return reportYear;
    }

    public void setReportYear(Integer reportYear) {
        this.reportYear = reportYear;
    }

    public Integer getReportMonthNumber() {
        return reportMonthNumber;
    }

    public void setReportMonthNumber(Integer reportMonthNumber) {
        this.reportMonthNumber = reportMonthNumber;
    }



    public YearMonth getYearMonth() {
//        if(reportYear == null && reportMonthNumber == null && period != null) {
//            String[] periods = period.split(" ");
//            if(reportYear == null){
//                reportYear = Integer.parseInt(periods[1]);
//            }
//            if (reportMonthNumber == null){
//                reportMonthNumber = DateVspom.getNumberOfMonth(periods[0]);
//            }
//        }
        YearMonth yearMonth = YearMonth.of(reportYear, reportMonthNumber);
        return yearMonth;
    }

    public BlockedReportData(Report report) {
        User user = report.getUser();
        Auto auto = report.getAuto();

        this.login = user.getLogin();
        this.name = user.getName();
        this.patronymic = user.getPatronymic();
        this.surname = user.getSurname();
        this.post = user.getPost();
        this.transponder = user.getTransponder();
        this.accountancyType = user.getAccountancyType();
        this.amortizacia = user.getAmortizacia();


//        this.brand = auto.getBrand();
////        this.model = auto.getModel();
//        this.number = auto.getNumber();
//        this.autoYear = auto.getYear();
//        this.bodyType = auto.getBodyType();
//        this.engineVolume = auto.getEngineVolume();
//        this.enginePower = auto.getEnginePower();
//        this.transmission = auto.getTransmission();
//        this.fuelType = auto.getFuelType();
//        this.climateMachine = auto.getClimateMachine();
//        this.stsResource = auto.getStsFileName();
//        this.osagoResource = auto.getOsagoFileName();
//        this.link = auto.getLink();
//        this.linkNorm = auto.getLinkNorm();
        this.summerNorm = auto.getSummerNorm();
        this.winterNorm = auto.getWinterNorm();


        this.report = report;
//        this.period = report.getPeriod();
        this.sumKmDistance = report.getSumKmDistance();
        this.sumSumm = report.getSumSumm();
        this.sumZone = report.getSumZone();
        this.mobileWeeks = report.getMobileWeeks();
        this.mobile = report.getMobile();
        this.mediumFuelPrice = report.getMediumFuelPrice();
        this.reportYear = report.getYear();
        this.reportMonthNumber = report.getMonthNumber();
    }


    @Override
    public String toString() {
        return "BlockedReportData{" +
                "id=" + super.getId() +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", post='" + post + '\'' +
                ", transponder='" + transponder + '\'' +
                ", accountancyType='" + accountancyType + '\'' +
                ", amortizacia=" + amortizacia +
//                ", brand='" + brand + '\'' +
////                ", model='" + model + '\'' +
//                ", number='" + number + '\'' +
//                ", autoYear=" + autoYear +
//                ", bodyType='" + bodyType + '\'' +
//                ", engineVolume=" + engineVolume +
//                ", enginePower=" + enginePower +
//                ", transmission='" + transmission + '\'' +
//                ", fuelType='" + fuelType + '\'' +
//                ", climateMachine='" + climateMachine + '\'' +
//                ", stsResource='" + stsResource + '\'' +
//                ", osagoResource='" + osagoResource + '\'' +
//                ", link='" + link + '\'' +
//                ", linkNorm=" + linkNorm +
                ", summerNorm=" + summerNorm +
                ", winterNorm=" + winterNorm +
                ", report=" + report +
//                ", period='" + period + '\'' +
                ", sumKmDistance=" + sumKmDistance +
                ", sumSumm=" + sumSumm +
                ", sumZone=" + sumZone +
                ", mobileWeeks=" + mobileWeeks +
                ", mobile=" + mobile +
                ", mediumFuelPrice=" + mediumFuelPrice +
                ", reportYear=" + reportYear +
                ", reportMonthNumber=" + reportMonthNumber +
                '}';
    }
}
