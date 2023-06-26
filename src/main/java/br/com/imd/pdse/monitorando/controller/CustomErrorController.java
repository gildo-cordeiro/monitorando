package br.com.imd.pdse.monitorando.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PAGE = "error";

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(WebRequest request, Model model) {
        Map<String, Object> errorAttributesMap = errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE));

        model.addAttribute("status", errorAttributesMap.get("status"));
        model.addAttribute("error", errorAttributesMap.get("error"));
        model.addAttribute("message", errorAttributesMap.get("message"));
        model.addAttribute("path", errorAttributesMap.get("path"));
        model.addAttribute("timestamp", errorAttributesMap.get("timestamp"));

        return ERROR_PAGE;
    }
}
