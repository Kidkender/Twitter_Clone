package vn.sparkminds.be_twitter.services;

import vn.sparkminds.be_twitter.entities.Like;
import vn.sparkminds.be_twitter.entities.User;
import vn.sparkminds.be_twitter.exceptions.TwitException;
import vn.sparkminds.be_twitter.exceptions.UserException;

import java.util.List;

public interface LikeService {
    public Like likeTwit(Long twitId, User user) throws UserException,
            TwitException;
    public List<Like> getAllLikes(Long twitId) throws TwitException;
}
