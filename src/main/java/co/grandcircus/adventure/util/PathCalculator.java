package co.grandcircus.adventure.util;

import co.grandcircus.adventure.exception.SceneNotFoundException;
import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.repo.SceneRepository;

import java.util.ArrayList;
import java.util.List;

public class PathCalculator {

    private final SceneRepository scenes;

    public PathCalculator(SceneRepository scenes) {
        this.scenes = scenes;
    }

    /**
     * Given a scene, this will recursively iterate through the child scenes until it reaches the end,
     * adding each to the output list.
     *
     * @param parent The parent scene that we will be iterating through
     * @param output A list containing each child scene to the parent scene, as well as their child scenes, etc.
     *
     * @return A list containing all the scenes under the given parent scene.
     */
    private List<Scene> flattenChildren(Scene parent, List<Scene> output) {
        // Add the given scene to the output list
        output.add(parent);
        // Obtain all the options for the given scene
        List<Scene> options = getOptions(parent.id);
        // If there are options to choose from, run this again but for each of those options until we reach an end.
        if (!options.isEmpty()) {
            options.forEach(scene -> flattenChildren(scene, output));
        }
        // Return the output list of child scenes
        return output;
    }

    public Scene findLongestPath(Scene scene) {
        int steps = 0;
        Scene out = null; // Output scene
        // Check each option for the given scene
        for (Scene option : getOptions(scene.id)) {
            List<Scene> children = new ArrayList<>();
            // Find how many scenes follow the given option
            int stepsToEnd = flattenChildren(option, children).size();
            // If the steps are greater than the current longest path, update the output
            if (stepsToEnd > steps) {
                steps = stepsToEnd;
                out = option;
            }
        }
        // Return the scene with the most amount of steps
        return out;
    }

    public Scene findShortestPath(Scene scene) {
        int steps = Integer.MAX_VALUE;
        Scene out = null; // Output scene
        // Check each option for the given scene
        for (Scene option : getOptions(scene.id)) {
            List<Scene> children = new ArrayList<>();
            // Find how many scenes follow the given option
            int stepsToEnd = flattenChildren(option, children).size();
            // If the steps are less than the current shortest path, update the output
            if (stepsToEnd < steps) {
                steps = stepsToEnd;
                out = option;
            }
        }
        // Return the scene with the least amount of steps
        return out;
    }

    /**
     * @return A List containing each of the options for the given Scene id
     */
    public List<Scene> getOptions(String id) {
        return scenes.findByParentID(id).orElseThrow(() -> new SceneNotFoundException("Scene not found!"));
    }

}
