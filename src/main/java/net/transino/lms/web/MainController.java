package net.transino.lms.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author lee
 * @since 5.0
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class MainController {
    @Value("#{config.wsdlLocation}")
    private String wsdlLocation;
    @Value("#{config.wsdlLocation2}")
    private String wsdlLocation2;
    @GetMapping(value = "/home")
    String home(ModelMap map) {
        map.addAttribute("wsdlLocation", this.wsdlLocation);
        map.addAttribute("wsdlLocation2", this.wsdlLocation2);
        return "index";
    }
}