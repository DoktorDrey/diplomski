package com.diplomski.katedra.pages.admin;


import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.SelectModelFactory;
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
    private SelectModel predmetSelectModel;

    @Property
    private int year;

    @Inject
    @Property
    private PredmetEncoder predmetEncoder;

    @Property
    private Predmet selectedPredmet;

    @Inject
    SelectModelFactory selectModelFactory;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private List<StudentAktivnostAss> activities;

    @Property
    @Persist
    private StudentAktivnostAss activity;

    @Inject
    private Messages messages;

    @Inject
    private AdminService adminService;

    @InjectComponent
    private Zone activityZone;

    void setupRender() {
        List<Predmet> predmets = adminService.findAllPredmets();
        predmetSelectModel = selectModelFactory.create(predmets, "name");

        // Get all persons - ask business service to find them (from the database)
//        logger.debug(activities.get(0).getBrojPoena());
//        logger.debug(activities.get(0).getStudent());
    }

    public List<Integer> getYears() {
        return adminService.getYears();
    }

    @SuppressWarnings("unchecked")
    @OnEvent(value="submit", component="filterForm")
    Object onSearchSubmit() throws Exception{
        logger.debug("tests");
        logger.debug(selectedPredmet.getId());
        logger.debug(year);
        activities = adminService.getStudentActivities(1, 2015);
        logger.debug(activities.toString());
        logger.debug(((StudentAktivnostAss) activities.get(0)).getAktivnost());
        logger.debug(((StudentAktivnostAss) activities.get(0)).getStudent().getBrojIndeksa());
        return activityZone.getBody();
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
