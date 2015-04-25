/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.pages;

import com.diplomski.katedra.services.app.MainService;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author PC
 */
public class Registration {

    @Persist
    @Property
    private String email;
    @Persist
    @Property
    private String ime;
    @Persist
    @Property
    private String prezime;
    @Persist
    @Property
    private String brIndeksa;
    
    @Inject
    private MainService mainService;

    Object onSuccess() {

//        return Pocetna.class;
        return null;
    }
    
    Object onSubmitFromRegistrationForm(){
        /*Class nextPage = null;
        if(mainService.postojiStudent(brIndeksa, ime, prezime)){
            mainService.sendMail(email);
            
        }*/
        return null;
    }
    
}
