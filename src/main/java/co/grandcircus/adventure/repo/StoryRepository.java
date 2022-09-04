package co.grandcircus.adventure.repo;

import co.grandcircus.adventure.model.Scene;
import co.grandcircus.adventure.model.Story;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
	List<Story> findAll();
	Optional<Story> findById(String id);
}
