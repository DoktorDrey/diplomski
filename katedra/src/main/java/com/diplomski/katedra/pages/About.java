package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Student;
import org.apache.tapestry5.annotations.SessionState;

public class About
{

    @SessionState(create=false)
    private Student student;

    Object onActivate() {
        if(student != null)
            return null;
        return Index.class;
    }
}
