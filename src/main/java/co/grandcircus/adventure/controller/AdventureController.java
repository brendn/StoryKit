package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.repo.SceneRepository;

import co.grandcircus.adventure.repo.StoryRepository;
import co.grandcircus.adventure.api.Photo;
import co.grandcircus.adventure.api.PictureResponse;
import co.grandcircus.adventure.api.PictureService;
import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@Controller
public class AdventureController {

    @Autowired
    private StoryRepository repo;
    
	@Autowired 
	private SceneRepository scene_repo;
	
	@Autowired
	private PictureService apiService;

	  @RequestMapping("/")
	    public String showIndex() {
		  	scene_repo.findAll();
	    	return "index";
	    }
	
    @RequestMapping("/home")
    public String index(Model model) {
        List<Story> options = repo.findAll();
        model.addAttribute("options", options);
        
		PictureResponse newPic = apiService.getPicture("Ocean");
		
		for (Photo photo: newPic.getPhotos()) {
			System.out.println(photo.getAvgColor());
			System.out.println(photo.getSrc().getOriginal());
		}
		
		
		System.out.println(newPic.getTotalResults());

    	return "home";
    }
    
    @RequestMapping("/create/{id}")
    public String createScene(@PathVariable("id") String id) {
    	return "createScene";
    }
    
    @PostMapping("/create/{id}")
    public String create(@PathVariable("id") String id, @RequestParam String title, @RequestParam String description, Model model) {
    	String parent = scene_repo.findById(id).orElseThrow(() -> new SceneNotFoundException(id)).getId();
    	Scene scene = new Scene(parent, title,description);
    	scene_repo.save(scene);
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
	
// story repo
//	@Autowired story_repo;
	
	@RequestMapping("/scene/{id}")
	public String displayScene(@PathVariable("id") String id, Model model){
		
//		The scene description.
//		The story id and title.
//		An array of the options, including the text and next sceneId for each
		
		Scene scene = scene_repo.findById(id).orElseThrow(() -> new SceneNotFoundException(id));		
		String title = scene.getTitle();
		String description = scene.getDescription();
        List<Scene> options = scene_repo.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Scene not found!"));
        
		model.addAttribute("id", id);
		model.addAttribute("description", description);
		model.addAttribute("title", title);
		model.addAttribute("options", options);
		return "index";
	}
// Start A Story On Home Page	
    @PostMapping("/createStory")
	@ResponseStatus(HttpStatus.CREATED)
	public Story createStory(@RequestBody Story story) {
		repo.insert(story);
		return story;
	}
    

    


}
