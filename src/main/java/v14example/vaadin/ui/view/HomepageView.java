package v14example.vaadin.ui.view;

import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import v14example.vaadin.ui.MyAppLayoutRouterLayout;


@Route(value = "", layout = MyAppLayoutRouterLayout.class)
@PageTitle("Example application")
public class HomepageView extends VerticalLayout
{
    private Dialog tutorialDialog = new Dialog();

    public HomepageView()
    {
        HorizontalLayout layout = new HorizontalLayout();
        HorizontalLayout[] inners = new HorizontalLayout[3];

        for (int i = 0; i < inners.length; i++)
        {
            inners[i] = new HorizontalLayout();
            inners[i].setHeight("100px");
            inners[i].setWidthFull();
            inners[i].getStyle().set("border", "1px solid #9E9E9E");
            inners[i].setJustifyContentMode(JustifyContentMode.EVENLY);
            inners[i].setDefaultVerticalComponentAlignment(Alignment.CENTER);
            inners[i].setPadding(true);

            layout.add(inners[i]);
        }

        H3[] h3Label = new H3[3];
        h3Label[0] = new H3("Explore / Model");
        h3Label[0].getStyle().set("color", "#FFFFFF");
        inners[0].add(new HorizontalLayout(h3Label[0]));

        h3Label[1] = new H3("Simulate");
        h3Label[1].getStyle().set("color", "#FFFFFF");
        inners[1].add(new HorizontalLayout(h3Label[1]));

        h3Label[2] = new H3("Visualize");
        h3Label[2].getStyle().set("color", "#FFFFFF");
        inners[2].add(new HorizontalLayout(h3Label[2]));

        Icon icon = new Icon(VaadinIcon.SEARCH);
        icon.setSize("70px");
        inners[0].add(icon);
        icon = new Icon(VaadinIcon.AUTOMATION);
        icon.setSize("70px");
        inners[1].add(icon);
        icon = new Icon(VaadinIcon.CHART);
        icon.setSize("70px");
        inners[2].add(icon);
        inners[0].getStyle().set("background-color", "#1A5276"); // https://htmlcolorcodes.com/
        inners[1].getStyle().set("background-color", "#196F3D");
        inners[2].getStyle().set("background-color", "#7B241C");
        inners[0].getStyle().set("color", "#FFFFFF");
        inners[1].getStyle().set("color", "#FFFFFF");
        inners[2].getStyle().set("color", "#FFFFFF");

        layout.setWidthFull();
        layout.setHeight("100px");
        setPadding(true);
        add(layout);
        add(new H3("Example workbench"));
        add(new Paragraph("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque euismod commodo magna, eget porttitor quam mattis ac. " +
                "Sed iaculis, dolor sit amet faucibus vestibulum, purus mauris scelerisque tellus, ac fringilla odio erat interdum urna. " +
                "Praesent turpis velit, ornare in pulvinar nec, auctor non enim. Proin diam nibh, egestas id odio tempor, ultrices faucibus lacus. " +
                "Sed ultrices finibus enim. Suspendisse scelerisque sapien est, quis ultricies ipsum varius nec. Nunc congue aliquam neque quis auctor. " +
                "Proin mattis erat varius diam porta, vel mollis augue faucibus. Sed vehicula augue id libero gravida accumsan. Sed at vestibulum lorem. " +
                "Nam vel libero nec felis lacinia posuere et id sem. Quisque bibendum convallis nulla, eget lobortis turpis tristique at. " +
                "Cras dictum laoreet enim non sollicitudin. Sed felis nunc, varius nec erat in, cursus imperdiet sapien.\n"));

        Image image = new Image();
        image.setSrc("images//home_page.png");
        image.setWidthFull();
        // add(image);

        // Tutorial
        tutorialDialog.add(new Label("Video goes here"));
        tutorialDialog.setWidth("1100px");
        tutorialDialog.setHeight("700px");
//        //HorizontalLayout buttonHolder = new HorizontalLayout(playButton());
//        buttonHolder.setWidthFull();
//        buttonHolder.setHeight("380px");
//        buttonHolder.setJustifyContentMode(JustifyContentMode.CENTER);
//        add(buttonHolder);

        // add("Study workflow is detailed below");
        setSizeFull();

    }

//    private Button playButton()
//    {
//        Image thumbnail = new Image("images//video//play.png", "Play tutorial");
//        thumbnail.setWidth("380px");
//        thumbnail.setHeight("210px");
//        Button play = new Button(thumbnail);
//        play.addClickListener(e -> tutorialDialog.open());
//        play.setWidth("380px");
//        play.setHeight("240px");
//        play.getStyle().set("cursor", "pointer");
//        return play;
//    }
}