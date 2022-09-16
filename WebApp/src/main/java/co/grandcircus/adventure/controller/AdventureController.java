package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.exception.StoryNotFoundException;
import co.grandcircus.adventure.repo.SceneRepository;

import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.pexels.PictureService;
import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;

import java.util.ArrayList;
import java.util.List;

import co.grandcircus.adventure.util.PathCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            urls.add(service.getPicture(story.getPictureURL()).getSmallURL());
        }

        model.addAttribute("options", options);
        model.addAttribute("urls", urls);

        return "home";
    }

    @RequestMapping("/create/{id}")
    public String createScene(@PathVariable("id") String id) {
        return "createScene";
    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") String id, @RequestParam String title, @RequestParam String description,
                         Model model) {
        Scene parent = scenes.findById(id).orElseThrow(() -> new SceneNotFoundException(id));
        Scene scene = new Scene(parent.id, title, description);
        scene.setStoryId(parent.getStoryId());
        scenes.save(scene);
        return "redirect:/scene/{id}";
    }

    @RequestMapping("/scene/{id}")
    public String displayScene(@PathVariable("id") String id, Model model) {
        Scene scene = scenes.findById(id).orElseThrow(() -> new SceneNotFoundException(id));
        String title = scene.getTitle();
        String description = scene.getDescription();
        List<Scene> options = scenes.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Scene not found!"));

        if (options.isEmpty()) {
            description = description + "<br/>The End.";
        }

        model.addAttribute("scene", scene);
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
    public String createStory(Model model, @RequestParam("title") String title, @RequestParam("picture") String picture,
                              @RequestParam("option") String option, @RequestParam("description") String description) {
        Scene newScene = new Scene(null, option, description);
        scenes.save(newScene);
        Story newStory = new Story(title, newScene.id, picture);
        stories.save(newStory);
        newScene.setStoryId(newStory.getID());
        scenes.save(newScene);
    	
        List<String> urls = service.getPicture(newStory.getPicturePrompt()).getTenPhotos();
        model.addAttribute("urls", urls);
        model.addAttribute("id", newStory.getID());

        return "selectPictures";
    }
    
    @RequestMapping("/selectPictures")
    public String selectPic() {
        return "selectPictures";
    }

    @PostMapping("/selectPictures")
    public String grabPictures(@RequestParam String searchType, @RequestParam String id) {
    	Story updatedPic = stories.findById(id).orElseThrow(StoryNotFoundException::new);
    	updatedPic.setPictureURL(searchType);
    	stories.save(updatedPic);
        return "redirect:/";
    }
    

    @RequestMapping("/editScene/{id}")
    public String editScene(@PathVariable("id") String id, Model model) {
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);
        model.addAttribute("id", id);
        model.addAttribute("title", scene.getTitle());
        model.addAttribute("description", scene.getDescription());
        return "editScene";
    }

    @PostMapping("/editScene/{id}")
    public String editScene(@RequestParam("id") String id, @RequestParam("title") String title,
                            @RequestParam("description") String description) {
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);
        scene.setTitle(title);
        scene.setDescription(description);
        scenes.save(scene);
        return "redirect:/scene/" + id;
    }

    @RequestMapping("/editStory/{id}")
    public String editStory(@PathVariable("id") String id, Model model) {
        Story story = stories.findById(id).orElseThrow(StoryNotFoundException::new);
        model.addAttribute("title", story.getTitle());
        model.addAttribute("picture", story.getPicturePrompt());
        model.addAttribute("id", id);
        return "editStory";
    }

    @PostMapping("/editStory")
    public String editStory(@RequestParam("storyID") String storyID, @RequestParam("title") String title,
                            @RequestParam("picture") String picture, Model model) {
        Story story = stories.findById(storyID).orElseThrow(StoryNotFoundException::new);
        story.setTitle(title);
        story.setPicturePrompt(picture);
        stories.save(story);

        List<String> urls = service.getPicture(story.getPicturePrompt()).getTenPhotos();
        model.addAttribute("urls", urls);
        model.addAttribute("id", story.getID());

        return "selectPictures";
    }

    @RequestMapping("/deleteScene/{id}")
    public String deleteScene(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "deleteScene";
    }

    @RequestMapping("/deleteStory/{id}")
    public String deleteStory(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        return "deleteStory";
    }

    @PostMapping("/deleteScene")
    public String deleteScene(@RequestParam("id") String id, @RequestParam("confirm") String confirm) {
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);
        Scene parent = scenes.findById(scene.getParentID()).orElseThrow(SceneNotFoundException::new);
        if (confirm != null) {
            PathCalculator calculator = new PathCalculator(this.scenes);
            List<Scene> children = new ArrayList<>();
            calculator.flattenChildren(scene, children);
            for (Scene child : children) {
                scenes.deleteById(child.id);
            }
            scenes.deleteById(id);
        }

        return "redirect:/scene/" + parent.id;
    }

    @PostMapping("/deleteStory")
    public String deleteStory(@RequestParam("id") String id, @RequestParam("confirm") String confirm) {
        if (confirm != null) {
            stories.deleteById(id);
            List<Scene> children = scenes.findByStoryId(id).orElseThrow(SceneNotFoundException::new);
            children.forEach(scene -> scenes.deleteById(scene.id));
        }
        return "redirect:/";
    }
}
