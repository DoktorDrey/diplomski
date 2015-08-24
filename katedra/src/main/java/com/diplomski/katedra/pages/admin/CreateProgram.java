package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.*;
import com.diplomski.katedra.encoders.ActivityEncoder;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by andrija on 8/24/15.
 */
public class CreateProgram {
    private static final Logger logger = Logger.getLogger(CreateProgram.class);

    @SessionState(create=false)
    private Predavac predavac;

    @Property
    private SelectModel predmetSelectModel;

    @Property
    @Persist
    private SelectModel activityTipSelectModel;

    @Property
    @Persist
    private Program program;

    @Property
    @Persist
    private int year;

    @Inject
    @Property
    private PredmetEncoder predmetEncoder;

    @Property
    @Persist
    private Predmet selectedPredmet;

    @Inject
    SelectModelFactory selectModelFactory;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private List<Aktivnost> activities;

    @Property
    @Persist
    private Aktivnost activity;

    @Inject
    private Messages messages;

    @Inject
    private AdminService adminService;

    @InjectComponent
    private Zone programZone;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String poruka;

    @Property
    @Persist
    List<TipAktivnosti> tipAktivnostis;

    @Property
    @Persist
    TipAktivnosti activityTip;


    void setupRender() {
        List<Predmet> predmets = adminService.findAllPredmets();
        predmetSelectModel = selectModelFactory.create(predmets, "name");
        tipAktivnostis = adminService.findAllActivityTypes();
        logger.debug(tipAktivnostis);
        activityTipSelectModel = selectModelFactory.create(tipAktivnostis, "nazivAktivnosti");
        logger.debug(tipAktivnostis.toString());
    }

    public List<Integer> getYears() {
        return adminService.getYears();
    }

    Object onValueChangedFromYear(int year) {
        logger.debug(year);
        logger.debug(this.year);
        this.year = year;
        if(this.selectedPredmet != null) {
            activities = adminService.getActivities(selectedPredmet.getId(), year);
            poruka = "";
            if(activities.isEmpty()) {
                poruka = "Nema pronadjenih rezultata za zadati kriterijum";
            }
            return programZone.getBody();
        }
        return null;
    }

    Object onValueChangedFromPredmet(Predmet predmet) {
        logger.debug(predmet);
        logger.debug(year);
        selectedPredmet = predmet;
        logger.debug(selectedPredmet);
        if(year != 0) {
            activities = adminService.getActivities(selectedPredmet.getId(), year);
            logger.debug(activities.toString());
            poruka = "";
            if(activities.isEmpty()) {
                poruka = "Nema pronadjenih rezultata za zadati kriterijum";
            }
            return programZone.getBody();
        }
        return null;
    }

    public boolean isShowProgramZone() {
        return year != 0 && selectedPredmet != null;
    }


    public ValueEncoder<TipAktivnosti> getActivityEncoder()
    {
        return new ValueEncoder<TipAktivnosti>()
        {
            public String toClient(TipAktivnosti value)
            {
                logger.debug("toClient");
                logger.debug(value.toString());
                logger.debug(value.getNazivAktivnosti());
                return value.getNazivAktivnosti();
            }

            public TipAktivnosti toValue(String clientValue)
            {
                logger.debug("toValue");
                logger.debug(clientValue);
                for (TipAktivnosti currentAktivity : tipAktivnostis)
                {
                    if (currentAktivity.getNazivAktivnosti() != null &&
                            clientValue.equals(currentAktivity.getNazivAktivnosti()))
                        return currentAktivity;
                }

                return null;
            }
        };
    }

    @OnEvent(value=EventConstants.ADD_ROW, component="activities")
    public Object onAddRowFromActivities()
    {
        Aktivnost aktivnost = new Aktivnost();

        activities.add(aktivnost);
        aktivnost.setProgram(program);
        aktivnost.setTipAktivnosti(new TipAktivnosti(3, "kolokvijum"));
        return aktivnost;
    }

    @OnEvent(value=EventConstants.REMOVE_ROW, component="activities")
    void onRemoveRowFromActivities(Aktivnost aktivnostToDelete)
    {
        activities.remove(aktivnostToDelete);
    }

    @OnEvent(EventConstants.SUCCESS)
    public Object onSuccess()
    {
        logger.debug("onSuccess");
        return this;
    }

    Object onActivate() {
        if (program == null)
            program = new Program();
        if(predavac != null)
            return null;
        return Index.class;
    }
}


