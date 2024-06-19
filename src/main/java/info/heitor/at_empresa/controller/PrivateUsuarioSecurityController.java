package info.heitor.at_empresa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class PrivateUsuarioSecurityController {

    @GetMapping("/oimundo")
    public String oimundo() {
        return "oi mundo";
    }
}
