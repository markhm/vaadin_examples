package v14example.vaadin.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.server.VaadinService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Locale;

public class Util
{
    private static Log log = LogFactory.getLog(Util.class);

    private static Util instance = null;

    private static boolean alreadyPrinted;

    public static Util getInstance()
    {
        if (instance == null)
        {
            instance = new Util();
        }

        return instance;
    }

    private Util()
    {
        alreadyPrinted = false;
    }

    public static void printLocaleDebugInfo(Component component, Locale localLocale)
    {
        log.info("********* From component "+component);
        log.info("Locale from Vaadin session is: "+UI.getCurrent().getLocale());
        log.info("Locale from getLocale() is: "+localLocale);
        log.info("");
    }

    public synchronized void printRoutesOnce()
    {
        if (! alreadyPrinted)
        {
            log.info("----");
            log.info("Found the following defined routes:");
            // Router router = UI.getCurrent().getRouter();
            // List<RouteData> routes = router.getRoutes();

            List<RouteData> routes = RouteConfiguration.forApplicationScope().getAvailableRoutes();

            for (RouteData route : routes)
            {
                log.info("Found registered route " + route);
            }

            log.info("----");
            alreadyPrinted = true;
        }
    }

}
