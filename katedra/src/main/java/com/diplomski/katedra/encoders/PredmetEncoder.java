package com.diplomski.katedra.encoders;

import com.diplomski.katedra.db.dao.PredmetDao;
import com.diplomski.katedra.db.model.Predmet;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderFactory;

/**
 * Created by andrija on 7/29/15.
 */

public class PredmetEncoder implements ValueEncoder<Predmet>, ValueEncoderFactory<Predmet> {
    private static final Logger logger = Logger.getLogger(PredmetEncoder.class);

    @Inject
    private PredmetDao predmetDao;

    @Override
    public String toClient(Predmet predmet) {
        return String.valueOf(predmet.getId());
    }

    @Override
    public Predmet toValue(String s) {
        logger.debug("333333333333");
        logger.debug(predmetDao);
        Predmet predmet = new Predmet();
        predmet.setId(Integer.parseInt(s));
//        return predmetDao.find(Integer.parseInt(s));
        return predmet;
    }

    @Override
    public ValueEncoder<Predmet> create(Class<Predmet> predmetClass) {
        return this;
    }
}
