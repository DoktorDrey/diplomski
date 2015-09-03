package com.diplomski.katedra.services.admin;

import com.diplomski.katedra.db.dao.*;
import com.diplomski.katedra.db.model.*;
import com.diplomski.katedra.security.Crypto;
import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrija Ilic on 8/2/2014.
 */
public class AdminServiceImpl implements AdminService {
    private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);
    @Inject
    private PredavacDao predavacDao;

    @Inject
    private StudentDao studentDao;

    @Inject
    private PredmetDao predmetDao;

    @Inject
    private ProgramDao programDao;

    @Inject
    private AktivnostDao aktivnostDao;

    @Inject
    private StudentAktivnostDao studentAktivnostDao;

    @Inject
    private StudentPredmetAssDao studentPredmetAssDao;

    @Inject
    private TipAktivnostiDao tipAktivnostiDao;

    @Inject
    private ProgramOceneDao programOceneDao;

    private static final int numOfLastYears = 3;

    @Override
    public Predavac authenticate(String username, String password) throws Exception {
        logger.debug(Crypto.hash(password));
        logger.debug(username);
        Predavac predavac = predavacDao.getByUserPass(username, Crypto.hash(password));
        if(predavac == null) {
            throw new Exception("Invalid username or password!");
        }
        return predavac;
    }

    @Override
    public void saveStudent(Student s) {
        studentDao.add(s);
    }

    @Override
    public void prijaviStudenta(String brojIndeksa, int predmet, int year) {
        Student student = studentDao.getByBrIndeks(brojIndeksa);
        Program program = programDao.findProgram(predmet, year);
        studentDao.prijaviStudenta(student, program);
    }

    @Override
    public List<Predmet> findAllPredmets() {
        return predmetDao.list();
    }

    @Override
    public List<Predmet> findAllPredmetsForPredavac(Predavac predavac) {
//        return predmetDao.list();
        return predmetDao.vratiPredmeteZaPredavaca(predavac);
    }

    @Override
    public List<Integer> getYears() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        List<Integer> years= new ArrayList<Integer>();
        for(int i = 0; i < numOfLastYears; i++) {
            years.add(year-i);
        }
        return years;
    }

    @Override
    public List<StudentPredmetAss> findAllStudentsInfo(int predmet, int year) {
        Program program = programDao.findProgram(predmet, year);
        List<StudentPredmetAss> students = studentPredmetAssDao.getStudentsByProgram(program);
        logger.debug(students.toString());
        return students;
    }

    @Override
    public List<Activity> getActivities(Program program) {
        logger.debug(program.getId());
        List<Aktivnost> aktivnosts = aktivnostDao.findForProgram(program);
        List<Activity> activities = new ArrayList<Activity>();
        String satnica;
        for (Aktivnost currentAktivity : aktivnosts)
        {
            satnica = currentAktivity.getDatum().toString().split(" ")[1].toString();
            satnica = satnica.split("\\.")[0];
            activities.add(new Activity(currentAktivity.getDatum(), currentAktivity, satnica));
        }
        logger.debug(activities.toString());
        return activities;
    }

    @Override
    public List<Aktivnost> vratiAktivnosti(Program program) {
        logger.debug(program.getId());
        return aktivnostDao.findForProgram(program);
    }

    @Override
    public List<StudentAktivnostAss> getStudentActivities(int predmet, int year) {
        Program program = programDao.findProgram(predmet, year);
        logger.debug(program.getId());
        List<StudentAktivnostAss> activities = studentAktivnostDao.findForProgram(program);
        return activities;
    }

    @Override
    public void unesiRezultat(String brojIndeksa, double brojPoena, Aktivnost selectedActivity) {
        Student student = studentDao.getByBrIndeks(brojIndeksa);
        StudentAktivnostAss saa = new StudentAktivnostAss();
        saa.setAktivnost(selectedActivity);
        saa.setBrojPoena(brojPoena);
        saa.setStudent(student);
        studentAktivnostDao.add(saa);
    }

    @Override
    public List<TipAktivnosti> findAllActivityTypes() {
        return tipAktivnostiDao.list();
    }

    @Override
    public void setProgramActivities(List<Activity> activities, ProgramOcene programOcene) {
        aktivnostDao.removeActivitiesForProgram(programOcene.getProgramId());
        programOceneDao.remove(programOcene);
        programOceneDao.add(programOcene);
        for (Activity currentAktivity : activities)
        {
            try {
                Date datum = currentAktivity.getDatum();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date parsedDate = dateFormat1.parse(dateFormat.format(datum)+" "+currentAktivity.getSatnica());
                logger.debug(dateFormat.format(parsedDate));
                Timestamp datum1 = new Timestamp(parsedDate.getTime());
                logger.debug(datum1.toString());
                currentAktivity.getAktivnost().setDatum(datum1);
                aktivnostDao.add(currentAktivity.getAktivnost());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Program findProgram(int predmet, int year) {
        return programDao.findProgram(predmet, year);
    }
}
