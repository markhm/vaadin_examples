package v14example.vaadin.ui.view.mapbox;

import com.github.markhm.mapbox.GeoLocation;
import com.github.markhm.mapbox.Layer;
import com.github.markhm.mapbox.MapboxMap;
import com.github.markhm.mapbox.Sprite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import v14example.vaadin.ui.MyAppLayoutRouterLayout;

@PageTitle("Mapbox view")
@Route(value = "mapbox", layout = MyAppLayoutRouterLayout.class)
public class MapboxView extends VerticalLayout
{
    private MapboxMap mapboxMap = null;

    boolean alreadyRendered = false;

    public MapboxView()
    {
        if (!alreadyRendered)
        {
            render();
        }
    }

    private void render()
    {
        H3 title = new H3("Mapbox Demo");
        add(title);

        addTopButtons();

        mapboxMap = new MapboxMap(GeoLocation.InitialView_Turku_NY, 2);
        add(mapboxMap);

        addBottomButtons();
    }

    private void addTopButtons()
    {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setAlignItems(Alignment.CENTER);

        Button zoomTurku = new Button("Turku", e -> mapboxMap.flyTo(GeoLocation.Turku));
        Button zoomCopenhagen = new Button("Copenhagen", e -> mapboxMap.flyTo(GeoLocation.Copenhagen));
        Button zoomAmsterdam = new Button("Amsterdam", e -> mapboxMap.flyTo(GeoLocation.Amsterdam));
        Button zoomParis = new Button("Paris", e -> mapboxMap.flyTo(GeoLocation.Paris));
        Button zoomNewYork = new Button("New York JFK", e -> mapboxMap.flyTo(GeoLocation.NewYork_JFK));

        Button zoomWorld = new Button("World", e ->
        {
            mapboxMap.flyTo(GeoLocation.Center);
            mapboxMap.zoomTo(1);
        });

        horizontalLayout.add(new Label("Zoom to:"), zoomTurku, zoomCopenhagen, zoomAmsterdam, zoomParis, zoomNewYork, zoomWorld);

        add(horizontalLayout);
    }

    private void addBottomButtons()
    {
        HorizontalLayout layerLayout = new HorizontalLayout();
        layerLayout.setAlignItems(Alignment.CENTER);

        HorizontalLayout animationsLayout = new HorizontalLayout();
        animationsLayout.setAlignItems(Alignment.CENTER);

        Button startAnimation = new Button("Circle animation", e -> mapboxMap.startAnimation());
        Button addLayer = new Button("Add layer", e -> mapboxMap.executeJs("addLayer(" + getExampleLayer() + ");"));
        // Button removeLayer = new Button("Remove layer", e -> mapboxMap.executeJS("removeLayer('" + getLayer().getId()+ "');"));
        Button hideLayer = new Button("Hide layer", e -> mapboxMap.executeJs("hideLayer('" + getExampleLayer().getId()+ "');"));
        Button unhideLayer = new Button("Unhide layer", e -> mapboxMap.executeJs("unhideLayer('" + getExampleLayer().getId()+ "');"));
        Button turkuNewYork = new Button("From Turku to New York", e ->
        {
            mapboxMap.drawOriginDestinationFlight(GeoLocation.Turku, GeoLocation.NewYork_JFK);
        });
        turkuNewYork.setId("replay");

        layerLayout.add(addLayer, hideLayer, unhideLayer);
        animationsLayout.add(new Label("Animations:"), turkuNewYork);

        add(layerLayout);
        add(animationsLayout);
    }

    public static Layer getExampleLayer()
    {
        // getLayer().toString() or getLayer().toString().replace("\"", "\'") is not needed
        Layer layer = new Layer("points", Layer.Type.symbol);

        Layer.Properties mapboxDCProperties = new Layer.Properties("National Bank", Sprite.Bank.toString());
        GeoLocation mapboxDCLocation = new GeoLocation(-77.03238901390978, 38.913188059745586);
        Layer.Feature mapboxDCFeature = new Layer.Feature("Feature", mapboxDCProperties, mapboxDCLocation);
        layer.addFeature(mapboxDCFeature);

        Layer.Properties mapboxDangerProperties = new Layer.Properties("Dangerous Animal", Sprite.Danger.toString());
        GeoLocation mapboxDangerLocation = new GeoLocation(30, -20);
        Layer.Feature mapboxDangerFeature = new Layer.Feature("Feature", mapboxDangerProperties, mapboxDangerLocation);
        layer.addFeature(mapboxDangerFeature);

        Layer.Properties mapboxSFProperties = new Layer.Properties("Helicopter Haven", Sprite.Helicopter.toString());
        GeoLocation mapboxSFLocation = new GeoLocation(-122.414, 37.776);
        Layer.Feature mapboxSFFeature = new Layer.Feature("Feature", mapboxSFProperties, mapboxSFLocation);
        layer.addFeature(mapboxSFFeature);

        // System.out.println(layer.toString(2));

        return layer;
    }
}
