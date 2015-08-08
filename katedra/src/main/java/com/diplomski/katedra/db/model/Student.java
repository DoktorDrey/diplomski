package com.diplomski.katedra.db.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by andrija on 5/14/15.
 */
@Entity
@Table(name = "student", schema = "", catalog = "katedra")
public class Student {
    private Long id;
    private String email;
    private String password;
    private String brojIndeksa;
    private String ime;
    private String prezime;
    private boolean activated;
    private String token;

    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "broj_indeksa")
    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    @Basic
    @Column(name = "ime")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Basic
    @Column(name = "prezime")
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Basic
    @Column(name = "activated")
    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }


    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        if (id != that.id) return false;
        if (brojIndeksa != null ? !brojIndeksa.equals(that.brojIndeksa) : that.brojIndeksa != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (ime != null ? !ime.equals(that.ime) : that.ime != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (prezime != null ? !prezime.equals(that.prezime) : that.prezime != null) return false;

        return true;
    }
}
