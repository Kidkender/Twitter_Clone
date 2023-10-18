package vn.sparkminds.be_twitter.utils;

import vn.sparkminds.be_twitter.entities.Like;
import vn.sparkminds.be_twitter.entities.Twit;
import vn.sparkminds.be_twitter.entities.User;

public class TwitUtil {
    public final static boolean isLikedByReqUser(User reqUser, Twit twit) {
        for (Like like : twit.getLikes()) {
            if (like.getUser().getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;
    }

    public final static boolean isRetwitedByReqUser(User reqUser, Twit twit) {
        for (User user : twit.getReTwitUsers()) {
            if (user.getId().equals(reqUser.getId())) {
                return true;
            }
        }
        return false;
    }


}
