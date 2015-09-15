package com.diplomski.katedra.pages;

import com.diplomski.katedra.services.app.MainService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by andrija on 8/8/15.
 */
public class Activation {
    private static final Logger logger = Logger.getLogger(Activation.class);

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String poruka;

    @ActivationRequestParameter(value = "token")
    private String token;

    @Inject
    private MainService mainService;

    Object onActivate() {
        logger.debug(token);

        if (mainService.aktivacijaKorisnika(token))
            poruka = "Uspesno ste se aktivirali.";
        else
            poruka = "Link nije validan";
        return null;
    }

}
