package co.grandcircus.adventure.controller;

import co.grandcircus.adventure.StoryRepository;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        repo.deleteAll();
        // Setup root scene
        Scene test = new Scene(null, "Test Scene", "This is a test scene. What do?");
        // Option for root scene
        Scene continueOption = new Scene(test, "Continue", "You have chosen to continue");
        // Add an empty option to this scene
        continueOption.addOption("Exit", "Exit the game");
        // Add continue option to the root scene
        test.addOption(continueOption);
        // Add exit option to root scene
        test.addOption("Exit", "You have chosen to exit");

        // Add the test scene to a new story
        repo.insert(new Story("Test Story", test));
    }

}
