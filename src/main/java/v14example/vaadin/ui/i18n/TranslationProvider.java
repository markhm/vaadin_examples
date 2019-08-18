package v14example.vaadin.ui.i18n;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.joconner.i18n.Utf8ResourceBundleControl;
import com.vaadin.flow.i18n.I18NProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class TranslationProvider implements I18NProvider
{
    private static Log log = LogFactory.getLog(TranslationProvider.class);

    public static final String BUNDLE_PREFIX = "translations";

    public static final Locale LOCALE_DA = new Locale("da", "DK");
    public static final Locale LOCALE_EN = new Locale("en", "GB");

    private List<Locale> locales = Collections.unmodifiableList(Arrays.asList(LOCALE_DA, LOCALE_EN));

    private static final LoadingCache<Locale, ResourceBundle> bundleCache =
            CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<Locale, ResourceBundle>()
            {

                @Override
                public ResourceBundle load(final Locale key) throws Exception
                {
                    return initializeBundle(key);
                }
            });

    public TranslationProvider()
    {
        log.info("TranslationProvider initializing");
    }

    @Override
    public List<Locale> getProvidedLocales()
    {
        return locales;
    }

    @Override
    public String getTranslation(String key, Locale locale, Object... params)
    {
        // log.info("@translationProvider.getTranslation(" + key + "," + locale + ",...)");
        if (key == null)
        {
            log.warn("Got lang request for key with null value!");
            return "";
        }

        final ResourceBundle bundle = bundleCache.getUnchecked(locale);
        // final ResourceBundle bundle = readProperties(locale);// .getUnchecked(locale);

        String value;
        try
        {
            value = bundle.getString(key);
        }
        catch (final MissingResourceException e)
        {
            log.error("Missing resource", e);
            return "!" + locale.getLanguage() + ": " + key;
        }
        if (params.length > 0)
        {
            value = MessageFormat.format(value, params);
        }
        return value;
    }

    private static ResourceBundle initializeBundle(final Locale locale)
    {
        return readProperties(locale);
    }

    protected static ResourceBundle readProperties(final Locale locale)
    {
        final ClassLoader cl = TranslationProvider.class.getClassLoader();

        ResourceBundle propertiesBundle = null;
        try
        {
            ResourceBundle.Control utf8Control = new Utf8ResourceBundleControl(false);
            propertiesBundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale, utf8Control);
        }
        catch (final MissingResourceException e)
        {
            log.error("Missing resource", e);
        }
        return propertiesBundle;
    }
}
