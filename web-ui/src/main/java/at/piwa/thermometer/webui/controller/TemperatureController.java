package at.piwa.thermometer.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by philippwaibel on 18/01/16.
 */
@Controller
public class TemperatureController {

    @RequestMapping(value = "/temperatures", method = RequestMethod.GET)
    public String list(Model model){
        return "temperatures";
    }

}
