package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.StudentPredmetAss;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.SelectModelFactory;

import java.util.ArrayList;
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
    @Persist
    private StudentPredmetAss currentStudent;

    @Inject
    SelectModelFactory selectModelFactory;

    @Property
    @Persist
    private List<StudentPredmetAss> students;

    @Inject
    private AdminService adminService;

    @InjectComponent
    private Zone studentZone;

    @Component
    private Zone detailZone;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String poruka;

    @Inject
    private Request request;

    @Property
    private int currentIndex;

    @Inject
    private AssetSource assetSource;

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
        selectedPredmet = predmet;
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
        logger.debug(year);
        logger.debug(year);
        logger.debug(year != 0 && selectedPredmet != null && students != null && !students.isEmpty());
        logger.debug(currentStudent);
        return year != 0 && selectedPredmet != null && students != null && !students.isEmpty();
    }

    @OnEvent(value = "action")
    Object showDetail(int index)
    {
        logger.debug(index);
        logger.debug(students.toString());
        if (!request.isXHR()) { return this; }
            currentStudent= (StudentPredmetAss)students.get(index);
        return detailZone;
    }

    public JSONObject getDialogParam()
    {
        JSONObject param = new JSONObject();
        param.put("width", 600);
        return param;
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
    public Asset getImageURL() {
        logger.debug(assetSource.getClass().getResource("./"));
        final String path = "/layout/images/" + currentStudent.getStudentId().getImageName();
        return assetSource.getContextAsset(path, null);
    }

    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
