package softuni.demoexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.demoexam.models.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Repository> {

    Optional<User> findByUsername(String name);
}
