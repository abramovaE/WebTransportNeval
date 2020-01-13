package com.springapp.mvc.model;

import org.hibernate.annotations.*;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractObj{

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

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

    @Column(name = "eMail")
    private String eMail;

    @Column(name = "accountancyType")
    private String accountancyType;

    @Column(name = "isBlocked")
    private boolean blocked;

    @Column(name = "vuNumber")
    private String vuNumber;

    @Column(name = "vuCat")
    private String vuCat;

    //    коэффициент амортизации
    @Column(name = "amortizacia")
    private Double amortizacia;

    @OneToMany(mappedBy = "whoChanged")
    private List<ChangesLog> whochangedList;

    @OneToMany(mappedBy = "user")
    private List<Report> reports;

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Auto> autos;


    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Department department;

    @OneToMany(mappedBy = "user")
    private List<ChangesLog> changesLogs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Role> roles;

    @Column(name = "isBuhgalteria")
    private boolean buhgalteria;

    @Column(name = "isFired")
    private boolean fired;


    @OneToOne
    @JoinColumn(name = "currentAuto_id")
    @LazyCollection(LazyCollectionOption.FALSE)
//    @Transient
    private Auto currentAuto;


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Column(name="vuFileName")
    private String vuFileName;


    @Column(name = "vuDate")
    private String vuDate;

    @Transient
    private boolean depCheckBox;

    public boolean getDepCheckBox() {
        return depCheckBox;
    }

    public void setDepCheckBox(boolean depCheckBox) {
        this.depCheckBox = depCheckBox;
    }

    //
//    @Transient
//    private String colorVu;


//    public String getColorVU() {
//        if(vuDate != null && !vuDate.isEmpty()){
//            LocalDate nowDateTime = LocalDate.now();
//            String[] y_m_d = vuDate.split("-");
//            LocalDate vuLocalDate = LocalDate.of(Integer.parseInt(y_m_d[0]), Integer.parseInt(y_m_d[1]), Integer.parseInt(y_m_d[2]));
//            java.time.Period period = nowDateTime.until(vuLocalDate);
//            if(period.getYears() == 0 && period.getMonths() == 0){
//                return "red";
//            }
//            else if(period.getYears() == 0 && period.getMonths() < 2){
//                return "orange";
//            }
//            else {
//                return "green";
//            }
//        }
//        return "grey";
//    }












    //    Временно старые данные
    @Column(name = "year")
    private Integer year;

    @Column(name="auto")
    private String auto;

    @Column(name = "autoNumber")
    private String autoNumber;

    @Column(name="engineVolume")
    private Double engineVolume;

    @Column(name="transmission")
    private String transmission;

    @Column(name="bodyType")
    private String bodyType;

    @Column(name = "climateMachine")
    private String climateMachine;

    @Column(name = "enginePower")
    private Integer enginePower;

    @Column(name="stsResource")
    private String stsResource;

    @Column(name="osagoResource")
    private String osagoResource;

    @Column(name="fuelType")
    private String fuelType;

    @Column(name = "link")
    private String link;

    @Column(name="linkNorm")
    private Double linkNorm;






    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getAutoNumber() {
        return autoNumber;
    }

    public void setAutoNumber(String autoNumber) {
        this.autoNumber = autoNumber;
    }

    public Double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(Double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getClimateMachine() {
        return climateMachine;
    }

    public void setClimateMachine(String climateMachine) {
        this.climateMachine = climateMachine;
    }

    public Integer getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    public String getStsResource() {
        return stsResource;
    }

    public void setStsResource(String stsResource) {
        this.stsResource = stsResource;
    }

    public String getOsagoResource() {
        return osagoResource;
    }

    public void setOsagoResource(String osagoResource) {
        this.osagoResource = osagoResource;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
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
//
////временная заглушка
//    public List<Koefficient> getKoefficients(){
//        return null;
//    }
//
////временная заглушка
//    public Double getSummerNorm(){
//        return null;
//    }
//
////временная заглушка
//    public Double getWinterNorm(){
//        return null;
//    }
//




    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAccountancyType() {
        return accountancyType;
    }

    public void setAccountancyType(String accountancyType) {
        this.accountancyType = accountancyType;
    }


    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }



    public Double getAmortizacia() {
        return amortizacia;
    }

    public void setAmortizacia(Double amortizacia) {
        this.amortizacia = amortizacia;
    }


    public List<ChangesLog> getWhochangedList() {
        return whochangedList;
    }

    public void setWhochangedList(List<ChangesLog> whochangedList) {
        this.whochangedList = whochangedList;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public List<Auto> getAutos() {
        return autos;
    }

    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }

    public List<ChangesLog> getChangesLogs() {
        return changesLogs;
    }

    public void setChangesLogs(List<ChangesLog> changesLogs) {
        this.changesLogs = changesLogs;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getBuhgalteria() {
        return buhgalteria;
    }

    public void setBuhgalteria(boolean buhgalteria) {
        this.buhgalteria = buhgalteria;
    }

    public boolean getFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public Auto getCurrentAuto() {
        return currentAuto;
    }

    public void setCurrentAuto(Auto currentAuto) {
        this.currentAuto = currentAuto;
    }

    public String getVuFileName() {
        return vuFileName;
    }

    public void setVuFileName(String vuFileName) {
        this.vuFileName = vuFileName;
    }


    public String getVuDate() {
        return vuDate;
    }

    public void setVuDate(String vuDate) {
        this.vuDate = vuDate;
    }

    public User() {

        super();
    }


    public String getMol(){
        String mol = new String();
        if(post != null && surname != null && name != null && patronymic != null &&
                !post.isEmpty() && !surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty()) {
            mol = post + " " + surname + " " + name.substring(0, 1) + "." + patronymic.substring(0, 1) + ".";
        }
        return mol;
    }

    public String getShortFullName(){
        StringBuilder fullName = new StringBuilder();
        if(surname != null && !surname.isEmpty()){
            fullName.append(surname);
        }
        if(name != null && !name.isEmpty()) {
            fullName.append(" " + name.substring(0, 1) + ".");
        }
        if(patronymic != null && !patronymic.isEmpty()){
            fullName.append(" " + patronymic.substring(0, 1) + ".");
        }
        return fullName.toString();
    }

    public String getVuNumber() {
        return vuNumber;
    }

    public void setVuNumber(String vuNumber) {
        this.vuNumber = vuNumber;
    }

    public String getVuCat() {
        return vuCat;
    }

    public void setVuCat(String vuCat) {
        this.vuCat = vuCat;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", surname='" + surname + '\'' +
                ", post='" + post + '\'' +
                ", transponder='" + transponder + '\'' +
                ", eMail='" + eMail + '\'' +
                ", accountancyType='" + accountancyType + '\'' +
                ", blocked=" + blocked +
                ", amortizacia=" + amortizacia +
                ", curr=" + currentAuto +

//                ", whochangedList=" + whochangedList +
//                ", reports=" + reports +
                ", autos=" + autos +
//                ", changesLogs=" + changesLogs +
//                ", roles=" + roles +
//                ", buhgalteria=" + buhgalteria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null)
            return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getPatronymic() != null ? !getPatronymic().equals(user.getPatronymic()) : user.getPatronymic() != null)
            return false;
        return !(getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }


    public User(BlockedReportData blockedReportData) {
        this.login = blockedReportData.getLogin();
        this.name = blockedReportData.getName();
        this.patronymic = blockedReportData.getPatronymic();
        this.surname = blockedReportData.getSurname();
        this.post = blockedReportData.getPost();
        this.transponder = blockedReportData.getTransponder();
        this.accountancyType = blockedReportData.getAccountancyType();
        this.amortizacia = blockedReportData.getAmortizacia();

        this.password = null;
        this.eMail = null;
        this.blocked = false;
        this.whochangedList = null;
        this.reports = null;
        this.changesLogs = null;
        this.roles = null;
        this.autos = null;
    }

    @Override
    public String getDateForColor() {
        return vuDate;
    }

    public String getFiredColor(){
        if(fired){
            return "fired";
        }
        return "";
    }

    public String getButtonColor(){
        if(!fired){
            return "grey";
        }
        else {
            return "firedButton";
        }
    }
}