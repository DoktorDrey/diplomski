package com.diplomski.katedra.components;

import com.diplomski.katedra.db.model.Student;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;


/**
 * Layout component for pages of application katedra.
 */
@Import(stylesheet = "context:layout/layout.css")
public class CommonLayout
{
    @SessionState(create = false)
    private Student student;

    /**
     * The pages title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private String sidebarTitle;

    @Property
    @Parameter(defaultPrefix = BindingConstants.LITERAL)
    private Block sidebar;

    @Inject
    private ComponentResources resources;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;


    public String getClassForPageName()
    {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "current_page_item"
                : null;
    }

    public String[] getPageNames()
    {
        if(student == null) {
            return new String[]{"Index","Contact"};
        } else {
            return new String[]{"Index", "About", "Contact","Test"};
        }
    }
}
