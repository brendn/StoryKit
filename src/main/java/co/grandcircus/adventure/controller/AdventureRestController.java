package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.response.ExtendedSceneResponse;
import co.grandcircus.adventure.model.response.OptionResponse;
import co.grandcircus.adventure.model.response.SceneResponse;
import co.grandcircus.adventure.model.response.StoryResponse;
import co.grandcircus.adventure.repo.SceneRepository;
import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.exception.StoryNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdventureRestController {

    @Autowired
    private StoryRepository stories;

    @Autowired
    private SceneRepository scenes;

    @GetMapping("/stories")
    public List<StoryResponse> getStories() {
        return StoryResponse.fromStories(stories.findAll());
    }

    @GetMapping("/reset")
    public void reset() {
        stories.deleteAll();
        scenes.deleteAll();
    }

    @GetMapping("/scenes")
    public List<SceneResponse> getAllScenes() {
        return SceneResponse.fromScenes(scenes.findAll());
    }

    @GetMapping("/stories/{id}")
    public StoryResponse readOne(@PathVariable("id") String id) {
        Story story = stories.findById(id).orElseThrow(() -> new StoryNotFoundException("Story not found!"));
        return new StoryResponse(story);
    }

    @GetMapping("/scenes/{id}/options")
    public List<SceneResponse> getOptionsBySceneID(@PathVariable("id") String id) {
        List<Scene> scenesList = scenes.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Scene not found!"));
        return SceneResponse.fromScenes(scenesList);
    }

    @GetMapping("/scenes/{id}/extended")
    public ExtendedSceneResponse getExtendedSceneInfo(@PathVariable("id") String id) {
        Scene scene = scenes.findById(id).orElseThrow(() -> new SceneNotFoundException("idk"));
        List<Scene> optionsOld = scenes.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Not here chief"));
        List<OptionResponse> options = OptionResponse.fromScenes(optionsOld);
        OptionResponse[] optionArray = options.toArray(new OptionResponse[options.size()]);
        String storyTitle = readOne(scene.getStoryId()).title;

        return new ExtendedSceneResponse(scene.getDescription(), scene.getStoryId(), storyTitle, optionArray);
    }

    @GetMapping("/scenes/{id}")
    public SceneResponse readOneScene(@PathVariable("id") String id) {
        Scene scene = scenes.findById(id).orElseThrow(() -> new SceneNotFoundException("Story not found!"));
        return new SceneResponse(scene);
    }

    @DeleteMapping("/stories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id) {
        stories.deleteById(id);
        for (Scene scene : scenes.findAll()) {
            if (scene.getStoryId() != null) {
                if (scene.getStoryId().equals(id)) {
                    scenes.deleteById(scene.getId());
                }
            }
        }
        return "Deleted Story ID: " + id;
    }

    @ResponseBody
    @ExceptionHandler(StoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StoryNotFoundException ex) {
        return ex.getMessage();
    }

}
