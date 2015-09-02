package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.pages.admin.Home;
import com.diplomski.katedra.services.app.MainService;
import com.diplomski.katedra.services.mail.MailService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by andrija on 8/8/15.
 */
public class EditovanjePodataka {
    private static final Logger logger = Logger.getLogger(Registration.class);

    @InjectComponent
    private Form myProfileForm;

    @SessionState(create = false)
    @Property
    private Student student;

    @Inject
    private MainService mainService;

    @Inject
    private Messages messages;

    Object onSuccess() {
        return this;
    }

    void onValidateFromMyProfileForm(){
        logger.debug(student.getBrojIndeksa());
        Class nextPage = null;
        try {
            mainService.updateStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            myProfileForm.recordError(messages.get("student-not-exist"));
        }
//        return null;
    }
    Object onActivate() {
        if(student != null)
            return null;
        return Index.class;
    }
}
