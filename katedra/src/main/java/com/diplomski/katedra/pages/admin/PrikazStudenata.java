package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.StudentPredmetAss;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import java.util.List;

/**
 * Created by andrija on 7/28/15.
 */
public class PrikazStudenata {
    private static final Logger logger = Logger.getLogger(PrikazStudenata.class);


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

    @Property
    private StudentPredmetAss currentStudent;

    @Inject
    SelectModelFactory selectModelFactory;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private List<StudentPredmetAss> students;

    @Inject
    private AdminService adminService;

    public List<Integer> getYears() {
        return adminService.getYears();
    }

    void setupRender() {
        List<Predmet> predmets = adminService.findAllPredmetsForPredavac(predavac);
        predmetSelectModel = selectModelFactory.create(predmets, "name");
    }

    @SuppressWarnings("unchecked")
    @OnEvent(value="submit", component="filterForm")
    void onSearchSubmit() throws Exception{
        logger.debug("tests");
        logger.debug(selectedPredmet.getId());
        logger.debug(year);
        students = adminService.findAllStudentsInfo(selectedPredmet.getId(), year);
    }

    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
