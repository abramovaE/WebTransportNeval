package com.springapp.mvc.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kot on 10.07.17.
 */

@Entity
@Table(name = "address")
public class AdressesCoordinates extends Model {



    @Column(name = "address")
    private String address;

    @Column(name = "coordinates")
    private String coordinates;


    @Column(name = "numberOfZone")
    private Integer numberOfZone;


    @Column (name = "usedForAutoReports")
    private boolean usedForAutoReports;

    @OneToMany(mappedBy = "departure")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Point> depPoints;


    @OneToMany(mappedBy = "arrival")
//    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Point> arrPoints;

    @OneToMany(mappedBy = "techOffice")
    private List<MainSettings> mainSettings;


    @OneToMany(mappedBy = "officialOffice")
    private List<MainSettings> officialOffice;



    public AdressesCoordinates() {
        super();
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getNumberOfZone() {
        return numberOfZone;
    }

    public void setNumberOfZone(Integer numberOfZone) {
        this.numberOfZone = numberOfZone;
    }

    public List<Point> getDepPoints() {
        return depPoints;
    }

    public void setDepPoints(List<Point> depPoints) {
        this.depPoints = depPoints;
    }

    public List<Point> getArrPoints() {
        return arrPoints;
    }

    public void setArrPoints(List<Point> arrPoints) {
        this.arrPoints = arrPoints;
    }


    public boolean isUsedForAutoReports() {
        return usedForAutoReports;
    }

    public void setUsedForAutoReports(boolean usedForAutoReports) {
        this.usedForAutoReports = usedForAutoReports;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdressesCoordinates)) return false;

        AdressesCoordinates that = (AdressesCoordinates) o;

        return getId() == that.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    public List<MainSettings> getMainSettings() {
        return mainSettings;
    }

    public void setMainSettings(List<MainSettings> mainSettings) {
        this.mainSettings = mainSettings;
    }


    public List<MainSettings> getOfficialOffice() {
        return officialOffice;
    }

    public void setOfficialOffice(List<MainSettings> officialOffice) {
        this.officialOffice = officialOffice;
    }
}
