package com.springapp.mvc.model;

import com.springapp.mvc.enums.ClimateMachineTypes;
import com.springapp.mvc.enums.Period;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;




/**
 * Created by oem on 24.05.18.
 */

@Entity
@Table(name = "auto")
public class Auto extends AbstractObj{

        @Column(name = "brand")
        private String brand;

        @Column(name = "number")
        private String number;

        @Column(name = "year")
        private Integer year;

        //    Тип кузова
        @Column(name = "bodyType")
        private String bodyType;

        @Column(name = "engineVolume")
        private Double engineVolume;

        @Column(name = "enginePower")
        private Integer enginePower;

        @Column(name = "transmission")
        private String transmission;

        @Column(name = "fuelType")
        private String fuelType;

        @Column(name = "climateMachine")
        private String climateMachine;

        @Column(name = "stsFileName")
        private String stsFileName;

        @Column(name = "osagoFileName")
        private String osagoFileName;

        @Column(name = "link")
        private String link;

        @Column(name = "linkNorm")
        private Double linkNorm;

        @Column(name = "stsNumber")
        private String stsNumber;


        @OneToOne(mappedBy = "currentAuto")
        @ManyToOne(optional = true)
        @JoinColumn(name = "user_id")
        @LazyCollection(LazyCollectionOption.FALSE)
        private User user;


        @OneToMany(mappedBy = "auto")
        @LazyCollection(LazyCollectionOption.FALSE)
        private List<Report> reports;



    @Column(name = "osagoDate")
    private String osagoDate;

    @Transient
    private Double summerNorm;

    @Transient
    private Double winterNorm;

    @Transient
    private boolean currentAuto;


    public void setSummerNorm(Double summerNorm) {
        this.summerNorm = summerNorm;
    }

    public void setWinterNorm(Double winterNorm) {
        this.winterNorm = winterNorm;
    }

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ChangesLog> changesLogs;

    public Auto() {
        super();
    }




    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getClimateMachine() {
        return climateMachine;
    }

    public void setClimateMachine(String climateMachine) {
        this.climateMachine = climateMachine;
    }

    public String getStsFileName() {
        return stsFileName;
    }

    public void setStsFileName(String stsFileName) {
        this.stsFileName = stsFileName;
    }

    public String getOsagoFileName() {
        return osagoFileName;
    }

    public void setOsagoFileName(String osagoFileName) {
        this.osagoFileName = osagoFileName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getLinkNorm() {
        return linkNorm;
    }

    public void setLinkNorm(Double linkNorm) {
        this.linkNorm = linkNorm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getOsagoDate() {
        return osagoDate;
    }


    public void setOsagoDate(String osagoDate) {
        this.osagoDate = osagoDate;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Double getSummerNorm() {
        return getNorm(Period.SUMMER);
    }

    public boolean getCurrentAuto() {
        return currentAuto;
    }

    public void setCurrentAuto(boolean currentAuto) {
        this.currentAuto = currentAuto;
    }

    public Double getWinterNorm() {
        return getNorm(Period.WINTER);
    }


    public List<ChangesLog> getChangesLogs() {
        return changesLogs;
    }

    public void setChangesLogs(List<ChangesLog> changesLogs) {
        this.changesLogs = changesLogs;
    }

    private Double getNorm(Period period) {
        List<Koefficient> koefficients = getKoefficients();
        Double norm = 0d;
        int summKoef = 0;

        String ignor = null;
        switch (period){
            case WINTER:
                ignor = Period.SUMMER.getName();
                break;
            case SUMMER:
                ignor = Period.WINTER.getName();
                break;
        }

        for (Koefficient k : koefficients) {
            if (!k.getPrimechanie().contains(ignor)) {
                summKoef += k.getValue();
            }
        }
        summKoef += 100;

        if(linkNorm != null) {
            BigDecimal bdBNrm = new BigDecimal(this.linkNorm).multiply(new BigDecimal(summKoef)).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
            norm = bdBNrm.doubleValue();
        }
        return norm;
    }



        public List<Koefficient> getKoefficients() {

            List<Koefficient> koefficients = new LinkedList<>();
            Koefficient k1 = new Koefficient("Работа в зимнее время года", 10, Period.WINTER.getName());
            Koefficient k2 = new Koefficient("Работа в крупных населенных пунктах", 25, "Постоянный");

            int kAge = 0;
            String prim = "Новое авто";
            if (year != null) {
                    int age = LocalDate.now().getYear() - year;
                    if (age >= 0 && age <= 4) {
                        kAge = 0;
                    } else if (age >= 5 && age <= 10) {
                        kAge = age;
                    } else if (age > 10) {
                        kAge = 10;
                    }
                prim = age + " лет авто";
            }
            Koefficient k3 = new Koefficient("Возраст авто + общий пробег", kAge, prim);

            int climate = 0;
            int cond = 0;

            if (climateMachine != null) {
                    if (climateMachine.equalsIgnoreCase(ClimateMachineTypes.CLIMATE.getName())) {
                        climate = 5;
                    } else if (climateMachine.equalsIgnoreCase(ClimateMachineTypes.CONDITIONER.getName())) {
                        cond = 5;
                    }
                }

            Koefficient k4 = new Koefficient("Наличие \"климат-контроля\"", climate, "Постоянный (при наличии)");
            Koefficient k5 = new Koefficient("Наличие кондиционера", cond, Period.SUMMER.getName());

            koefficients.add(k1);
            koefficients.add(k2);
            koefficients.add(k3);
            koefficients.add(k4);
            koefficients.add(k5);
            return koefficients;
        }

//    public Auto(BlockedReportData blockedReportData){
//
//
////        this.brand = blockedReportData.getBrand();
////        this.number = blockedReportData.getNumber();
////        this.year = blockedReportData.getAutoYear();
////        this.bodyType = blockedReportData.getBodyType();
////        this.engineVolume = blockedReportData.getEngineVolume();
////        this.enginePower = blockedReportData.getEnginePower();
////        this.transmission = blockedReportData.getTransmission();
////        this.fuelType = blockedReportData.getFuelType();
////        this.climateMachine = blockedReportData.getClimateMachine();
////        this.stsFileName = blockedReportData.getStsResource();
////        this.osagoFileName = blockedReportData.getOsagoResource();
////        this.link = blockedReportData.getLink();
////        this.linkNorm = blockedReportData.getLinkNorm();
//        this.reports = null;
//        this.changesLogs = null;
//
//
//
//    }


    public String getStsNumber() {
        return stsNumber;
    }

    public void setStsNumber(String stsNumber) {
        this.stsNumber = stsNumber;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + super.getId() +
                ", brand='" + brand + '\'' +
                ", number='" + number + '\'' +
                ", year=" + year +
                ", bodyType='" + bodyType + '\'' +
                ", engineVolume=" + engineVolume +
                ", enginePower=" + enginePower +
                ", transmission='" + transmission + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", climateMachine='" + climateMachine + '\'' +
                ", stsFileName='" + stsFileName + '\'' +
                ", osagoFileName='" + osagoFileName + '\'' +
                ", link='" + link + '\'' +
                ", linkNorm=" + linkNorm +

                ", osagoDate='" + osagoDate + '\'' +

                '}';
    }

    @Override
    public String getDateForColor() {
        return osagoDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auto)) return false;

        Auto auto = (Auto) o;

        if (getId() != auto.getId()) return false;
        if (getBrand() != null ? !getBrand().equals(auto.getBrand()) : auto.getBrand() != null) return false;
        if (getNumber() != null ? !getNumber().equals(auto.getNumber()) : auto.getNumber() != null) return false;
        if (getYear() != null ? !getYear().equals(auto.getYear()) : auto.getYear() != null) return false;
        if (getBodyType() != null ? !getBodyType().equals(auto.getBodyType()) : auto.getBodyType() != null)
            return false;
        if (getEngineVolume() != null ? !getEngineVolume().equals(auto.getEngineVolume()) : auto.getEngineVolume() != null)
            return false;
        if (getEnginePower() != null ? !getEnginePower().equals(auto.getEnginePower()) : auto.getEnginePower() != null)
            return false;
        if (getTransmission() != null ? !getTransmission().equals(auto.getTransmission()) : auto.getTransmission() != null)
            return false;
        if (getFuelType() != null ? !getFuelType().equals(auto.getFuelType()) : auto.getFuelType() != null)
            return false;
        if (getClimateMachine() != null ? !getClimateMachine().equals(auto.getClimateMachine()) : auto.getClimateMachine() != null)
            return false;
        if (getStsFileName() != null ? !getStsFileName().equals(auto.getStsFileName()) : auto.getStsFileName() != null)
            return false;
        if (getOsagoFileName() != null ? !getOsagoFileName().equals(auto.getOsagoFileName()) : auto.getOsagoFileName() != null)
            return false;
        if (getLink() != null ? !getLink().equals(auto.getLink()) : auto.getLink() != null) return false;
        if (getLinkNorm() != null ? !getLinkNorm().equals(auto.getLinkNorm()) : auto.getLinkNorm() != null)
            return false;
        return !(getOsagoDate() != null ? !getOsagoDate().equals(auto.getOsagoDate()) : auto.getOsagoDate() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getYear() != null ? getYear().hashCode() : 0);
        result = 31 * result + (getBodyType() != null ? getBodyType().hashCode() : 0);
        result = 31 * result + (getEngineVolume() != null ? getEngineVolume().hashCode() : 0);
        result = 31 * result + (getEnginePower() != null ? getEnginePower().hashCode() : 0);
        result = 31 * result + (getTransmission() != null ? getTransmission().hashCode() : 0);
        result = 31 * result + (getFuelType() != null ? getFuelType().hashCode() : 0);
        result = 31 * result + (getClimateMachine() != null ? getClimateMachine().hashCode() : 0);
        result = 31 * result + (getStsFileName() != null ? getStsFileName().hashCode() : 0);
        result = 31 * result + (getOsagoFileName() != null ? getOsagoFileName().hashCode() : 0);
        result = 31 * result + (getLink() != null ? getLink().hashCode() : 0);
        result = 31 * result + (getLinkNorm() != null ? getLinkNorm().hashCode() : 0);
        result = 31 * result + (getOsagoDate() != null ? getOsagoDate().hashCode() : 0);
        return result;
    }
}
