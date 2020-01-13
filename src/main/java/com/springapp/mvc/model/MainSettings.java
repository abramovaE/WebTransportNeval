package com.springapp.mvc.model;

import javax.persistence.*;


/**
 * Created by oem on 10.10.18.
 */

@Entity
@Table(name = "setting")
public class MainSettings extends Model{



    @ManyToOne(optional = true)
    @JoinColumn(name = "techOfficeId")
    private AdressesCoordinates techOffice;
    @Transient
    private String techOfficeAdress;


    @ManyToOne(optional = true)
    @JoinColumn(name = "officialOfficeId")
    private AdressesCoordinates officialOffice;
    @Transient
    private String officialOfficeAdress;


    @ManyToOne(optional = true)
    @JoinColumn(name = "genDirId")
    private User genDir;
    @Transient
    private String genDirUsername;

    @ManyToOne(optional = true)
    @JoinColumn(name = "finDirId")
    private User finDir;
    @Transient
    private String finDirUsername;

    @ManyToOne(optional = true)
    @JoinColumn(name = "glavBuhId")
    private User glavBuh;
    @Transient
    private String glavBuhUsername;

    @Column(name = "emailAdress")
    private String emailAdress;

    @Column(name = "emailPassword")
    private String emailPassword;

    @Column(name = "devEmailAdress")
    private String devEmailAdress;

    public MainSettings() {
        super();
    }



    public AdressesCoordinates getTechOffice() {
        return techOffice;
    }

    public void setTechOffice(AdressesCoordinates techOffice) {
        this.techOffice = techOffice;
    }


    public AdressesCoordinates getOfficialOffice() {
        return officialOffice;
    }

    public void setOfficialOffice(AdressesCoordinates officialOffice) {
        this.officialOffice = officialOffice;
    }

    public User getGenDir() {
        return genDir;
    }

    public void setGenDir(User genDir) {
        this.genDir = genDir;
    }

    public String getGenDirUsername() {
        return genDirUsername;
    }

    public void setGenDirUsername(String genDirUsername) {
        this.genDirUsername = genDirUsername;
    }


    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getDevEmailAdress() {
        return devEmailAdress;
    }

    public void setDevEmailAdress(String devEmailAdress) {
        this.devEmailAdress = devEmailAdress;
    }

    public String getTechOfficeAdress() {
        return techOfficeAdress;
    }

    public void setTechOfficeAdress(String techOfficeAdress) {
        this.techOfficeAdress = techOfficeAdress;
    }

    public String getOfficialOfficeAdress() {
        return officialOfficeAdress;
    }

    public void setOfficialOfficeAdress(String officialOfficeAdress) {
        this.officialOfficeAdress = officialOfficeAdress;
    }

    public User getFinDir() {
        return finDir;
    }

    public void setFinDir(User finDir) {
        this.finDir = finDir;
    }

    public String getFinDirUsername() {
        return finDirUsername;
    }

    public void setFinDirUsername(String finDirUsername) {
        this.finDirUsername = finDirUsername;
    }

    public User getGlavBuh() {
        return glavBuh;
    }

    public void setGlavBuh(User glavBuh) {
        this.glavBuh = glavBuh;
    }

    public String getGlavBuhUsername() {
        return glavBuhUsername;
    }

    public void setGlavBuhUsername(String glavBuhUsername) {
        this.glavBuhUsername = glavBuhUsername;
    }

}
