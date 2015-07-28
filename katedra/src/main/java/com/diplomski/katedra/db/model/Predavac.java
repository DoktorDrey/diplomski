package com.diplomski.katedra.db.model;

import javax.persistence.*;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
@Table(name = "predavac", schema = "", catalog = "katedra")
public class Predavac {
    private int id;
    private String ime;
    private String prezime;
    private Integer titula;
    private String username;
    private String password;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "titula")
    public Integer getTitula() {
        return titula;
    }

    public void setTitula(Integer titula) {
        this.titula = titula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predavac predavac = (Predavac) o;

        if (id != predavac.id) return false;
        if (ime != null ? !ime.equals(predavac.ime) : predavac.ime != null) return false;
        if (prezime != null ? !prezime.equals(predavac.prezime) : predavac.prezime != null) return false;
        if (titula != null ? !titula.equals(predavac.titula) : predavac.titula != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ime != null ? ime.hashCode() : 0);
        result = 31 * result + (prezime != null ? prezime.hashCode() : 0);
        result = 31 * result + (titula != null ? titula.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
