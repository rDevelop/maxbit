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
public class ViewsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/views")
    public String views(Model model) {
        logger.info("/views/index");
        return "views/index";
    }

    @RequestMapping("/views/index")
    public String views_index(Model model) {
        logger.info("/views/index");
        return "views/index";
    }
}
