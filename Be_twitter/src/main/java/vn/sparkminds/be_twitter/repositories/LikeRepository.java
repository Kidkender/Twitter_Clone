package vn.sparkminds.be_twitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sparkminds.be_twitter.entities.Like;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("Select l from Like l where l.user.id =:userId AND " +
            "l.twit.id=:twitId")
    public Like isLikeExist(@Param("userId") Long userId,
                            @Param("twitId") Long twitId);

    @Query("select l from Like l where l.twit.id =:twitId")
    public List<Like> findByTwitId(@Param("twitId") Long twitId);
}
