package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import org.apache.tapestry5.annotations.SessionState;

/**
 * Created by Andrija Ilic on 8/3/2014.
 */
public class Logout {
    @SessionState
    private Predavac loggedIn;

    Object onActivate() {
        loggedIn = null;
        return Index.class;
    }
}
