package co.grandcircus.adventure.model.response;

public class ExtendedSceneResponse {

    public String description;

    public String storyID;

    public String storyTitle;

    public OptionResponse[] options;

    public ExtendedSceneResponse(String description, String storyID, String storyTitle, OptionResponse[] options) {
        this.description = description;
        this.storyTitle = storyTitle;
        this.storyID = storyID;
        this.options = options;
    }
}
