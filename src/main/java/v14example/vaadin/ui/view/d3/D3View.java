package v14example.vaadin.ui.view.d3;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import v14example.vaadin.ui.MainLayout;
import elemental.json.JsonValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

// The tree.js example is from: http://bl.ocks.org/d3noob/8375092
// A more extensive example to integrate could be: http://bl.ocks.org/robschmuecker/7880033

@Route(value = "", layout = MainLayout.class)
@PageTitle("D3 View")
// This is not working or needed, since we add the correct library below.
// @JavaScript("https://d3js.org/d3.v5.min.js")
// @JavaScript("http://d3js.org/d3.v3.min.js")
public class D3View extends VerticalLayout
{
    private static Log log = LogFactory.getLog(D3View.class);

    public D3View()
    {
    }

    protected void onAttach(AttachEvent attachEvent)
    {
        super.onAttach(attachEvent);

        Label anchor = new Label("Placeholder");
        anchor.getElement().setProperty("innerHTML", "<anchor>");
        add(anchor);

        HorizontalLayout hl = new HorizontalLayout();
        hl.setJustifyContentMode(JustifyContentMode.START);

        hl.add(new D3Anchor()); // Creates the <div> anchor, which is referred in tree.js:43.
        add(hl);

        addTree(attachEvent);

        // addThreeBalls(attachEvent);
    }

    private void addTree(AttachEvent attachEvent)
    {
        Page page = attachEvent.getUI().getPage();

        page.addJavaScript("http://d3js.org/d3.v3.min.js");
        page.addJavaScript("js/tree.js"); // retrieves from /src/main/resources/META-INF/resources/frontend/js/tree.js
    }

    @Tag("div")
    public static class D3Anchor extends Component
    {
        public D3Anchor()
        {
            // Nothing is needed here, since just the <div> element is needed as anchor.
            // Relevant documentation: https://vaadin.com/docs/v13/flow/creating-components/tutorial-component-container.html
        }
    }

// -----------

    @Tag("svg")
    public static class ThreeBalls extends Component
    {
        public ThreeBalls()
        {
            getElement().setProperty("innerHTML",
                    "<svg width=\"720\" height=\"120\">"
                            + "  <circle cx=\"40\" cy=\"60\" r=\"10\"></circle>"
                            + "  <circle cx=\"80\" cy=\"60\" r=\"10\"></circle>"
                            + "  <circle cx=\"120\" cy=\"60\" r=\"10\"></circle>"
                            + "</svg>");
        }
    }

    private void addThreeBalls(AttachEvent attachEvent)
    {
        add(new ThreeBalls());

        ComboBox<String> colorSelect = new ComboBox<>();
        List<String> colors = new ArrayList<>();
        colors.add("Fuchsia");
        colors.add("Gold");
        colors.add("Teal");
        colors.add("Aqua");
        add(colorSelect);
        colorSelect.setItems(colors);
        Page page = attachEvent.getUI().getPage();
        colorSelect.addValueChangeListener(e ->
        {
            page.executeJs("d3.selectAll('circle').style('fill',$0)",
                    colorSelect.getValue());
        });
    }

// ----------------------
    private void showAlert()
    {
        Page page = UI.getCurrent().getPage();

        page.addHtmlImport("html/htmlimport.html");

        String d3js_location = "https://d3js.org/d3.v5.min.js";
        page.addJavaScript(d3js_location);

        String alertExpression = "alert( 'Hello, world!' );";

        PendingJavaScriptResult pendingResult = page.executeJs(alertExpression);

        pendingResult.then(new SerializableConsumer<JsonValue>()
        {
            @Override
            public void accept(JsonValue jsonValue)
            {
                log.info("result from JS:\n "+jsonValue);
            }
        });
    }
}
