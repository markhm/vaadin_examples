package mhm.example.vaadin.ui.session;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

@VaadinSessionScope
@Component
public class SessionContext implements Serializable
{
    private static Log log = LogFactory.getLog(SessionContext.class);

    private static final long serialVersionUID = -1178538977327157062L;

    private Locale locale;

    public void setLocale(Locale locale)
    {
        // log.info("Setting locale: " + locale);
        // this.locale = new Locale(locale.getLanguage());
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
