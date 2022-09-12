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

    // "option"
    private String title;

    private String description;

    private String storyId;

    public Scene() {
        super();
    }
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public Scene(String parentID, String title, String description) {
        this.parentID = parentID;
        this.title = title;
        this.description = description;
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
}
