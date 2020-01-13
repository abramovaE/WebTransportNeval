package com.springapp.mvc.model.checks;

import com.springapp.mvc.model.Model;
import com.springapp.mvc.model.User;
import org.hibernate.annotations.GeneratorType;


import javax.persistence.*;

/**
 * Created by oem on 28.11.18.
 */
@Entity
@Table(name = "qr")
public class JSONCheck extends Model {


    @Column(name = "qr_code")
    private String qr_code;

    @Column(name = "login")
    private String login;

    public JSONCheck() {
        super();
    }


    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
