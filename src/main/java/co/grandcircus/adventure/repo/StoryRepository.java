package co.grandcircus.adventure.repo;

import co.grandcircus.adventure.model.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
}
