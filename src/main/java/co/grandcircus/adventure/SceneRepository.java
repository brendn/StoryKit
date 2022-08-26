package co.grandcircus.adventure;

import co.grandcircus.adventure.base.Scene;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SceneRepository extends MongoRepository<Scene, String> {
}
