package co.grandcircus.adventure.model.response;

import co.grandcircus.adventure.model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryResponse {

    public String id, title, startingSceneId;

    public StoryResponse(String id, String title, String startingSceneId) {
        this.id = id;
        this.title = title;
        this.startingSceneId = startingSceneId;
    }

    public StoryResponse(Story story) {
        this.id = story.getId();
        this.title = story.getTitle();
        this.startingSceneId = story.getStartingScene();
    }

    public static List<StoryResponse> fromStories(List<Story> stories) {
        List<StoryResponse> out = new ArrayList<>();
        stories.forEach(story -> out.add(new StoryResponse(story)));
        return out;
    }

}