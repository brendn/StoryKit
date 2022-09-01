package co.grandcircus.adventure.repo;

import co.grandcircus.adventure.model.Scene;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SceneRepository extends MongoRepository<Scene, String> {

    Optional<List<Scene>> findByParentID(String parentID);
}
