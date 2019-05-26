package Polytechnic.network.repo;

import Polytechnic.network.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {"subscription", "subscribers"})
    Optional<User> findById(String s);
}
