package v14example.vaadin.ui;

import com.github.appreciated.app.layout.addons.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.addons.notification.component.NotificationButton;
import com.github.appreciated.app.layout.addons.notification.entity.DefaultNotification;
import com.github.appreciated.app.layout.addons.profile.ProfileButton;
import com.github.appreciated.app.layout.addons.profile.builder.AppBarProfileButtonBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.LeftSubmenu;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftClickableItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.VaadinServletService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import v14example.vaadin.ui.view.HomepageView;
import v14example.vaadin.ui.view.d3.D3View;
import v14example.vaadin.ui.view.mapbox.MapboxView;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;


@Push
@CssImport(value = "./styles/view-styles.css", id = "view-styles")
@CssImport(value = "./styles/shared-styles.css", include = "view-styles")
// @JsModule(com.vaadin.flow.component.dnd.internal.DndUtil.DND_CONNECTOR)
@Theme(value = Lumo.class)
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MyAppLayoutRouterLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsiveHybrid>
{
    /**
     * Do not initialize here. This will lead to NPEs
     */
    private DefaultNotificationHolder notifications;

    private DefaultBadgeHolder badge;

    // private Class<? extends AppLayout> variant = LeftLayouts.LeftResponsiveHybrid.class;

    public MyAppLayoutRouterLayout()
    {
        // init(getLayoutConfiguration(variant));

        notifications = new DefaultNotificationHolder(newStatus ->
        {
        });

        badge = new DefaultBadgeHolder(1);

        for (int i = 1; i < 1; i++)
        {
            notifications.add(new DefaultNotification("Test notification" + i, "A rather long test description ............... " + i));
        }

        // LeftNavigationItem menuEntry = new LeftNavigationItem("Badge ding", VaadinIcon.MENU.create(), SelectView.class);

        AppLayoutBuilder<LeftLayouts.LeftResponsiveHybrid> appLayoutBuilder = AppLayoutBuilder.get(LeftLayouts.LeftResponsiveHybrid.class);
        // appLayoutBuilder.withdo();
        appLayoutBuilder.setTitleComponent(new H3("DSOL Workbench"));

        // appLayoutBuilder.withAppBar(getAppBar());
        appLayoutBuilder.withUpNavigation();

        appLayoutBuilder.withAppMenu(getLeftMenu());

        LeftLayouts.LeftResponsiveHybrid leftPanel = null;
        leftPanel = appLayoutBuilder.build();

        init(leftPanel);
    }

    private Component getLeftMenu()
    {
        LeftAppMenuBuilder leftAppMenuBuilder = LeftAppMenuBuilder.get();

        // header top
        String imageLocation = VaadinServletService.getCurrent().resolveResource("frontend://img/earth.jpg", VaadinSession.getCurrent().getBrowser());
        LeftHeaderItem headerTop = new LeftHeaderItem("© MHM 2019", "v0.1-SNAPSHOT", imageLocation);
        LeftNavigationItem homeItem = new LeftNavigationItem("Home", VaadinIcon.HOME.create(), HomepageView.class);
        leftAppMenuBuilder.addToSection(HEADER, headerTop);

        // main menu items
        leftAppMenuBuilder.add(homeItem, getMapMenu()).build();

        // header footer;
        leftAppMenuBuilder.addToSection(FOOTER, new LeftClickableItem("Settings", VaadinIcon.COGS.create(), clickEvent -> Notification.show("To be implemented.")));

        return leftAppMenuBuilder.build();
    }

    private FlexLayout getAppBar()
    {
        NotificationButton bellButton = new NotificationButton<>(VaadinIcon.BELL, notifications);

        ProfileButton profileButton = AppBarProfileButtonBuilder.get()
                .withItem("Profile", event -> Notification.show("Profile clicked"))
                .withItem("Profile", event -> Notification.show("Profile clicked"))
                .withItem("Profile", event -> Notification.show("Profile clicked"))
                .build();

        return AppBarBuilder.get().add(profileButton, bellButton).build();
    }

    private LeftSubmenu getMapMenu()
    {
        LeftNavigationItem d3View = new LeftNavigationItem("D3 example", VaadinIcon.MAP_MARKER.create(), D3View.class);
        LeftNavigationItem mapboxExampleItem = new LeftNavigationItem("Mapbox example", VaadinIcon.MAP_MARKER.create(), MapboxView.class);
        // LeftNavigationItem mapView = new LeftNavigationItem("NL Map", VaadinIcon.MAP_MARKER.create(), MapView.class);
        LeftSubmenu otherSubmenu = LeftSubMenuBuilder.get("Maps").add(d3View).add(mapboxExampleItem).build();

        return otherSubmenu;
    }

//    private LeftSubmenu getSimulationMenu()
//    {
//        // LeftNavigationItem selectItem = new LeftNavigationItem("Select", VaadinIcon.SELECT.create(), SelectView.class);
//        LeftNavigationItem configureView = new LeftNavigationItem("Configure", VaadinIcon.CONNECT.create(), ModelView.class);
//        LeftNavigationItem simulateView = new LeftNavigationItem("Run", VaadinIcon.DASHBOARD.create(), SimulateView.class);
//        // badge.bind(simulateView.getBadge());
//
//        LeftNavigationItem multipleView = new LeftNavigationItem("Compare", VaadinIcon.CHART_LINE.create(), SimulateMultipleView.class);
//        LeftSubmenu simulationMenu = LeftSubMenuBuilder.get("Simulate").add(configureView, simulateView, multipleView).build();
//
//        return simulationMenu;
//    }

//    private LeftSubmenu getVisualizationMenu()
//    {
//        // LeftNavigationItem animationCanvasView = new LeftNavigationItem("1) Animation Canvas", VaadinIcon.CLIPBOARD.create(), AnimationCanvasView.class);
//        LeftNavigationItem animationPoCView = new LeftNavigationItem("Animation on SVG", VaadinIcon.PENCIL.create(), AnimationSVGView.class);
//        LeftNavigationItem animationMM1 = new LeftNavigationItem("MM1 Animation PoC", VaadinIcon.FACTORY.create(), AnimationMM1View.class);
//        LeftNavigationItem chartItem = new LeftNavigationItem("Dynamic chart example", VaadinIcon.CHART_GRID.create(), ChartView.class);
//        LeftSubmenu anotherMenu = LeftSubMenuBuilder.get("Visualize").add(animationPoCView, animationMM1, chartItem).build();
//
//        return anotherMenu;
//    }

    public DefaultNotificationHolder getNotifications()
    {
        return notifications;
    }

    public DefaultBadgeHolder getBadge()
    {
        return badge;
    }
}

//    RouterLink modelView = createRouterLink("Model", ModelView.class, VaadinIcon.COGS);
//    RouterLink simulateView = createRouterLink("Simulate", SimulateView.class, VaadinIcon.DASHBOARD);
//    RouterLink simulateMultipleView = createRouterLink("Compare", SimulateMultipleView.class, VaadinIcon.CHART_LINE);
//    RouterLink chartView = createRouterLink("Chart", ChartView.class, VaadinIcon.CHART_GRID);
//    // RouterLink mapView = createRouterLink("Map", MapView.class, VaadinIcon.MAP_MARKER);
//    RouterLink geoView = createRouterLink("Geo", MapView.class, VaadinIcon.GLOBE);
//    RouterLink canvasView = createRouterLink("Animation", AnimationView.class, VaadinIcon.PENCIL);
//    RouterLink d3View = createRouterLink("D3", D3View.class, VaadinIcon.CLUSTER);

