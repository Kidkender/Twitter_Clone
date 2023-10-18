package vn.sparkminds.be_twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.sparkminds.be_twitter.entities.Twit;
import vn.sparkminds.be_twitter.entities.User;

import java.util.List;

public interface TwitRepository extends JpaRepository<Twit, Long> {
    List<Twit> findAllByIsTwitTrueOrderByCreatedAtDesc();

    //    List<Twit> findByReTwitUserContainsOrUserId_andIsTwitTrueOrderByCreatedAtDesc(User user, Long userId);
    List<Twit> findByReTwitUsersContainsOrUserIdAndIsTwitTrueOrderByCreatedAtDesc(User user, Long userId);

    List<Twit> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("select  t from Twit t join t.likes l where l.user.id =:userId")
    List<Twit> findByLikesUserId(Long userId);

}
