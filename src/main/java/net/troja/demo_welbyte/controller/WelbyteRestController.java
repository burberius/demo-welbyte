package net.troja.demo_welbyte.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eligibility")
public class WelbyteRestController {
    @GetMapping
    public String eligibility() {
        return "eligibility";
    }
}
