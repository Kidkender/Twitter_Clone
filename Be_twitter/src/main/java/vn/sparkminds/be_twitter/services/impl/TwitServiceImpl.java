package vn.sparkminds.be_twitter.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sparkminds.be_twitter.entities.Twit;
import vn.sparkminds.be_twitter.entities.User;
import vn.sparkminds.be_twitter.exceptions.TwitException;
import vn.sparkminds.be_twitter.exceptions.UserException;
import vn.sparkminds.be_twitter.repositories.TwitRepository;
import vn.sparkminds.be_twitter.services.TwitService;
import vn.sparkminds.be_twitter.services.dto.request.TwitReplyRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
 public class TwitServiceImpl implements TwitService {
    @Autowired
    private TwitRepository twitRepository;

    @Override
    public Twit createTwit(Twit req, User user) throws UserException {
        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUser(req.getUser());
        twit.setReply(false);
        twit.setTwit(true);
        twit.setVideo(req.getVideo());

        return twitRepository.save(twit);
    }

    @Override
    public List<Twit> findAllTwit() {
        return twitRepository.findAllByIsTwitTrueOrderByCreatedAtDesc();
    }

    @Override
    public Twit reTwit(Long twitId, User user) throws UserException, TwitException {
        Twit twit = findById(twitId);
        if (twit.getReTwitUsers().contains(user)) {
            twit.getReTwitUsers().remove(user);
        } else {
            twit.getReTwitUsers().add(user);
        }
        return twitRepository.save(twit);
    }

    @Override
    public Twit findById(Long twitId) throws TwitException {
        Twit twit =
                twitRepository.findById(twitId).orElseThrow(() -> new TwitException("Twit not found with id " + twitId));

        return twit;
    }

    @Override
    public void deleteTwitById(Long twitId, Long userId) throws UserException, TwitException {
        Twit twit = findById(twitId);
        if (!userId.equals(twit.getUser().getId())) {
            throw new UserException("You can't delete another user's twit");
        }
        twitRepository.deleteById(twit.getId());
    }

    @Override
    public Twit removeFromReTwit(Long twitId, User user) throws TwitException, UserException {

        return null;
    }

    @Override
    public Twit createdReply(TwitReplyRequest req, User user) throws TwitException {
        Twit replyFor = findById(req.getTwitId());
        Twit twit = new Twit();
        twit.setContent(req.getContent());
        twit.setCreatedAt(LocalDateTime.now());
        twit.setImage(req.getImage());
        twit.setUser(user);
        twit.setReply(true);
        twit.setTwit(false);
        twit.setReplyFor(replyFor);
        Twit savedReply = twitRepository.save(twit);

        twit.getReplyTwits().add(savedReply);
        twitRepository.save(twit);
        return replyFor;
    }



    @Override
    public List<Twit> getUserTwit(User user) throws UserException {
        return twitRepository.findByReTwitUserContainsOrUserId_andIsTwitTrueOrderByCreatedAtDesc(user,user.getId());
    }

    @Override
    public List<Twit> findByLikesContainsUser(User user) throws UserException {
        return twitRepository.findByLikesUserId(user.getId());
    }
}
