package com.diplomski.katedra.pages.admin;


import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.ImportJQueryUI;

import java.util.List;

/**
 * Created by andrija on 8/10/15.
 */
@Import(stylesheet = { "context:resources/css/jquerydatatables.css" })
@ImportJQueryUI(theme = "context:resources/css/jquery-ui-1.8.23.custom.css")
public class PrikazAktivnosti {

    private static final Logger logger = Logger.getLogger(PrikazAktivnosti.class);

    @SessionState(create=false)
    private Predavac predavac;

    @Property
    private List<StudentAktivnostAss> activities;

    @Property
    private StudentAktivnostAss activity;

    @Inject
    private Messages messages;

    @Inject
    private AdminService adminService;

    void setupRender() {
        // Get all persons - ask business service to find them (from the database)
        activities = adminService.getStudentActivities(1, 2015);
        logger.debug(activities.toString());
        logger.debug(((StudentAktivnostAss) activities.get(0)).getAktivnost());
//        logger.debug(activities.get(0).getBrojPoena());
//        logger.debug(activities.get(0).getStudent());
    }

    public JSONObject getOptions() {

        // The available options are documented at http://www.datatables.net/ref

        JSONObject options = new JSONObject();
        options.put("bJQueryUI", "true");
        return options;
    }
    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
