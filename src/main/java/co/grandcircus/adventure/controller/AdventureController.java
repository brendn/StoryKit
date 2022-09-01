package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.repo.SceneRepository;
import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdventureController {

    @Autowired
    private StoryRepository repo;

    @RequestMapping("/")
    public String home() {
        reset();
        return "home";
    }
    
    @RequestMapping("/create")
    public String create() {
    	return "createScene";
    }

    /**
     * Generates the following flow:
     *
     * Test Scene                               // Test scene title
     * + This is a test scene.  What do?        // Test scene description, shown on the page with options
     *      - Continue                          // Option #1 - Just the title
     *      + You have chosen to continue       // Description for Option #1
     *          - Exit                          // Once Option #1 is chosen, this option will be presentsd.
     *          + Exit the game                 // Description for this option
     *      - Exit                              // Option #2 - Just the title
     *      + You have chosen to exit           // Description for Option #2
     */
    private void reset() {
//        repo.deleteAll();
//        // Setup root scene
//        Scene test = new Scene(null, "Test Scene", "This is a test scene. What do?");
//        // Option for root scene
//        Scene continueOption = new Scene(test.id, "Continue", "You have chosen to continue");
//
//        // Add the test scene to a new story
//        repo.insert(new Story("Test Story", test.id));
    }
    
	@Autowired 
	private SceneRepository scene_repo;
	
	// story repo
//	@Autowired story_repo;
	
	@RequestMapping("/home/{id}")
	public String displayScene(@PathVariable("id") String id, Model model){
		
//The scene description.
//		The story id and title.
//		An array of the options, including the text and next sceneId for each
		
		Scene scene = scene_repo.findById(id).orElseThrow(() -> new SceneNotFoundException(id));		
		String title = scene.getTitle();
		String description = scene.getDescription();
        List<Scene> options = scene_repo.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Scene not found!"));

		model.addAttribute("description", description);
		model.addAttribute("title", title);
		model.addAttribute("options", options);
		return "home";
	}

}
