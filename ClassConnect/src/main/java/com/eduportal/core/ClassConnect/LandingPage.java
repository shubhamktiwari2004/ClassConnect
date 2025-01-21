package com.eduportal.core.ClassConnect;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LandingPage {

    @RequestMapping("/")
    public String landingPage(Model model , HttpSession session) {
        return "LandingPage"; // Loads index.html from the templates folder
    }

}
