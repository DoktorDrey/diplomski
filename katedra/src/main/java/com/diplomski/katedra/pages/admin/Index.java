package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
@Import(stylesheet = "context:resources/css/admin_global.css")
public class Index {
    @Persist
    @Property
    private String userName;

    @Property
    private String password;

    @Inject
    private AdminService adminService;

    @SessionState(create = false)
    private Predavac predavac;

    Object onSubmitFromLoginForm() {
        try {
            predavac = adminService.authenticate(userName, password);
            return Home.class;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    Object onActivate() {
        if(predavac != null)
            return Home.class;
        return null;
    }
}
