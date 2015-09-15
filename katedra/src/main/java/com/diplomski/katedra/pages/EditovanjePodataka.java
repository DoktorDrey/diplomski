package com.diplomski.katedra.pages;

import com.diplomski.katedra.db.model.Student;
import com.diplomski.katedra.services.app.MainService;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tapestry5.Asset;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.upload.services.UploadedFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by andrija on 8/8/15.
 */
public class EditovanjePodataka {
    private static final Logger logger = Logger.getLogger(Registration.class);

    @InjectComponent
    private Form myProfileForm;

    @SessionState(create = false)
    @Property
    private Student student;

    @Inject
    private MainService mainService;

    @Inject
    private Messages messages;

    @Inject
    private AssetSource assetSource;

    @Property
    private UploadedFile file;

    @Persist(PersistenceConstants.FLASH)
    @Property
    private String message;

    @Inject
    private Request request;



    /*    public String getImageURL() {
        String assetUrl = assetSource.getContextAsset("images/" + student.getImageName(), locale).toClientURL();
        logger.debug(assetUrl);
        return assetUrl.toString();
    }*/
    public Asset getImageURL() {
        logger.debug(assetSource.getClass().getResource("./"));
        final String path = "/layout/images/" + student.getImageName();
        return assetSource.getContextAsset(path, null);
    }
    Object onSuccess() {
        return this;
    }

    void onValidateFromMyProfileForm(){
        ucitajSliku();
        logger.debug(student.getBrojIndeksa());
        Class nextPage = null;
        try {
            mainService.izmenaKorisnika(student);
        } catch (Exception e) {
            e.printStackTrace();
            myProfileForm.recordError(messages.get("student-not-exist"));
        }
//        return null;
    }

    Object onUploadException(FileUploadException ex)
    {
        message = "Upload exception: " + ex.getMessage();

        return this;
    }

    private void ucitajSliku() {
        try {
            logger.debug(file.getFileName());
            logger.debug(request.getContextPath());

            File copied = new File("C:/Program Files/Apache/apache-tomcat-7.0.62/webapps/ROOT/layout/images/" + file.getFileName());
            student.setImageName(file.getFileName());
            file.write(copied);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Object onActivate() {
        if(student != null)
            return null;
        return Index.class;
    }
}
