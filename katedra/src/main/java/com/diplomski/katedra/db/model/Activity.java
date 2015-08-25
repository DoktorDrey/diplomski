package com.diplomski.katedra.db.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by andrija on 8/25/15.
 */
public class Activity implements Serializable{
    private Date datum;
    private Aktivnost aktivnost;
    private String satnica;

    private static final Logger logger = Logger.getLogger(Activity.class);

    public String getSatnica() {
        return satnica;
    }

    public void setSatnica(String satinca) {
        this.satnica = satinca;
    }

    public Aktivnost getAktivnost() {
        return aktivnost;
    }

    public void setAktivnost(Aktivnost aktivnost) {
        this.aktivnost = aktivnost;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        logger.debug(datum);
        this.datum = datum;
    }

    public Activity(Date datum, Aktivnost aktivnost, String satnica) {
        this.datum = datum;
        logger.debug(datum);
        this.aktivnost = aktivnost;
        this.satnica = satnica;
    }
}
