package vangoh74.backend.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vangoh74.backend.security.model.AppUser;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {
}
