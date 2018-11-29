package at.piwa.thermometer.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by philippwaibel on 17/01/16.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model) {


        return "login";
    }

}
