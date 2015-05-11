/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diplomski.katedra.pages;

import com.diplomski.katedra.services.app.MainService;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author PC
 */
public class Registration {

    @Persist
    @Property
    @Validate("required")
    private String email;
    @Persist
    @Property
    @Validate("required")
    private String ime;
    @Persist
    @Property
    @Validate("required")
    private String prezime;
    @Persist
    @Property
    @Validate("required")
    private String brIndeksa;
    
    @Inject
    private MainService mainService;

    Object onSuccess() {
        System.out.println("onSuccess");
        return About.class;
    }

    Object onSubmitFromRegistrationForm(){
        System.out.println("onSubmitFromRegistrationForm");
        /*Class nextPage = null;
        if(mainService.postojiStudent(brIndeksa, ime, prezime)){
            mainService.sendMail(email);
            
        }*/
        return null;
    }
    
}
