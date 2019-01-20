package at.piwa.thermometer.frontend.controller.advice;

import at.piwa.thermometer.frontend.controller.DashboardController;
import at.piwa.thermometer.frontend.controller.TemperatureHistoryController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by philippwaibel on 22/01/16.
 */
@ControllerAdvice(assignableTypes = DashboardController.class)
public class DashboardControllerAdvice {

    @ModelAttribute("dashboardActiveSettings")
    public String cssActivePage() {
        return "active";
    }
}
