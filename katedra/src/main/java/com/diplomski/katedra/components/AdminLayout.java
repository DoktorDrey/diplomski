package com.diplomski.katedra.components;

import com.diplomski.katedra.db.model.Predavac;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by Andrija Ilic on 8/3/2014.
 */
@Import(stylesheet = {"context:resources/css/admin_layout.css"})
public class AdminLayout {
    @SessionState(create = false)
    private Predavac predavac;
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

    public String getClassForPageName()
    {
        System.out.println(pageName);
        System.out.println(resources.getPageName());
        return resources.getPageName().equalsIgnoreCase("admin/"+pageName)
                ? "current_page_item"
                : null;
    }

    public String[] getPageNames()
    {
        if(predavac == null)
            return new String[]{};
        else
            return new String[]{"UnosStudenata","PrikazStudenata","Logout"};
    }

}
