package mhm.example.vaadin.ui.i18n;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.server.VaadinService;

import java.util.List;
import java.util.Locale;

public class TranslationBase
{
    public TranslationBase()
    {

    }

    public String getTranslation(String key, Object... params)
    {
        return this.getTranslation(key, this.getLocale(), params);
    }

    public String getTranslation(String key, Locale locale, Object... params)
    {
        return this.getI18NProvider() == null ? "!{" + key + "}!" : this.getI18NProvider().getTranslation(key, locale, params);
    }

    private I18NProvider getI18NProvider()
    {
        return VaadinService.getCurrent().getInstantiator().getI18NProvider();
    }

    protected Locale getLocale()
    {
        UI currentUi = UI.getCurrent();
        Locale locale = currentUi == null ? null : currentUi.getLocale();
        if (locale == null)
        {
            List<Locale> locales = this.getI18NProvider().getProvidedLocales();
            if (locales != null && !locales.isEmpty())
            {
                locale = (Locale) locales.get(0);
            } else
            {
                locale = Locale.getDefault();
            }
        }

        return locale;
    }
}
