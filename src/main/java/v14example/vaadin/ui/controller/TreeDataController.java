package v14example.vaadin.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/data")
public class TreeDataController
{
    public TreeDataController()
    {
    }

    // https://www.baeldung.com/spring-controller-vs-restcontroller

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody
    String getTree()
    {
        // StratificationUtil stratificationUtil = new StratificationUtil();

        // return stratificationUtil.stratify(getTreeList()).toString();

        return null;
    }

//    @GetMapping(value = "/{id}", produces = "application/json")
//    public @ResponseBody List<String> getTree(@PathVariable int id)
//    {
//        return getTreeList();
//    }

    private List<String> getTreeList()
    {
        List<String> result = new ArrayList<>();

        result.add("https://www.test.com/one/two/three");
        result.add("https://www.test.com/one/two/four");
        result.add("https://www.test.com/five/two/four");
        result.add("https://www.test.com/five/six/four");

        return result;
    }
}
