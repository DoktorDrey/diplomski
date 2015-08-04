package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.SelectModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrija on 7/28/15.
 */
public class PrikazStudenata {
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
    private Student currentStudent;

    @Property
    private int currentIndex;

    @Inject
    SelectModelFactory selectModelFactory;

    @Property
    private ArrayList<Student> students;

    @Inject
    private AdminService adminService;

    @Inject
    private BeanModelSource _beanModelSource;

    @Inject
    private ComponentResources _componentResources;

    void setupRender() {
        // invoke my service to find all colors, e.g. in the database
        List<Predmet> predmets = adminService.findAllPredmets();

        // create a SelectModel from my list of colors
        predmetSelectModel = selectModelFactory.create(predmets, "name");
    }

    public List<Integer> getYears() {
        return adminService.getYears();
    }

    public BeanModel getMyModel(){
        BeanModel myModel = _beanModelSource.createDisplayModel(Student.class,
                _componentResources.getMessages());
        /*myModel.add("action", null);
        myModel.include("firstName", "lastName", "action");
        myModel.get("firstName").sortable(false);
        myModel.get("lastName").label("Surname");*/
        return myModel;
    }

   /* void onSuccess()
    {
        BeanModel myModel = _beanModelSource.createDisplayModel(Student.class,
                _componentResources.getMessages());
        myModel.add("action", null);
        myModel.include("firstName", "lastName", "action");
        myModel.get("firstName").sortable(false);
        myModel.get("lastName").label("Surname");
    }*/

    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
