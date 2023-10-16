package vn.sparkminds.be_twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sparkminds.be_twitter.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);

    @Query("select distinct u from User u where u.fullName like %:query% or  " +
            "u.email like %:query%")
    public List<User> searchUser(@Param("query") String query);
}
