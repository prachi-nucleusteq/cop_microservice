package cop.project.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cop.project.user.dto.LoginDto;
import cop.project.user.dto.ResponseDto;
import cop.project.user.dto.UserDetailsOutDto;
import cop.project.user.dto.UserInformationDto;
import cop.project.user.dto.UserInformationUpdateInDto;
import cop.project.user.service.UserService;
import cop.project.user.service.ValidatorService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorService validatorService;

    @PostMapping("/signUp")
    public ResponseDto saveUser(@RequestBody UserInformationDto userInformationDto) {
        log.info("Request recieved to signUp user Controller {}", userInformationDto.toString());
        ResponseDto responseDto = userService.saveUser(userInformationDto);
        return responseDto;
    }

    @PostMapping("/login")
    public ResponseDto loginUser(@RequestBody LoginDto loginDto) throws Exception {
        log.info("Request recieved to login user Controller {}", loginDto.toString());
        ResponseDto responseDto =  userService.loginUser(loginDto);
        return responseDto;
    }

    @GetMapping("/get/{userId}")
    public UserDetailsOutDto getDetailsById(@PathVariable Long userId) throws Exception {
        log.info("Request recieved to get details by id Controller", userId);
        return userService.getDetailsById(userId);
        
    }

    @PutMapping("/update/{userId}")
    public ResponseDto updateUserDetails(@PathVariable Long userId, @RequestBody UserInformationUpdateInDto userInformationUpdateInDto)
            throws Exception {
        log.info("Request recieved to update user details controller",userInformationUpdateInDto.toString(), userId);
        validatorService.validateUserId(userId);
        return userService.updateUserDetails(userId, userInformationUpdateInDto);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseDto deleteUserById(@PathVariable Long userId) throws Exception {
        log.info("Request recieved to delete user by id Controller", userId);
        return userService.deleteUserById(userId);
    }
}
