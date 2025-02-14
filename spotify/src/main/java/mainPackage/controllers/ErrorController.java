package mainPackage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String showError(@RequestParam(name = "message", required = false) String message, Model model) {
        model.addAttribute("errorMessage", message != null ? message : "An unknown error occurred.");
        return "error";
    }
}
