package v14example.vaadin.ui.view.d3;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;

import java.util.ArrayList;
import java.util.List;

public class SimpleD3Example
{
    public static VerticalLayout createThreeBallsExample(AttachEvent attachEvent)
    {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(new ThreeBalls());

        ComboBox<String> colorSelect = new ComboBox<>();
        List<String> colors = new ArrayList<>();
        colors.add("Fuchsia");
        colors.add("Gold");
        colors.add("Teal");
        colors.add("Aqua");
        verticalLayout.add(colorSelect);
        colorSelect.setItems(colors);
        Page page = attachEvent.getUI().getPage();
        colorSelect.addValueChangeListener(e ->
        {
            page.executeJs("d3.selectAll('circle').style('fill',$0)",
                    colorSelect.getValue());
        });

        return verticalLayout;
    }

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
}
