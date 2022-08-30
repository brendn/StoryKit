package co.grandcircus.adventure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("scene")
public class Scene {

    @Id
    public String id;

    /**
     * Previous scene, if this is the starting scene make this null
     */
    private String parentID;

    private List<Scene> options = new ArrayList<>();

    // "option"
    private String title;

    private String description;

    public Scene(String parentID, String title, String description) {
        this.parentID = parentID;
        this.title = title;
        this.description = description;
    }

    public Scene(Scene parent, String title, String description) {
        this(parent.id, title, description);
    }

    public void addOption(Scene scene) {
        this.options.add(scene);
    }

    public void addOption(String title, String description) {
        options.add(new Scene(this.id, title, description));
    }

    public Scene getOption(String id) {
        for (Scene scene : options) {
            if (scene.id.equals(id)) {
                return scene;
            }
        }
        return null;
    }

    public String getParent() {
        return parentID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Scene> getOptions() {
        return options;
    }
}
