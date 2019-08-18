package v14example.vaadin.ui.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class CommonViewElements
{
    private CommonViewElements()
    {}

    public static void setGridLookAndfeel(Grid grid)
    {
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
    }

    public static Component createHeader(String viewName)
    {
        HorizontalLayout header = new HorizontalLayout();
        header.add(new H3("KDI "+viewName));

        // header.setAlignItems(FlexComponent.Alignment.CENTER);

        header.setSpacing(true);
        header.setWidthFull();
        // header.setAlignItems(FlexComponent.Alignment.STRETCH);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        return header;
    }

    public static void showOutput(String text, Component content, HasComponents outputContainer)
    {
        HtmlComponent p = new HtmlComponent(Tag.P);
        p.getElement().setText(text);
        outputContainer.add(p);
        outputContainer.add(content);
    }

    public static void openDialogWindow(String headerText, List<String> messages)
    {
        openDialogWindow(headerText,messages, 700, 550);
    }

    public static void openDialogWindow(String headerText, List<String> messages, int height, int width)
    {
        VerticalLayout messageStructure = new VerticalLayout();
        messageStructure.add(new H3(headerText));

        for (String message : messages)
        {
            messageStructure.add(new Label(message));
        }

        Dialog dialogWindow = new Dialog();
        dialogWindow.add(messageStructure);

        dialogWindow.setWidth(width+"px");
        dialogWindow.setHeight(height+"px");
        dialogWindow.open();
    }
}
