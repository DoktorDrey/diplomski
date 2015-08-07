package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Aktivnost;
import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Predmet;
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
    private int year;

    @Inject
    @Property
    private PredmetEncoder predmetEncoder;

    @Property
    private Predmet selectedPredmet;

    @Property
    private Aktivnost activity;

    @Property
    @Persist
    private List<Aktivnost> activities;

    @Inject
    SelectModelFactory selectModelFactory;

    @Inject
    private AdminService adminService;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @InjectComponent
    private Zone modelZone;

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
    }

    public Object onValueChanged(Predmet predmet)
    {
        logger.debug(year);
        logger.debug(predmet.getName());
//        activities = adminService.getActivities();

        return modelZone.getBody();
    }

    public List<Integer> getYears() {
        return adminService.getYears();
    }

    public void onSuccess() {
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
