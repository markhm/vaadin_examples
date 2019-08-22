package v14example.vaadin.ui.view.d3;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.page.PendingJavaScriptResult;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.dom.DomEventListener;
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
public class D3View extends VerticalLayout
{
    private static Log log = LogFactory.getLog(D3View.class);

    private Page page = null;

    private boolean alreaderRendered = false;

    public D3View()
    {
        page = UI.getCurrent().getPage();
    }

    protected void onAttach(AttachEvent attachEvent)
    {
        super.onAttach(attachEvent);

        page = attachEvent.getUI().getPage();
        // retrieves from /src/main/resources/META-INF/resources/frontend/js/
        page.addStyleSheet("js/v4/style.css");

        page.addJavaScript("js/v4/d3.v4.js");
        // page.addJavaScript("js/v4/d3.v4.min.js");

        page.addJavaScript("js/v4/script.js");
        page.addJavaScript("js/v4/search.js"); // retrieves from /src/main/resources/META-INF/resources/frontend/js/
        page.addJavaScript("js/v4/tree.js"); // retrieves from /src/main/resources/META-INF/resources/frontend/js/

        addJsEventListener();

        addTree(attachEvent);

        // add(SimpleD3Example.createThreeBallsExample(attachEvent));
    }

    private void addTree(AttachEvent attachEvent)
    {
        this.setId("tree");

        if (!alreaderRendered)
        {
            this.add(getTreeMenu());

            page.executeJs("treeAlreadyRendered=false;renderTree();");
            alreaderRendered = true;
        }

        Page page = attachEvent.getUI().getPage();

        // page.addJavaScript("http://d3js.org/d3.v3.min.js");
        // page.addJavaScript("js/tree.js"); // retrieves from /src/main/resources/META-INF/resources/frontend/js/tree.js
        // page.executeJs("treeAlreadyRendered=false;renderTree();");
    }

    private void addJsEventListener()
    {
        // click, mouseover, mouseout, mousemove
        // load, change,
        // See: https://www.w3schools.com/js/js_htmldom_events.asp

        this.getElement().addEventListener("click", new DomEventListener()
        {
            @Override
            public void handleEvent(com.vaadin.flow.dom.DomEvent event)
            {
                log.info("***** Click event received. The event is:");
                log.info("phase: "+event.getPhase());
                log.info("type: "+event.getType());
                log.info("source: "+event.getSource());
                log.info("data: "+event.getEventData());
            }
        });
    }

    private HorizontalLayout getTreeMenu()
    {
        HorizontalLayout treeMenu = new HorizontalLayout();
        treeMenu.setWidthFull();
        treeMenu.setAlignItems(Alignment.END);
        treeMenu.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        Button expandButton = new Button("Expand all", e -> page.executeJs("myTree.expandTree()"));
        Button collapseButton = new Button("Collapse all", e -> page.executeJs("myTree.collapseTree()"));

        NumberField widthField = new NumberField("Width");

        widthField.setPattern("[0-9.,]*");
        widthField.setPreventInvalidInput(true);
        widthField.setHasControls(true);
        widthField.setValue((double) 90);
        widthField.setStep(10);
        widthField.setPattern("^[1-9][0-9]$");
        widthField.addValueChangeListener(e -> page.executeJs("myTree.updateWidth("+widthField.getValue()+")"));

        treeMenu.add(expandButton, collapseButton, widthField);

        return treeMenu;
    }

// -----------

// ----------------------
    private void showAlert()
    {
        Page page = UI.getCurrent().getPage();

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
