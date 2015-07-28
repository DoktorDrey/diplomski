package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Student;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import java.util.ArrayList;

/**
 * Created by andrija on 7/28/15.
 */
public class PrikazStudenata {
    @SessionState(create=false)
    private Predavac predavac;

    @Property
    private ArrayList<Student> students;

    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
