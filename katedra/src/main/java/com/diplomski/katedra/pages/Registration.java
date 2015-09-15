/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.services.app.MainService;
import com.diplomski.katedra.services.mail.MailService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author PC
 */
public class Registration {

    private static final Logger logger = Logger.getLogger(Registration.class);

    @InjectComponent
    private Form registrationForm;

    @Property
    private Student student;

    @Inject
    private MainService mainService;

    @Inject
    private MailService mailService;

    @Inject
    private Messages messages;

    void onPrepare() {
        logger.debug("on prepare");
        student = new Student();
    }

    Object onSuccess() {
        logger.debug("onSuccess");
        String body = messages.get("activation-message");
        body += "<br/> http://localhost:8081/activation/?token=";
        body += student.getToken();
        logger.debug(body);
        mailService.sendMail(student.getEmail(), body);
        return About.class;
    }

    void onValidateFromRegistrationForm(){
        logger.debug("onSubmitFromRegistrationForm");
        logger.debug(student.getBrojIndeksa());
        Class nextPage = null;
        try {
            mainService.registracijaKorisnika(student);
        } catch (Exception e) {
            e.printStackTrace();
            registrationForm.recordError(messages.get("student-not-exist"));
        }
//        return null;
    }

}
