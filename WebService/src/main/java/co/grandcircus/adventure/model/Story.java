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

    private String pictureURL;

    private String picturePrompt;

    public Story(String title, String startingScene, String picturePrompt) {
        this.title = title;
        this.startingScene = startingScene;
        this.picturePrompt = picturePrompt;
    }

    public Story() {}

    public String getPicturePrompt() {
        return picturePrompt;
    }

    public void setPicturePrompt(String picturePrompt) {
        this.picturePrompt = picturePrompt;
    }

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

    public void setStartingScene(String startingScene) {
        this.startingScene = startingScene;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPictureURL() {
        if (pictureURL == null) {
            this.pictureURL = "Forest";
        }
        return pictureURL;
    }

}
