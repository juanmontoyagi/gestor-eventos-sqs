package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class PruebaController {

    @PostMapping("/prueba")
    public String metodito(){
        return "hola";
    }
}
