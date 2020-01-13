package com.springapp.mvc.model;

import com.springapp.mvc.fns.Item;
import com.springapp.mvc.model.checks.Receipt;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kot on 05.07.17.
 */

@Entity
@Table(name = "day")
public class Day extends Model {

//    @Column(name = "date")
    private String date;

    @Column(name = "dayNumber")
    private Integer dayNumber;

    @Column(name = "summ")
    private Double summ;

    @Column(name = "costByLiter")
    private Double costByLiter;

//    в метрах для новых расчетов
    @Column(name = "dayDistance")
    private int dayDistance;

    @Transient
    private int prevFillingDistance;

    @ManyToOne(optional = true)
    @JoinColumn(name = "report_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Report report;


    @OneToMany(mappedBy = "day", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Point> points;


    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Receipt> receipts;

    @Transient
    private List<DepartmentTrip> departmentTrip;


    @Column(name = "shipping")
    private boolean shipping;

    public List<DepartmentTrip> getDepartmentTrip() {
        return departmentTrip;
    }

    public void setDepartmentTrip(List<DepartmentTrip> departmentTrip) {
        this.departmentTrip = departmentTrip;
    }



    public Day() {
        super();
    }


    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }


    public String getDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.of(report.getYear(), report.getMonthNumber(), dayNumber).format(dateTimeFormatter);
    }
////
//    public void setDate(String date) {
//        this.date = date;
//    }

    public Double getSumm() {

        if(receipts != null && !receipts.isEmpty()){
            int sum = 0;
            for(Receipt receipt: receipts){
                        sum += receipt.getTotalSum();
            }
            return sum/100d;
        }
        return summ;
    }

    public boolean getShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    public Double getCostByLiter() {

        if(receipts != null && !receipts.isEmpty()){
            int cost = 0;
            for(Receipt receipt: receipts){
                com.springapp.mvc.model.checks.Item item = receipt.getItems().get(0);
//                List<com.springapp.mvc.model.checks.Item> items = receipt.getItems();
//                if(items != null && !items.isEmpty()){
//                    for(com.springapp.mvc.model.checks.Item item: items){
                        cost += item.getPrice();
//                    }
//                }
            }
            return cost/100d;
        }


        return costByLiter;
    }

    public void setCostByLiter(Double costByLiter) {
        this.costByLiter = costByLiter;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<Point> getPoints() {
        if(points != null && !points.isEmpty()) {
            Collections.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.getPosition() - o2.getPosition();
                }
            });
        }
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public int getDayDistance() {
        return dayDistance;
    }

    public void setDayDistance(int dayDistance) {
        this.dayDistance = dayDistance;
    }


    public int getPrevFillingDistance() {
        return prevFillingDistance;
    }

    public void setPrevFillingDistance(int prevFillingDistance) {
        this.prevFillingDistance = prevFillingDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;

        Day day = (Day) o;

        if (getId() != day.getId()) return false;
        if (getDayNumber() != day.getDayNumber()) return false;
        if (getDayDistance() != day.getDayDistance()) return false;
        if (getSumm() != null ? !getSumm().equals(day.getSumm()) : day.getSumm() != null) return false;
        return !(getCostByLiter() != null ? !getCostByLiter().equals(day.getCostByLiter()) : day.getCostByLiter() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getDayNumber();
        result = 31 * result + (getSumm() != null ? getSumm().hashCode() : 0);
        result = 31 * result + (getCostByLiter() != null ? getCostByLiter().hashCode() : 0);
        result = 31 * result + getDayDistance();
        return result;
    }


    @Override
    public String toString() {
        return "Day{" +
                "id=" + super.getId() +
                ", summ=" + summ +
                ", costByLiter=" + costByLiter +
                ", dayDistance=" + dayDistance +
                ", dayNumber=" + dayNumber +
                '}';
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
