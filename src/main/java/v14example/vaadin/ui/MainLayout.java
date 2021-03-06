package v14example.vaadin.ui;

/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.*;
import v14example.vaadin.ui.session.SessionContext;
import v14example.vaadin.ui.view.CommonViewElements;
import v14example.vaadin.ui.view.d3.D3View;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import v14example.vaadin.ui.view.tabs.TabsView;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The main layout contains the header with the navigation buttons, and the
 * child views below that.
 */

// @ Push (transport = Transport.LONG_POLLING)
// @ Push
// @ Push (transport = Transport.WEBSOCKET_XHR)
// @ CssImport(value = "./styles/view-styles.css", id = "view-styles")
// @ CssImport(value = "./styles/shared-styles.css", include = "view-styles")
// @ Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout // extends Div implements RouterLayout, PageConfigurator, LocaleChangeObserver
{
    // DEACTIVATED
//    private static Log log = LogFactory.getLog(MainLayout.class);
//
//    private static final String COOKIE_NAME = "Cookie identifier";
//
//    private SessionContext context;
//
//    private Div header = null;
//    private Image languageImage = null;
//
//    private H2 title = null;
//
//    // private Text d3viewButtonText = null;
//    private Text tabsButtonText = null;
//    private Text formLayoutButtonText = null;
//
//    @Autowired
//    public MainLayout(SessionContext context)
//    {
//        if (context.getLocale() == null)
//        {
//            context.setLocale(getLocale());
//        }
//
//        title = new H2("Vaadin Examples");
//        title.addClassName("main-layout__title");
//
//        RouterLink d3View = createRouterLink("D3 Tree", D3View.class, VaadinIcon.LINK);
//        RouterLink tabsView = createRouterLink("Tabs", TabsView.class, VaadinIcon.LINK);
//
//        // Image is read from 'src/main/resources/META-INF/resources/'.
//        String resolvedImage = VaadinServletService.getCurrent().resolveResource("frontend://img/Vaadin Examples logo.png", VaadinSession.getCurrent().getBrowser());
//        Image logo = new Image(resolvedImage, "Vaadin Examples logo");
//
//        logo.setHeight("50%");
//        logo.addClickListener(e -> popupColofon());
//
//        Label whitespaceBefore = new Label("");
//        // whitespaceBefore.getElement().setProperty("innerHTML", "&emsp;");
//        whitespaceBefore.setVisible(true);
//
//        Label whitespaceAfter = new Label("");
//        whitespaceAfter.getElement().setProperty("innerHTML", "&emsp;");
//        whitespaceAfter.setVisible(true);
//
//        Label space = new Label("   ");
//        space.setVisible(true);
//
//        header = new Div(title, logo, space, getLanguageImage());
//        header.addClassName("main-layout__header");
//        add(header);
//
//        addClassName("main-layout");
//    }
//
//    private RouterLink createRouterLink(String label, Class<? extends Component> component, VaadinIcon vaadinIcon)
//    {
//        return createRouterLink(label, component, vaadinIcon, "base");
//    }
//
//    private RouterLink createRouterLink(String label, Class<? extends Component> component, VaadinIcon vaadinIcon, String iconColor)
//    {
//        RouterLink routerLink = new RouterLink(null, component);
//        Text text = new Text(label);
//        Icon icon = new Icon(vaadinIcon);
//        icon.setColor(iconColor);
//        routerLink.add(icon, text);
//        routerLink.addClassName("main-layout__nav-item");
//
//        // menuTextAndTranslation.put(text, translationItem);
//
//        return routerLink;
//    }
//    @Override
//    public void localeChange(LocaleChangeEvent event)
//    {
//        header.remove(languageImage);
//        header.add(getLanguageImage());
//
//        title.setText(getTranslation("mainlayout.title", getLocale()));
//        // d3viewButtonText.setText(getTranslation("mainlayout.menubar.d3view", getLocale()));
//    }
//
//    private void getLocaleFromCookie()
//    {
//        boolean cookieFound = false;
//        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
//        for (Cookie cookie : cookies)
//        {
//            if (cookie.getName().equals(COOKIE_NAME))
//            {
//                cookieFound = true;
//                // currentLanguage = cookie.getValue();
//                // setLocaleFromLanguage(currentLanguage);
//            }
//        }
//
//        log.info("Cookie found: "+cookieFound);
//
//        //if (currentLanguage == null)
//        {
//            // currentLanguage = DEFAULT_LANGUAGE;
//            // setLocaleFromLanguage(currentLanguage);
//        }
//    }
//
//    private void storeLanguageSetting(String language)
//    {
//        log.info("Storing language " + language + " in the cookie");
//        Cookie languageCookie = new Cookie(COOKIE_NAME, language);
//        VaadinService.getCurrentResponse().addCookie(languageCookie);
//    }
//
//    private Image getLanguageImage()
//    {
//        String resolvedImage = VaadinServletService.getCurrent().resolveResource("frontend://img/united-kingdom-flag-round-icon-32.png", VaadinSession.getCurrent().getBrowser());
//        languageImage = new Image(resolvedImage, "Click to change language");
//        return languageImage;
////        // source https://www.countryflags.com/en/united-kingdom-flag-icon.html
////        // source https://www.countryflags.com/en/denmark-flag-icon.html
////
////        Locale locale = getLocale();
////
////        if (!(TranslationProvider.LOCALE_EN.equals(locale) || TranslationProvider.LOCALE_DA.equals(locale)))
////        {
////            log.info("Locale "+locale+" was unknown, defaulting to "+DEFAULT_LOCALE);
////            UI.getCurrent().setLocale(DEFAULT_LOCALE);
////            locale = getLocale();
////        }
////
////        if (locale.equals(TranslationProvider.LOCALE_EN))
////        {
////
////
////        }
////        else if (locale.equals(TranslationProvider.LOCALE_DA))
////        {
////            String resolvedImage = VaadinServletService.getCurrent().resolveResource("frontend://img/denmark-flag-round-icon-32.png", VaadinSession.getCurrent().getBrowser());
////            languageImage = new Image(resolvedImage, "Klik for at ændre sprog");
////        }
////        else
////        {
////            log.info("Locale was unknown, keeping default.");
////        }
////
////        languageImage.setWidth("25px");
////        languageImage.addClickListener(e -> changeLanguage());
////
////        return languageImage;
//    }
//
//
//    private void popupColofon()
//    {
//        // Util.printCookies();
//
//        List<String> messages = new ArrayList<>();
//        messages.add("v0.1 by Mark Hissink Muller");
//        messages.add("");
//        messages.add("First version August 2019");
//
//        CommonViewElements.openDialogWindow("Colophon", messages, 300, 550);
//    }
//
//    @Override
//    public void configurePage(InitialPageSettings settings)
//    {
//        settings.addMetaTag("apple-mobile-ui-app-capable", "yes");
//        settings.addMetaTag("apple-mobile-ui-app-status-bar-style", "black");
//    }
}
