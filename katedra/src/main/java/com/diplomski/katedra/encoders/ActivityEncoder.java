package com.diplomski.katedra.encoders;

import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Predmet;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.services.ValueEncoderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by andrija on 7/29/15.
 */
@Component
public class ActivityEncoder implements ValueEncoder<Aktivnost>, ValueEncoderFactory<Aktivnost> {
    private static final Logger logger = Logger.getLogger(ActivityEncoder.class);

    @Override
    public String toClient(Aktivnost aktivnost) {
        return String.valueOf(aktivnost.getId());
    }

    @Override
    public Aktivnost toValue(String s) {
        Aktivnost aktivnost = new Aktivnost();
        aktivnost.setId(Integer.parseInt(s));
        return aktivnost;
    }

    @Override
    public ValueEncoder<Aktivnost> create(Class<Aktivnost> aktivnostClass) {
        return this;
    }

/*
    @Override
    public String toClient(Predmet predmet) {
        return String.valueOf(predmet.getId());
    }

    @Override
    public Predmet toValue(String s) {
        Predmet predmet = new Predmet();
        predmet.setId(Integer.parseInt(s));
//        return predmetDao.find(Integer.parseInt(s));
        return predmet;
    }

    @Override
    public ValueEncoder<Predmet> create(Class<Predmet> predmetClass) {
        return this;
    }
*/
}
