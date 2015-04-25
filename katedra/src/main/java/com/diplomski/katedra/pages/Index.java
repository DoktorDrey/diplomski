package com.diplomski.katedra.pages;

import java.util.Date;

import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.services.app.MainService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.corelib.components.*;
import org.apache.tapestry5.SymbolConstants;

/**
 * Start pages of application katedra.
 */
public class Index
{
    private static final Logger logger = Logger.getLogger(Index.class);

    @Property
    @Inject
    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    private String tapestryVersion;


    @Property
    private Student student;
    /*@Property
    private String userName;
    @Property
    private String password;*/

    @InjectComponent
    private Form loginForm;

    @Inject
    private MainService mainService;

    /*@SessionState
    private Student authenticatedUser;*/

    @Inject
    private Messages messages;

    @SessionState(create = false)
    private Student loggedIn;

    void onPrepare() {
        logger.debug("on prepare");
        student = new Student();
    }

    void onValidateFromLoginForm() {
        logger.debug("validate");
        if (loginForm.isValid()) {
            Student authenticated = null;
            try {
                authenticated = mainService.authenticate(
                        student.getUsername(), student.getPassword());
            } catch (Exception e) {
                loginForm.recordError(messages.get("invalid-usernameor-password"));
            }

            loggedIn = authenticated;
        }
    }

    Object onSuccess() {
        logger.debug("on success");
        return About.class;
    }

   /* Object onSubmitFromLoginForm() {
        logger.debug("onSubmitFromLoginForm");
        logger.debug(student.getUsername());
        logger.debug(student.getPassword());
        Class nextPage = null;
        try {
            authenticatedUser = mainService.authenticate(student.getUsername(), student.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug(authenticatedUser);
        if (authenticatedUser == null) {
            return this;
        }
        return About.class;
    }*/
    public Date getCurrentTime()
    {
        return new Date();
    }
}
