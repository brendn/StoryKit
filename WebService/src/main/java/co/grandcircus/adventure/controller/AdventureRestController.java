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
import co.grandcircus.adventure.util.PathCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        // TODO : Once we create our adventure for this project, prevent it from being deleted here.
        stories.deleteAll();
        scenes.deleteAll();
    }

    @GetMapping("/scenes")
    public List<SceneResponse> getAllScenes() {
        return SceneResponse.fromScenes(scenes.findAll());
    }

    /**
     * Given a story ID, this will return information for that given story.
     */
    @GetMapping("/stories/{id}")
    public StoryResponse readOne(@PathVariable("id") String id) {
        Story story = stories.findById(id).orElseThrow(StoryNotFoundException::new);
        return new StoryResponse(story);
    }

    /**
     * Returns each of the options for the given scene.
     */
    @GetMapping("/scenes/{id}/options")
    public List<SceneResponse> getOptionsBySceneID(@PathVariable("id") String id) {
        List<Scene> scenesList = scenes.findByParentID(id).orElseThrow(SceneNotFoundException::new);
        return SceneResponse.fromScenes(scenesList);
    }

    /**
     * Returns extended information for the given scene, including which of the options has the longest/shortest
     * path.
     */
    @GetMapping("/scenes/{id}/extended")
    public ExtendedSceneResponse getExtendedSceneInfo(@PathVariable("id") String id) {
        // Find the target Scene we will be getting extended information for
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);

        // Find each of the options for the given scene
        List<Scene> optionsOld = scenes.findByParentID(id).orElseThrow(SceneNotFoundException::new);

        // Convert those options from Scene objects to OptionResponse objects
        List<OptionResponse> options = OptionResponse.fromScenes(optionsOld);

        // Initialize new PathCalculator, which will be used to find the shortest/longest path
        PathCalculator pathCalculator = new PathCalculator(this.scenes);

        // Find the shortest and longest path Scene IDs
        String shortestPathSceneID = pathCalculator.findShortestPath(scene).id;
        String longestPathSceneID = pathCalculator.findLongestPath(scene).id;

        // Iterate through each of the options, checking if the ID matches those of the shortest and longest paths
        for (OptionResponse option : options) {
            option.setShortest(option.sceneId.equals(shortestPathSceneID));
            option.setLongest(option.sceneId.equals(longestPathSceneID));
        }

        // Once we are done, convert this list into an array
        OptionResponse[] optionArray = options.toArray(new OptionResponse[options.size()]);

        // Get the title of the story this scene is for, needed for the ExtendedSceneResponse constructor
        String storyTitle = readOne(scene.getStoryId()).title;

        return new ExtendedSceneResponse(scene.getDescription(), scene.getStoryId(), storyTitle, optionArray);
    }

    @GetMapping("/scenes/{id}")
    public SceneResponse readOneScene(@PathVariable("id") String id) {
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);
        return new SceneResponse(scene);
    }
    
    @DeleteMapping("/scenes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteScene(@PathVariable("id") String id) {
        PathCalculator pathCalculator = new PathCalculator(this.scenes);
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);
        List<Scene> children = new ArrayList<>();
        pathCalculator.flattenChildren(scene, children);
        int i = 0;
        for (Scene child : children) {
            scenes.deleteById(child.id);
            i++;
        }
        return "Deleted " + i + " scenes.";
    }

    @DeleteMapping("/stories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable("id") String id) {
        // Delete the story itself
        stories.deleteById(id);
        // Iterate through all of our scenes
        for (Scene scene : scenes.findAll()) {
            // Make sure the scene has a story ID (... it should)
            if (scene.getStoryId() != null) {
                // If the story ID of the scene matches the story ID we are trying to get rid of, delete that scene
                if (scene.getStoryId().equals(id)) {
                    scenes.deleteById(scene.getID());
                }
            }
        }
        return "Deleted Story ID: " + id;
    }

    @PostMapping("/stories")
    @ResponseStatus(HttpStatus.CREATED)
    public StoryResponse create(@RequestBody StoryResponse storyResponse) {
        stories.insert(storyResponse.toStory());
        return storyResponse;
    }

    @PostMapping("/scenes")
    @ResponseStatus(HttpStatus.CREATED)
    public SceneResponse createScene(@RequestBody SceneResponse sceneResponse) {
        scenes.insert(sceneResponse.toScene());
        return sceneResponse;
    }

    @PutMapping("/scenes/{id}")
    public SceneResponse updateScene(@PathVariable("id") String id, @RequestBody SceneResponse sceneResponse) {
        Scene scene = scenes.findById(id).orElseThrow(SceneNotFoundException::new);
        scene.setTitle(sceneResponse.option);
        scene.setDescription(sceneResponse.description);
        scene.setStoryId(sceneResponse.storyId);
        scenes.save(scene);
        return sceneResponse;
    }

    @PutMapping("/stories/{id}")
    public StoryResponse updateStory(@PathVariable("id") String id, @RequestBody StoryResponse storyResponse) {
        Story story = stories.findById(id).orElseThrow(StoryNotFoundException::new);
        story.setTitle(storyResponse.title);
        story.setStartingScene(storyResponse.startingSceneId);
        stories.save(story);
        return storyResponse;
    }

    @ResponseBody
    @ExceptionHandler(StoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StoryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SceneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(SceneNotFoundException ex) {
        return ex.getMessage();
    }

}
