package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.StudentPredmetAss;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Zone;
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
    @Persist
    private int year;

    @Inject
    @Property
    private PredmetEncoder predmetEncoder;

    @Property
    @Persist
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

    @InjectComponent
    private Zone studentZone;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String poruka;

    public List<Integer> getYears() {
        return adminService.getYears();
    }

    void setupRender() {
        List<Predmet> predmets = adminService.findAllPredmetsForPredavac(predavac);
        predmetSelectModel = selectModelFactory.create(predmets, "name");
    }

    Object onValueChangedFromYear(int year) {
        logger.debug(year);
        logger.debug(this.year);
        this.year = year;
        logger.debug(this.selectedPredmet);
        if(this.selectedPredmet != null) {
            students = adminService.findAllStudentsInfo(selectedPredmet.getId(), year);
            poruka = "";
            if(students.isEmpty()) {
                poruka = "Nema pronadjenih rezultata za zadati kriterijum";
            }
            return studentZone.getBody();
        }
        return null;
    }

    Object onValueChangedFromPredmet(Predmet predmet) {
        logger.debug(predmet);
        logger.debug(year);
        selectedPredmet = predmet;
        logger.debug(selectedPredmet);
        if(year != 0) {
            students = adminService.findAllStudentsInfo(selectedPredmet.getId(), year);
            poruka = "";
            if(students.isEmpty()) {
                poruka = "Nema pronadjenih rezultata za zadati kriterijum";
            }
            return studentZone.getBody();
        }
        return null;
    }
    public boolean isShowStudents() {
        return year != 0 && selectedPredmet != null && students != null && !students.isEmpty();
    }


    /*@SuppressWarnings("unchecked")
    @OnEvent(value="submit", component="filterForm")
    void onSearchSubmit() throws Exception{
        logger.debug("tests");
        logger.debug(selectedPredmet.getId());
        logger.debug(year);
        students = adminService.findAllStudentsInfo(selectedPredmet.getId(), year);
    }
*/
    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
