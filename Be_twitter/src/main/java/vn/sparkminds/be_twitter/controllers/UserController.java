package vn.sparkminds.be_twitter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.sparkminds.be_twitter.entities.User;
import vn.sparkminds.be_twitter.exceptions.UserException;
import vn.sparkminds.be_twitter.services.UserService;
import vn.sparkminds.be_twitter.services.dto.UserDto;
import vn.sparkminds.be_twitter.services.mapper.UserDtoMapper;
import vn.sparkminds.be_twitter.utils.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader(
            "Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setReq_user(true);

        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@RequestHeader(
            "Authorization") String jwt, @PathVariable Long userId) throws UserException {

        User reqUser = userService.findUserProfileByJwt(jwt);
        User user = userService.findUserById(userId);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setReq_user(UserUtil.isReqUser(reqUser, user));
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, user));
        return new ResponseEntity<UserDto>(userDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestHeader(
            "Authorization") String jwt, @RequestParam String query) throws UserException {

        User reqUser = userService.findUserProfileByJwt(jwt);
        List<User> users = userService.searchUser(query);

        List<UserDto> userDtos = UserDtoMapper.toUserDtos(users);
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestHeader(
            "Authorization") String jwt, @RequestBody User req) throws UserException {

        User reqUser = userService.findUserProfileByJwt(jwt);
        User users = userService.updateUser(reqUser.getId(), req);

        UserDto userDtos = UserDtoMapper.toUserDto(users);
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }

    @PutMapping("{userId}/follow")
    public ResponseEntity<UserDto> followUser(@PathVariable Long userId,
                                              @RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserProfileByJwt(jwt);
        User user = userService.followUser(userId, reqUser);
        UserDto userDto = UserDtoMapper.toUserDto(user);
        userDto.setFollowed(UserUtil.isFollowedByReqUser(reqUser, user));
        return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
    }
}
