import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.grandcircus.adventure.model.Scene;

@Controller
public class SceneController {
	// scene repo
//	@Autowired scene_repo;
	
	// story repo
//	@Autowired story_repo;
	
	@RequestMapping("/home/{id}")
	public String displayScene(@PathVariable("id") String id, Model model){
		
//The scene description.
//		The story id and title.
//		An array of the options, including the text and next sceneId for each
		
		//String title = scene.getTitle
		//String description = scene.getDescription
		// List<Scene> options = scene.getOptions

//		model.addAttribute("description", description);
//		model.addAttribute("title", title);
//		model.addAttribute("options", options);		
		return "home";
	}

}
