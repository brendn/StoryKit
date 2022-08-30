package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.repo.SceneRepository;
import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.exception.StoryNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//TODO: Closely match the guidelines, eg use String instead of direct object references in story.startingScene etc
@RestController
public class AdventureRestController {

    @Autowired
    private StoryRepository stories;

    @Autowired
    private SceneRepository scenes;

    @GetMapping("/stories")
    public List<Story> getStories() {
        return stories.findAll();
    }

    @GetMapping("/reset")
    public void reset() {
        stories.deleteAll();
        // Setup root scene
        Scene test = new Scene("-1", "Test Scene", "This is a test scene. What do?");
        // Option for root scene
        Scene continueOption = new Scene(test, "Continue", "You have chosen to continue");
        // Add an empty option to this scene
        continueOption.addOption(new Scene(continueOption,"Exit", "Exit the game"));
        // Add continue option to the root scene
        test.addOption(continueOption);
        // Add exit option to root scene
        test.addOption("Exit", "You have chosen to exit");

        scenes.save(test);

        // Add the test scene to a new story
        stories.save(new Story("Test Story", test.id));
    }

    @GetMapping("/scenes")
    public List<Scene> getAllScenes() {
        return scenes.findAll();
    }

    @GetMapping("/stories/{id}")
    public Story readOne(@PathVariable("id") String id) {
        return stories.findById(id).orElseThrow(() -> new StoryNotFoundException("Story not found!"));
    }

    @GetMapping("/scenes/{id}")
    public Scene readOneScene(@PathVariable("id") String id) {
        return scenes.findById(id).orElseThrow(() -> new SceneNotFoundException("Story not found!"));
    }

    @DeleteMapping("/stories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        stories.deleteById(id);
    }

    @ResponseBody
    @ExceptionHandler(StoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StoryNotFoundException ex) {
        return ex.getMessage();
    }
}
