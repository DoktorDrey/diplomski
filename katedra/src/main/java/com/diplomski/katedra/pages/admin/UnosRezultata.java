package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
import com.diplomski.katedra.encoders.ActivityEncoder;
import com.diplomski.katedra.encoders.PredmetEncoder;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.upload.services.UploadedFile;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andrija on 8/7/15.
 */
public class UnosRezultata {
    private static final Logger logger = Logger.getLogger(UnosRezultata.class);

    @SessionState(create=false)
    private Predavac predavac;

    @Property
    private UploadedFile file;

    @Property
    private SelectModel predmetSelectModel;

    @Property
    private SelectModel activitySelectModel;

    @Property
    @Persist
    private int year;

    @Inject
    @Property
    private PredmetEncoder predmetEncoder;

    @Inject
    @Property
    private ActivityEncoder activityEncoder;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private Predmet selectedPredmet;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private Aktivnost selectedActivity;

    @Property
    @Persist
    private List<Integer> years;

    @Inject
    SelectModelFactory selectModelFactory;

    @Inject
    private AdminService adminService;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @InjectComponent
    private Zone activityZone;

    public void ucitajExcel() {
        try {
            InputStream fileInputStream = file.getStream();
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;
            if(rowIterator.hasNext()) {
                row = rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                Cell cell = cellIterator.next();
                try {
                    adminService.prijaviStudenta(cell.getStringCellValue(), selectedPredmet.getId(), year);
                } catch (Exception e) {
                    logger.debug(cell.getStringCellValue());
                }
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setupRender() {
        // invoke my service to find all colors, e.g. in the database
        List<Predmet> predmets = adminService.findAllPredmets();
        // create a SelectModel from my list of colors
        predmetSelectModel = selectModelFactory.create(predmets, "name");
        years = adminService.getYears();
//        List<Aktivnost> activities = adminService.getActivities(1, 2015);
    }

    void onValueChangedFromYear(int year) {
        logger.debug(year);
        logger.debug(this.year);
        this.year = year;
    }

    Object onValueChangedFromPredmet(Predmet predmet) {
        logger.debug(predmet);
        logger.debug(year);
        logger.debug(selectedPredmet);
        selectedPredmet = predmet;
        List<Aktivnost> activities = adminService.getActivities(predmet.getId(), year);
        activitySelectModel = selectModelFactory.create(activities, "TipAktivnosti");
        return activityZone.getBody();
        // Record the source in the activation parameters (AKA query parameters) so it is available in requests from the other zones.
//        logger.debug(predmet.getName());
//        choosenPredmet = predmet;
//        logger.debug(choosenPredmet);
//        logger.debug(selectedPredmet);
//        logger.debug(selectedPredmet.getName());
//        selectedPredmet = predmet;

        // Refresh the makes and models.

//        logger.debug(selectedPredmet.getName());

    }

    /*void onValueChangedFromActivity(Aktivnost activity) {

    }*/

    void onValidateFromSearchCriteria() {
        logger.debug(selectedPredmet);
    }
    /*public Object onValueChanged(Predmet predmet)
    {
        logger.debug(year);
        logger.debug(predmet.getName());
//        activities = adminService.getActivities();

        return modelZone.getBody();
    }*/

    /*public List<Integer> getYears() {
        return adminService.getYears();
    }*/

    public void onSuccess() {
        logger.debug("ttt");
        ucitajExcel();
    }

    Object onUploadException(FileUploadException ex) {
        message = "Upload exception: " + ex.getMessage();
        return this;
    }

    Object onActivate() {
        if(predavac != null)
            return null;
        return Index.class;
    }
}
