package com.springapp.mvc.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by kot on 27.07.17.
 */

@Entity
@Table(name = "point")
public class Point extends Model {

    @Transient
    private String depAdress;

    @Transient
    private String arrAdress;


    @Column(name = "costByZone")
    private Double costByZone;


    @Column(name = "factAdress")
    private String factAdress;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "zsd")
    private boolean zsd;

    @Column(name = "needChange")
    private boolean needChange;

    @Column(name = "isChanged")
    private boolean isChanged;




    @ManyToOne(optional = true)
    @JoinColumn(name = "day_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Day day;

    @ManyToOne(optional = true)
    @JoinColumn(name = "departure_id")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private AdressesCoordinates departure;

    @ManyToOne(optional = true)
    @JoinColumn(name = "arrival_id")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private AdressesCoordinates arrival;


    @Column(name = "position")
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Point() {
        super();
    }




    public String getDepAdress() {
        return depAdress;
    }

    public void setDepAdress(String depAdress) {
        this.depAdress = depAdress;
    }

    public String getArrAdress() {
        return arrAdress;
    }

    public void setArrAdress(String arrAdress) {
        this.arrAdress = arrAdress;
    }

    public Double getCostByZone() {
        return costByZone;
    }

    public void setCostByZone(Double costByZone) {
        this.costByZone = costByZone;
    }

    public String getFactAdress() {
        return factAdress;
    }

    public void setFactAdress(String factAdress) {
        this.factAdress = factAdress;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public boolean isZsd() {
        return zsd;
    }

    public void setZsd(boolean zsd) {
        this.zsd = zsd;
    }

    public AdressesCoordinates getDeparture() {
        return departure;
    }

    public void setDeparture(AdressesCoordinates departure) {
        this.departure = departure;
    }

    public AdressesCoordinates getArrival() {
        return arrival;
    }

    public void setArrival(AdressesCoordinates arrival) {
        this.arrival = arrival;
    }


    public boolean isNeedChange() {
        return needChange;
    }

    public void setNeedChange(boolean needChange) {
        this.needChange = needChange;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        return getId() == point.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

//    @Override
//    public String toString() {
//        return "Point{" +
//                "id=" + id +
//                ", depAdress='" + depAdress + '\'' +
//                ", arrAdress='" + arrAdress + '\'' +
//                ", costByZone=" + costByZone +
//                ", factAdress='" + factAdress + '\'' +
//                ", distance=" + distance +
//                ", zsd=" + zsd +
//                ", needChange=" + needChange +
//                ", isChanged=" + isChanged +
//                ", day=" + day +
//                ", departure=" + departure +
//                ", arrival=" + arrival +
//                '}';
//    }


//    @Override
//    public String toString() {
//        return "Point{" +
//                "id=" + id +
//                ", departure=" + departure.getId() +
//                ", arrival=" + arrival.getId() +
//                ", position=" + position +
////                ", day=" + day +
//                '}';
//    }

    public BigDecimal getExpensePrice(){
        BigDecimal priceForOneKm = new BigDecimal(this.getDay().getReport().getPriceForOneKm());
        BigDecimal dist = new BigDecimal(this.distance/1000d);
        BigDecimal expPrice = priceForOneKm.multiply(dist).setScale(2, RoundingMode.HALF_UP);
        return expPrice;
    }

    public String getIdColor(){
        if(isChanged){
            return "changed";
        }

        else if(needChange){
            return "needChange";
        }

        else {
            return "";
        }
    }
}
