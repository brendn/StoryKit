package co.grandcircus.adventure.base;

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
    private String startingSceneID;

}
