package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Student;
import org.apache.tapestry5.annotations.SessionState;

/**
 * Created by Andrija Ilic on 8/3/2014.
 */
public class Logout {
    @SessionState
    private Student loggedIn;

    Object onActivate() {
        loggedIn = null;
        return Index.class;
    }
}
