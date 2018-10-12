package us.rlit.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by rob on 11/19/16.
 */
@Controller
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String root() {
        logger.info("/");
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        logger.info("/index");
        model.addAttribute("message", "Welcome");
        model.addAttribute("fragment", "welcome");
        return "index";
    }

    @RequestMapping("/index/{fragment}")
    public String index(@PathVariable String fragment, Model model) {
        logger.info("/index/{{}}", fragment);
        if(fragment == null) {
            model.addAttribute("message", "Welcome");
            model.addAttribute("fragment", fragment);
        }
        return "index";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        logger.info("/login-error");
        model.addAttribute("message", "Login Error");
        model.addAttribute("fragment", "loginError");
        logger.info("login-error");
        return "index";
    }
}
