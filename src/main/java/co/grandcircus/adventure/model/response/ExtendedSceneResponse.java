package co.grandcircus.adventure.model.response;

import co.grandcircus.adventure.model.Scene;

public class ExtendedSceneResponse {

    public String description, storyID, storyTitle;

    public OptionResponse[] options;

    public ExtendedSceneResponse(String description, String storyID, String storyTitle, OptionResponse[] options) {
        this.description = description;
        this.storyTitle = storyTitle;
        this.storyID = storyID;
        this.options = options;
    }
}
