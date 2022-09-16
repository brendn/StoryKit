package co.grandcircus.adventure.model.response;

import co.grandcircus.adventure.model.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is primarily used for our extended Scene information
 * See {@link co.grandcircus.adventure.controller.AdventureRestController#getExtendedSceneInfo(String)}
 *
 * Each option in a specific Scene will be put through the {@link #fromScenes(List)} method, converting those
 * Scene objects into OptionResponse objects.  This will contain information such as if the specific option is
 * the longest or shortest path to the end of the adventure.
 */
public class OptionResponse {

    public String text, sceneId;
    public boolean shortest, longest;

    public OptionResponse(String text, String sceneId, boolean shortest, boolean longest) {
        this.text = text;
        this.sceneId = sceneId;
        this.shortest = shortest;
        this.longest = longest;
    }

    public OptionResponse(String text, String sceneId) {
        this(text, sceneId, false, false);
    }

    public static List<OptionResponse> fromScenes(List<Scene> sceneList) {
        List<OptionResponse> out = new ArrayList<>();
        sceneList.forEach(scene -> out.add(new OptionResponse(scene.getDescription(), scene.getID())));
        return out;
    }

    public void setShortest(boolean shortest) {
        this.shortest = shortest;
    }

    public void setLongest(boolean longest) {
        this.longest = longest;
    }
}
