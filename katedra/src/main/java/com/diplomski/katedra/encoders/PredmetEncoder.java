package com.diplomski.katedra.encoders;

import com.diplomski.katedra.db.dao.PredmetDao;
import com.diplomski.katedra.db.model.Predmet;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andrija on 7/29/15.
 */

public class PredmetEncoder implements ValueEncoder<Predmet>, ValueEncoderFactory<Predmet> {
    private static final Logger logger = Logger.getLogger(PredmetEncoder.class);

    @Override
    public String toClient(Predmet predmet) {
        return String.valueOf(predmet.getId());
    }

    @Override
    public Predmet toValue(String s) {
        Predmet predmet = new Predmet();
        predmet.setId(Integer.parseInt(s));
//        return predmetDao.promeni(Integer.parseInt(s));
        return predmet;
    }

    @Override
    public ValueEncoder<Predmet> create(Class<Predmet> predmetClass) {
        return this;
    }
}
