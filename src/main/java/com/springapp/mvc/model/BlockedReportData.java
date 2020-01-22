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
        this.summerNorm = auto.getSummerNorm();
        this.winterNorm = auto.getWinterNorm();
        this.report = report;
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
                ", summerNorm=" + summerNorm +
                ", winterNorm=" + winterNorm +
                ", report=" + report +
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
