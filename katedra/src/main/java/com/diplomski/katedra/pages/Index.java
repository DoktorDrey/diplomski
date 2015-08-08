package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.services.app.MainService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Date;

/**
 * Start pages of application katedra.
 */
public class Index
{
    private static final Logger logger = Logger.getLogger(Index.class);

    @Property
    private Student student;

    @InjectComponent
    private Form loginForm;

    @Inject
    private MainService mainService;

    @Inject
    private Messages messages;

    @SessionState(create = false)
    private Student loggedIn;

    void onPrepare() {
        logger.debug("on prepare");
        logger.debug(loggedIn);
        logger.debug(student);
        student = new Student();
    }

    void onValidateFromLoginForm() {
        if (loginForm.isValid()) {
            try {
                loggedIn = mainService.authenticate(
                        student.getEmail(), student.getPassword());
            } catch (Exception e) {
                loginForm.recordError(messages.get("invalid-usernameor-password"));
            }
        }
    }

    Object onSuccess() {
        logger.debug("on success");
        return About.class;
    }

    public Date getCurrentTime()
    {
        return new Date();
    }

    Object onActivate() {
        if(loggedIn != null)
            return About.class;
        return null;
    }
}
