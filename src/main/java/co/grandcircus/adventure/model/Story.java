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
    private String startingScene;

    private String picture;
    
    public Story(String title, String startingScene) {
        this.title = title;
        this.startingScene = startingScene;
        
    	}
    public Story(String title, String startingScene, String picture) {
        this.title = title;
        this.startingScene = startingScene;
        this.picture = picture;
        }
    
    public Story() {
    	
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

    public String getPicture() {
        if (picture == null) {
            this.picture = "Forest";
        }
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    
    
}
