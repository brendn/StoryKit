package co.grandcircus.adventure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("scene")
public class Scene {

    @Id
    public String id;

    /**
     * Previous scene, if this is the starting scene make this null
     */
    private String parentID;

    /**
     * The title of the scene, which will appear on the list of options for a given scene
     */
    private String title;

    /**
     * The description of the scene, which will display when you view the scene directly
     */
    private String description;

    /**
     * The ID of the story that this scene belongs to
     */
    private String storyId;

    public Scene() {
        super();
    }

    public Scene(String parentID, String title, String description) {
        this.parentID = parentID;
        this.title = title;
        this.description = description;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getParentID() {
        return this.parentID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
