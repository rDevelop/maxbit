package us.rlit.web.controllers;

import org.springframework.ui.Model;
import us.rlit.api.models.MaxResponseErrorHandler;
import us.rlit.api.services.BaseService;

/**
 * Created by rob on 3/10/17.
 */
public interface ErrorController {
    default String showError(Model model, String error, BaseService service) {
        MaxResponseErrorHandler responseError = service.getMaxResponseError();
        model.addAttribute("responseError", responseError);
        model.addAttribute("reason", error);
        return "response-error";
    }
}
