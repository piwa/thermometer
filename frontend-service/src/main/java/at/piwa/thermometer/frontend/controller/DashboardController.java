package at.piwa.thermometer.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;

/**
 * Created by philippwaibel on 17/01/16.
 */
@Controller
public class DashboardController {

    @RequestMapping("/")
    public String home(Model model) {
        return "dashboard";
    }

}
