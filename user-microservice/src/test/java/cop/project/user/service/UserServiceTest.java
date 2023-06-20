package cop.project.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cop.project.user.dbo.Business;
import cop.project.user.dbo.Location;
import cop.project.user.dbo.User;
import cop.project.user.dto.LoginDto;
import cop.project.user.dto.ResponseDto;
import cop.project.user.dto.UserDetailsOutDto;
import cop.project.user.dto.UserInformationDto;
import cop.project.user.dto.UserInformationUpdateInDto;
import cop.project.user.exception.NotFoundException;
import cop.project.user.repository.BusinessRepository;
import cop.project.user.repository.LocationRepository;
import cop.project.user.repository.UserRepository;
import cop.project.user.utils.Constants;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private ValidatorService validatorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddComment() {

        UserInformationDto userInformationDto = new UserInformationDto();
        userInformationDto.setUserName("user name");
        userInformationDto.setAccountStatus("active");
        userInformationDto.setAddress("address");
        userInformationDto.setBusinessContactNumber(9876543212l);
        userInformationDto.setBusinessName("business name");
        userInformationDto.setContactNumber(2343428767l);
        userInformationDto.setCountry("country");
        userInformationDto.setEmailId("email@gmail.com");
        userInformationDto.setGender("male");
        userInformationDto.setIsBusiness(true);
        userInformationDto.setPassword("Re33j");
        userInformationDto.setPincode(473927);
        userInformationDto.setState("state");
        userInformationDto.setUserName("user name");

        List<Location> locations = new ArrayList<>();
        Location location1 = new Location();
        location1.setBusinessId(1l);
        location1.setCountry("USA");
        location1.setId(2L);
        location1.setLocation("location");
        location1.setPincode(1312321);
        location1.setState("California");
        locations.add(location1);

        userInformationDto.setLocation(locations);

        ResponseDto expectedResponseDto = new ResponseDto();
        expectedResponseDto.setMessage(Constants.SUCCESS);

        Business business = new Business();
        business.setBusinessName(userInformationDto.getBusinessName());
        business.setBusinessAddress(userInformationDto.getAddress());
        business.setBusinesscontactNumber(userInformationDto.getBusinessContactNumber());
        business.setEmailId(userInformationDto.getEmailId());

        Location location = new Location();
        for (Location loc : userInformationDto.getLocation()) {

            location.setBusinessId(business.getId());
            location.setCountry(loc.getCountry());
            location.setLocation(loc.getLocation());
            location.setState(loc.getState());
            location.setPincode(loc.getPincode());

        }

        User userObject = new User();
        userObject.setIsBusiness(userInformationDto.getIsBusiness());
        userObject.setAccountStatus(userInformationDto.getAccountStatus());
        userObject.setAddress(userInformationDto.getAddress());
        userObject.setUserName(userInformationDto.getUserName());
        userObject.setEmailId(userInformationDto.getEmailId());
        userObject.setGender(userInformationDto.getGender());
        userObject.setContactNumber(userInformationDto.getContactNumber());
        userObject.setBusinessId(business.getId());
        userObject.setPassword(userInformationDto.getPassword());
        userObject.setCountry(userInformationDto.getCountry());
        userObject.setState(userInformationDto.getState());
        userObject.setPincode(userInformationDto.getPincode());

        when(businessRepository.save(business)).thenReturn(business);
        when(userRepository.save(userObject)).thenReturn(userObject);
        when(locationRepository.save(location)).thenReturn(location);

        ResponseDto actualResponseDto = userService.saveUser(userInformationDto);

        assertEquals(expectedResponseDto, actualResponseDto);
    }

    @Test
    public void testloginUser() throws NotFoundException {

        LoginDto loginDto = new LoginDto();
        loginDto.setEmailId("email@gmail.com");
        loginDto.setPassword("password");

        ResponseDto expectedResponseDto = new ResponseDto();
        expectedResponseDto.setMessage(Constants.LOGIN);

        User user = new User();
        user.setEmailId(loginDto.getEmailId());
        user.setPassword(loginDto.getPassword());

        when(userRepository.findByEmail(loginDto.getEmailId())).thenReturn(user);

        ResponseDto actualResponseDto = userService.loginUser(loginDto);

        assertEquals(expectedResponseDto, actualResponseDto);
    }

    @Test
    public void testGetDetailsByUserId() throws Exception {

        Long userId = 4L;
        User user = new User();
        user.setUserName("user name");
        user.setEmailId("email@gmail.com");
        user.setContactNumber(1324357463L);
        user.setCountry("country");
        user.setGender("male");
        user.setIsBusiness(true);
        user.setPassword("password");
        user.setPincode(78965);
        user.setState("state");
        user.setUserName("username");
        user.setBusinessId(5L);
        user.setAddress("address");
        user.setAccountStatus("active");

        Business business = new Business();
        business.setBusinessName("name");
        business.setBusinessAddress("address");
        business.setBusinesscontactNumber(9876543212L);
        business.setEmailId("email@gmail.com");
        business.setId(5L);

        List<Location> locations = new ArrayList<>();
        Location location1 = new Location();
        location1.setBusinessId(5l);
        location1.setCountry("USA");
        location1.setId(2L);
        location1.setLocation("location");
        location1.setPincode(1312321);
        location1.setState("California");
        locations.add(location1);

        when(validatorService.validateUserId(userId)).thenReturn(user);
        when(locationRepository.findByBusinessId(user.getBusinessId())).thenReturn(locations);
        when(validatorService.validateBusinessId(business.getId())).thenReturn(business);

        UserDetailsOutDto expectedUserDetailsOutDto = new UserDetailsOutDto();
        expectedUserDetailsOutDto.setLocation(locations);
        expectedUserDetailsOutDto.setUserName(user.getUserName());
        expectedUserDetailsOutDto.setEmailId(user.getEmailId());
        expectedUserDetailsOutDto.setContactNumber(user.getContactNumber());
        expectedUserDetailsOutDto.setBusinessName(business.getBusinessName());
        expectedUserDetailsOutDto.setAddress(user.getAddress());
        expectedUserDetailsOutDto.setPincode(user.getPincode());
        expectedUserDetailsOutDto.setState(user.getState());
        expectedUserDetailsOutDto.setCountry(user.getCountry());
        expectedUserDetailsOutDto.setBusinesscontactNumber(business.getBusinesscontactNumber());
        expectedUserDetailsOutDto.setBusinessAddress(business.getBusinessAddress());

        UserDetailsOutDto actualUserDetailsOutDto = userService.getDetailsById(userId);

        assertEquals(expectedUserDetailsOutDto, actualUserDetailsOutDto);
    }

    @Test
    public void testUpdateUserDetailsByUserId() throws Exception {

        Long userId = 4L;
        User user = new User();
        user.setUserName("user name");
        user.setEmailId("email@gmail.com");
        user.setContactNumber(1324357463L);
        user.setCountry("country");
        user.setGender("male");
        user.setIsBusiness(true);
        user.setPassword("password");
        user.setPincode(78965);
        user.setState("state");
        user.setUserName("username");
        user.setBusinessId(5L);
        user.setAddress("address");
        user.setAccountStatus("active");

        when(validatorService.validateUserId(userId)).thenReturn(user);

        Business business = new Business();
        business.setBusinessName("name");
        business.setBusinessAddress("address");
        business.setBusinesscontactNumber(9876543212L);
        business.setEmailId("email@gmail.com");
        business.setId(5L);

        when(validatorService.validateBusinessId(business.getId())).thenReturn(business);

        List<Location> locations = new ArrayList<>();
        Location location1 = new Location();
        location1.setBusinessId(5l);
        location1.setCountry("USA");
        location1.setId(2L);
        location1.setLocation("location");
        location1.setPincode(1312321);
        location1.setState("California");
        locations.add(location1);

        when(locationRepository.findById(2L)).thenReturn(Optional.of(location1));

        UserInformationUpdateInDto userInformationDto = new UserInformationUpdateInDto();
        userInformationDto.setAddress(user.getAddress());
        userInformationDto.setBusinessContactNumber(business.getBusinesscontactNumber());
        userInformationDto.setBusinessName(business.getBusinessName());
        userInformationDto.setContactNumber(user.getContactNumber());
        userInformationDto.setCountry(user.getCountry());
        userInformationDto.setEmailId(user.getEmailId());
        userInformationDto.setGender(user.getGender());
        userInformationDto.setLocationId(location1.getId());
        userInformationDto.setPincode(user.getPincode());
        userInformationDto.setState(user.getState());
        userInformationDto.setUserName(user.getUserName());
        userInformationDto.setLocation(locations);

        ResponseDto expectedResponseDto = new ResponseDto();
        expectedResponseDto.setMessage(Constants.UPDATED_USER_DETAILS);

        ResponseDto actualResponseDto = userService.updateUserDetails(userId, userInformationDto);

        assertEquals(expectedResponseDto, actualResponseDto);
    }
}
