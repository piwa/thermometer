package at.piwa.thermometer.webui.controller.advice;

import at.piwa.thermometer.webui.controller.TemperatureController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by philippwaibel on 22/01/16.
 */
@ControllerAdvice(assignableTypes = TemperatureController.class)
public class TemperatureControllerAdvice {

    @ModelAttribute("temperatureActiveSettings")
    public String cssActivePage() {
        return "active";
    }
}
