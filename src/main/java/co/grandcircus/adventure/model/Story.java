package co.grandcircus.adventure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("story")
public class Story {

    @Id
    private String id;

    /**
     * Title of the story
     */
    private String title;

    /**
     * First scene of this story's ID
     *
     * TODO: CRUD operations will return an ID instead of an actual object
     */
    private String startingScene;

    public Story(String title, String startingScene) {
        this.title = title;
        this.startingScene = startingScene;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStartingScene() {
        return startingScene;
    }

    public void setId(String id) {
        this.id = id;
    }
}
