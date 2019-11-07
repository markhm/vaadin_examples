package v14example.vaadin.ui.view.apexcharts;

import com.github.appreciated.apexcharts.ApexCharts;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import v14example.vaadin.ui.MainLayout;

@PageTitle("Formlayout View")
@Route(value = "form-layout", layout = MainLayout.class)
public class FormLayoutView extends FormLayout
{
//    public FormLayoutView()
//    {
//        for (ApexCharts chart : generator.getCharts())
//        {
//            chart.getStyle().set("align-self", "center");
//            add(new Div(chart));
//        }
//    }
}
