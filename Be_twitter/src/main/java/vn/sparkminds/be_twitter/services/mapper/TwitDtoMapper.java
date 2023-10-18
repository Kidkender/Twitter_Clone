package vn.sparkminds.be_twitter.services.mapper;

import vn.sparkminds.be_twitter.entities.Twit;
import vn.sparkminds.be_twitter.entities.User;
import vn.sparkminds.be_twitter.services.dto.TwitDto;
import vn.sparkminds.be_twitter.services.dto.UserDto;
import vn.sparkminds.be_twitter.utils.TwitUtil;

import java.util.ArrayList;
import java.util.List;

public class TwitDtoMapper {
    public static TwitDto toTwitDto(Twit twit, User reqUser) {
        UserDto user = UserDtoMapper.toUserDto(twit.getUser());
        boolean isLiked = TwitUtil.isLikedByReqUser(reqUser, twit);
        boolean isRetwited = TwitUtil.isRetwitedByReqUser(reqUser, twit);
        List<Long> retwitUserId = new ArrayList<>();
        for (User user1 : twit.getReTwitUsers()) {
            retwitUserId.add(user.getId());
        }
        TwitDto twitDto = new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twit.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTwits().size());
        twitDto.setTotalRetweets(twit.getReTwitUsers().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetwit(isRetwited);
        twitDto.setRetwitUserId(retwitUserId);
        twitDto.setReplyTwits(toTwitDtos(twit.getReplyTwits(),reqUser));
        twitDto.setVideo(twit.getVideo());
        return twitDto;
    }

    public static List<TwitDto> toTwitDtos(List<Twit> twits, User reqUser) {
        List<TwitDto> twitDtos = new ArrayList<>();
        for (Twit twit : twits) {
            TwitDto twitDto = toReplyTwitDto(twit, reqUser);
            twitDtos.add(twitDto);
        }
        return twitDtos;
    }

    private static TwitDto toReplyTwitDto(Twit twit, User reqUser) {

        UserDto user = UserDtoMapper.toUserDto(twit.getUser());
        boolean isLiked = TwitUtil.isLikedByReqUser(reqUser, twit);
        boolean isRetwited = TwitUtil.isRetwitedByReqUser(reqUser, twit);
        List<Long> retwitUserId = new ArrayList<>();
        for (User user1 : twit.getReTwitUsers()) {
            retwitUserId.add(user.getId());
        }
        TwitDto twitDto = new TwitDto();
        twitDto.setId(twit.getId());
        twitDto.setContent(twit.getContent());
        twitDto.setCreatedAt(twit.getCreatedAt());
        twitDto.setImage(twit.getImage());
        twitDto.setTotalLikes(twit.getLikes().size());
        twitDto.setTotalReplies(twit.getReplyTwits().size());
        twitDto.setTotalRetweets(twit.getReTwitUsers().size());
        twitDto.setUser(user);
        twitDto.setLiked(isLiked);
        twitDto.setRetwit(isRetwited);
        twitDto.setRetwitUserId(retwitUserId);
        twitDto.setVideo(twit.getVideo());
        return twitDto;
    }
}
