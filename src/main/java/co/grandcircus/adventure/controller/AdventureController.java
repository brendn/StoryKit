package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.SceneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdventureController {

    @Autowired
    private SceneRepository repo;

    @RequestMapping("/")
    public String home() {
        return "home";
    }
    
    @RequestMapping("/createScene")
    public String create() {
    	return "createScene";
    }

}
