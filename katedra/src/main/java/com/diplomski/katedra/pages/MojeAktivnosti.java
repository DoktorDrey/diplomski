package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.db.model.StudentAktivnostAss;
import com.diplomski.katedra.services.app.MainService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.ImportJQueryUI;

import java.util.List;

/**
 * Created by Andrija Ilic on 8/15/2015.
 */
@Import(stylesheet = { "context:resources/css/jquerydatatables.css" })
@ImportJQueryUI(theme = "context:resources/css/jquery-ui-1.8.23.custom.css")
public class MojeAktivnosti {
    private static final Logger logger = Logger.getLogger(MojeAktivnosti.class);


    @SessionState(create = false)
    @Property
    private Student student;

    @Property
    private String aktStatus;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private List<StudentAktivnostAss> activities;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private List<Aktivnost> activities1;

    @Property
    @Persist
    private StudentAktivnostAss activity;

    @Property
    @Persist
    private Aktivnost activity1;

    @Inject
    private MainService mainService;

    @InjectComponent
    private Zone activityZone;

    @InjectComponent
    private Zone activityZone1;


    @SuppressWarnings("unchecked")
    @OnEvent(value="submit", component="filterForm")
    Object onSearchSubmit() throws Exception{
        if(aktStatus.equals("Izvrsene")) {
            activities = mainService.getStudentActivities(student);
            if(activities != null)
                return activityZone.getBody();
        } else if(aktStatus.equals("Neizvrsene")) {
            activities1 = mainService.getFutureActivities(student);
            Aktivnost aktivnost = activities1.get(0);
            logger.debug(aktivnost.getProgram());
            if(activities1 != null)
                return activityZone1.getBody();
        }
        return null;
    }

   /* Object setupRender() {
        activities = mainService.getStudentActivities(student);
        return activityZone.getBody();
    }
*/
    public JSONObject getOptions() {

        // The available options are documented at http://www.datatables.net/ref

        JSONObject options = new JSONObject();
        options.put("bJQueryUI", "true");
        return options;
    }

    Object onActivate() {
        if(student != null){
            return null;
        }
        return Index.class;
    }
}
