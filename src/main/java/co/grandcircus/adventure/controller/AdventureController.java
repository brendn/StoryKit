package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.repo.SceneRepository;

import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.api.PictureResponse;
import co.grandcircus.adventure.api.PictureService;
import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;

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
        model.addAttribute("options", options);

        PictureResponse newPic = service.getPicture("Ocean");
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

    // Start A Story On Home Page
    @PostMapping("/createStory")
    @ResponseStatus(HttpStatus.CREATED)
    public Story createStory(@RequestBody Story story) {
        stories.insert(story);
        return story;
    }

}
