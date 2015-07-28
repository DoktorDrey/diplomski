
package com.diplomski.katedra.pages.admin;

import com.diplomski.katedra.db.model.Predavac;
import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.services.admin.AdminService;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.upload.services.UploadedFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * Created by Andrija Ilic on 8/3/2014.
 */
public class UnosStudenata {
    @SessionState(create=false)
    private Predavac predavac;

    @Property
    private UploadedFile file;

    @Inject
    private AdminService adminService;
    private static final Logger logger = Logger.getLogger(UnosStudenata.class);
    public void ucitajExcel() {
        try
        {
            logger.debug(file.toString());
            logger.debug(file.getFileName());
            logger.debug(file.getFilePath());
            File copied = new File("C:/Users/Andrija Ilic/diplomski/" + file.getFileName());
            file.write(copied);
            FileInputStream fileInputStream = new FileInputStream("C:/Users/Andrija Ilic/diplomski/" +file.getFileName());

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
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                Cell cell = cellIterator.next();
                Student s = new Student();
                s.setBrojIndeksa(cell.getStringCellValue());
                cell = cellIterator.next();
                s.setIme(cell.getStringCellValue());
                cell = cellIterator.next();
                s.setPrezime(cell.getStringCellValue());
                System.out.println(s.toString());
                adminService.saveStudent(s);
            }
            fileInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onSuccess() {
        ucitajExcel();
    }
    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

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
