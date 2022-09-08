package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.repo.SceneRepository;

import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.api.PictureResponse;
import co.grandcircus.adventure.api.PictureService;
import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@Controller
public class AdventureController {

    @Autowired
    private StoryRepository stories;

    @Autowired
    private SceneRepository scenes;

    @Autowired
    private PictureService service;

    @RequestMapping("/")
    public String index(Model model) {
        List<Story> options = stories.findAll();
        List<String> urls = new ArrayList<>();
        for (Story story : options) {
            urls.add(service.getPicture(story.getPicture()).getSmallURL());
        }

        PictureResponse newPic = service.getPicture("Ocean");
        model.addAttribute("options", options);
        model.addAttribute("urls", urls);
        return "home";
    }

    @RequestMapping("/create/{id}")
    public String createScene(@PathVariable("id") String id) {
        return "createScene";
    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") String id, @RequestParam String title, @RequestParam String description, Model model) {
        String parent = scenes.findById(id).orElseThrow(() -> new SceneNotFoundException(id)).getId();
        Scene scene = new Scene(parent, title, description);
        scenes.save(scene);
        return "createScene";
    }

    @RequestMapping("/scene/{id}")
    public String displayScene(@PathVariable("id") String id, Model model) {
        Scene scene = scenes.findById(id).orElseThrow(() -> new SceneNotFoundException(id));
        String title = scene.getTitle();
        String description = scene.getDescription();
        List<Scene> options = scenes.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Scene not found!"));

        model.addAttribute("id", id);
        model.addAttribute("description", description);
        model.addAttribute("title", title);
        model.addAttribute("options", options);
        return "scene";
    }

    @RequestMapping("/createStory")
    public String createPage() {
    		

        return "createStory";
    }

    // Start A Story On Home Page
    @PostMapping("/createStory")
//    @ResponseStatus(HttpStatus.CREATED)
    public String createStory(@RequestParam("title") String title, @RequestParam("picture") String picture, @RequestParam("option") String option, @RequestParam("description") String description) {

    	Story newStory = new Story(title, option,picture);
    	Scene newScene = new Scene(null,option,description);
    	
    	stories.insert(newStory);
    	scenes.insert(newScene);
    	
        return "redirect:/";
    }

}
