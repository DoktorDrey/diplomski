package com.diplomski.katedra.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Entity
public class Student {
    private String brojIndeksa;
    private String ime;
    private String prezime;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (brojIndeksa != null ? !brojIndeksa.equals(student.brojIndeksa) : student.brojIndeksa != null) return false;
        if (ime != null ? !ime.equals(student.ime) : student.ime != null) return false;
        if (prezime != null ? !prezime.equals(student.prezime) : student.prezime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brojIndeksa != null ? brojIndeksa.hashCode() : 0;
        result = 31 * result + (ime != null ? ime.hashCode() : 0);
        result = 31 * result + (prezime != null ? prezime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student: " + brojIndeksa;
    }
}
