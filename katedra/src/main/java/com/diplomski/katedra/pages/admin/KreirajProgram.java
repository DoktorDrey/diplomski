package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.*;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.log4j.Logger;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by andrija on 8/24/15.
 */
public class KreirajProgram {
    private static final Logger logger = Logger.getLogger(KreirajProgram.class);

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
    @Persist
    private List<Activity> activities;

    @Property
    private Activity activity;

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
    TipAktivnosti activityTip;

    @Property
    @Persist
    private ProgramOcene programOcene;

    void setupRender() {
        List<Predmet> predmets = adminService.findAllPredmetsForPredavac(predavac);
        predmetSelectModel = selectModelFactory.create(predmets, "name");
        tipAktivnostis = adminService.findAllActivityTypes();
        logger.debug(tipAktivnostis);
        activityTipSelectModel = selectModelFactory.create(tipAktivnostis, "nazivAktivnosti");
        logger.debug(tipAktivnostis.toString());
    }

    public List<Integer> getYears() {
        List<Integer> godine = adminService.getYears();
        year = godine.get(0);
        return godine;
    }

    Object onValueChangedFromYear(int year) {
        logger.debug(year);
        logger.debug(this.year);

        this.year = year;
        if(this.year == 0) {
            poruka = "Molimo vas odaberite godinu";
            return programZone.getBody();
        }
        if(this.selectedPredmet != null) {
            program = adminService.findProgram(selectedPredmet.getId(), year);
            activities = adminService.getActivities(program);
            programOcene = ProgramOcene.getDefault(program);
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
        if(this.selectedPredmet == null) {
            poruka = "Molimo vas odaberite predmet";
            return programZone.getBody();
        }
        if(year != 0) {
            program = adminService.findProgram(selectedPredmet.getId(), year);
            activities = adminService.getActivities(program);
            programOcene = ProgramOcene.getDefault(program);
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
                    logger.debug(currentAktivity.getNazivAktivnosti());
                    if (currentAktivity.getNazivAktivnosti() != null &&
                            clientValue.equals(currentAktivity.getNazivAktivnosti()))
                        return currentAktivity;
                }

                return null;
            }
        };
    }

    public ValueEncoder<Activity> getActivity1Encoder()
    {
        return new ValueEncoder<Activity>()
        {
            public String toClient(Activity value)
            {
                return String.valueOf(value.getAktivnost().getId());
            }

            public Activity toValue(String clientValue)
            {
                logger.debug("toValue");
                logger.debug(clientValue);
                for (Activity currentAktivity : activities)
                {
                    logger.debug(currentAktivity.getDatum());
                    logger.debug(currentAktivity.getAktivnost().getId());
                    if (Integer.parseInt(clientValue) == currentAktivity.getAktivnost().getId())
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

        aktivnost.setProgram(program);
        aktivnost.setTipAktivnosti(new TipAktivnosti(3, "kolokvijum"));
        aktivnost.setMaxPoints(100);
        aktivnost.setMinPoints(50);
        adminService.addActivity(aktivnost);
        Activity activity1 = new Activity(new Date(), aktivnost, "09:00:00");
        activities.add(activity1);
        return activity1;
    }

    @OnEvent(value=EventConstants.REMOVE_ROW, component="activities")
    void onRemoveRowFromActivities(Activity aktivnostToDelete)
    {
        activities.remove(aktivnostToDelete);
    }

    @OnEvent(EventConstants.SUCCESS)
    public Object onSuccess()
    {
        logger.debug("onSuccess");
        logger.debug(program);
        logger.debug(programOcene.getProgramId().getPredmet().getName());
        logger.debug(programOcene.getSest());
        adminService.setProgramActivities(activities, programOcene);
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


