package com.diplomski.katedra.pages.admin;

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
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.apache.tapestry5.upload.services.UploadedFile;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andrija on 7/29/15.
 */
public class PrijavaStudentaPredmet {
    private static final Logger logger = Logger.getLogger(PrijavaStudentaPredmet.class);

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

    @Inject
    SelectModelFactory selectModelFactory;

    @Inject
    private AdminService adminService;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    public void ucitajExcel() {
        try {
            message = "";
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
                    logger.debug(e.getMessage());
                    logger.debug(cell.getStringCellValue());
                    if(message.equals(""))
                        message = "Sledeci studente nisu prijavljeni na program:</br> ";
                    message += cell.getStringCellValue() + ", ";
                }
                if(message.equals(""))
                    message = "Studenti su uspesno prijavljeni";
                /*Student s = new Student();
                s.setBrojIndeksa(cell.getStringCellValue());
                cell = cellIterator.next();
                s.setIme(cell.getStringCellValue());
                cell = cellIterator.next();
                s.setPrezime(cell.getStringCellValue());
                adminService.saveStudent(s);*/
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setupRender() {
        // invoke my service to promeni all colors, e.g. in the database
        List<Predmet> predmets = adminService.findAllPredmetsForPredavac(predavac);

        // create a SelectModel from my izlistaj of colors
        predmetSelectModel = selectModelFactory.create(predmets, "name");
    }

    public List<Integer> getYears() {
        return adminService.getYears();
    }

   /* public PredmetEncoder getPredmetEncoder() {
        return new PredmetEncoder();
    }*/

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
