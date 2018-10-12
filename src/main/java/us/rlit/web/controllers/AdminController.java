package us.rlit.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rob on 11/19/16.
 */
@Controller
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/admin")
    public String adminRoot() {
        logger.info("/admin");
        return "admin/index";
    }

    @RequestMapping("/admin/index")
    public String adminIndex(Model model) {
        logger.info("/admin/index");
        model.addAttribute("message", "Welcome");
        model.addAttribute("fragment", "welcome");
        return "admin/index";
    }

    @RequestMapping("/admin/security")
    public String index(Model model) {
        logger.info("/admin/security");
        return "admin/security";
    }
}
