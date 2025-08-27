package com.github.erodriguezg.grpcjavalab.web.controllers.gestionar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionar/comunidad/{idComunidad}/persona")
public class PersonaController {

    @GetMapping
    public String irGestionarPersona(@PathVariable("idComunidad") String idComunidad, Model model) {
        model.addAttribute("idComunidad", idComunidad);
        return "gestionar/persona.html";
    }

}
