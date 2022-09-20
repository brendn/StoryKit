package co.grandcircus.adventure.model.response;

import co.grandcircus.adventure.model.Scene;

import java.util.ArrayList;
import java.util.List;

public class SceneResponse {

    public String id, storyId, parentId, option, description;

    public SceneResponse(Scene scene) {
        this.id = scene.getID();
        this.storyId = scene.getStoryId();
        this.parentId = scene.getParentID();
        this.option = scene.getTitle();
        this.description = scene.getDescription();
    }

    public static List<SceneResponse> fromScenes(List<Scene> scenes) {
        List<SceneResponse> out = new ArrayList<>();
        scenes.forEach(scene -> out.add(new SceneResponse(scene)));
        return out;
    }

    public Scene toScene() {
        return new Scene(parentId, option, description);
    }
}
