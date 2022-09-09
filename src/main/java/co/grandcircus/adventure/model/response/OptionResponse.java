package co.grandcircus.adventure.model.response;

import co.grandcircus.adventure.model.Scene;

import java.util.ArrayList;
import java.util.List;

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
        sceneList.forEach(scene -> out.add(new OptionResponse(scene.getDescription(), scene.getId())));
        return out;
    }
}
