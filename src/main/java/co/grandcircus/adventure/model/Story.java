package co.grandcircus.adventure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Scene startingScene;

    public Story(String title, Scene startingScene) {
        this.title = title;
        this.startingScene = startingScene;
    }

    public Scene getStartingScene() {
        return startingScene;
    }

    public String getTitle() {
        return title;
    }

    public String getID() {
        return id;
    }
}
