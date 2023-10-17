package vn.sparkminds.be_twitter.services;

import vn.sparkminds.be_twitter.entities.Twit;
import vn.sparkminds.be_twitter.entities.User;
import vn.sparkminds.be_twitter.exceptions.TwitException;
import vn.sparkminds.be_twitter.exceptions.UserException;
import vn.sparkminds.be_twitter.services.dto.request.TwitReplyRequest;

import java.util.List;

public interface TwitService {
    public Twit createTwit(Twit req, User user) throws UserException;

    public List<Twit> findAllTwit();

    public Twit reTwit(Long twitId, User user) throws UserException, TwitException;

    public Twit findById(Long twitId) throws TwitException;

    public void deleteTwitById(Long twitId, Long userId) throws UserException, TwitException;

    public Twit removeFromReTwit(Long twitId, User user) throws TwitException,
            UserException;

    public Twit createdReply(TwitReplyRequest req, User user) throws TwitException;

    public List<Twit> getUserTwit(User user) throws UserException;

    public List<Twit> findByLikesContainsUser(User user) throws UserException;

}
