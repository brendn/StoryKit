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
     */
    private String startingScene;

    private String picture;

    public Story(String title, String startingScene, String picture) {
        this.title = title;
        this.startingScene = startingScene;
        this.picture = picture;
    }

    public Story() {}

    public String getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStartingScene() {
        return startingScene;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        if (picture == null) {
            this.picture = "Forest";
        }
        return picture;
    }

}
