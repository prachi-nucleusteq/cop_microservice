package cop.project.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cop.project.user.dbo.Location;
import cop.project.user.dbo.User;
import cop.project.user.dto.LoginDto;
import cop.project.user.dto.ResponseDto;
import cop.project.user.dto.UserDetailsOutDto;
import cop.project.user.dto.UserInformationDto;
import cop.project.user.dto.UserInformationUpdateInDto;
import cop.project.user.repository.UserRepository;
import cop.project.user.service.UserService;
import cop.project.user.service.ValidatorService;
import cop.project.user.utils.Constants;

public class userControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidatorService validatorService;

    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void userSignUpTest() throws JsonProcessingException, Exception {

        UserInformationDto userInformationDto = new UserInformationDto();
        userInformationDto.setAccountStatus("Active");
        userInformationDto.setAddress("Address");
        userInformationDto.setBusinessName("businessName");
        userInformationDto.setContactNumber(930983L);
        userInformationDto.setCountry("country");
        userInformationDto.setEmailId("r@gamil.com");
        userInformationDto.setGender("male");
        userInformationDto.setIsBusiness(true);
        userInformationDto.setLocation(null);
        userInformationDto.setPassword("4534teffa");
        userInformationDto.setPincode(34534534);
        userInformationDto.setState("M.P");
        userInformationDto.setUserName("username");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(Constants.SUCCESS);

        when(userService.saveUser(userInformationDto)).thenReturn(responseDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/signUp")
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(userInformationDto)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void userLoginTest() throws JsonProcessingException, Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmailId("test@gmail.com");
        loginDto.setPassword("3jkk2LK");

        ResponseDto expectedResponseOutDto = new ResponseDto();
        expectedResponseOutDto.setMessage(Constants.LOGIN);

        when(userService.loginUser(loginDto)).thenReturn(expectedResponseOutDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(loginDto))).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(expectedResponseOutDto),
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getUserByUserIdTest() throws JsonProcessingException, Exception {
        UserDetailsOutDto userDetailsOutDto = new UserDetailsOutDto();
        userDetailsOutDto.setAddress("address");
        userDetailsOutDto.setBusiness(true);
        userDetailsOutDto.setBusinessAddress("addressbusiness");
        userDetailsOutDto.setBusinesscontactNumber(535980985L);
        userDetailsOutDto.setBusinessName("business Name");
        userDetailsOutDto.setContactNumber(3423443532L);
        userDetailsOutDto.setCountry("country");
        userDetailsOutDto.setEmailId("test@gmail.com");

        List<Location> locationlist = new ArrayList<Location>();

        Location location1 = new Location();
        location1.setId(1L);
        location1.setBusinessId(2L);
        location1.setCountry("country");
        location1.setLocation("location");
        location1.setState("state");
        location1.setPincode(234234);

        locationlist.add(location1);

        userDetailsOutDto.setLocation(locationlist);
        userDetailsOutDto.setPincode(32482);
        userDetailsOutDto.setState("Uttar Pradesh");
        userDetailsOutDto.setUserName("userName");

        when(userService.getDetailsById(3L)).thenReturn(userDetailsOutDto);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/user/get/{userId}", 3L).accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).header("user-agent", "userAgent"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(userDetailsOutDto), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void updateByUserIdTest() throws JsonProcessingException, Exception {

        UserInformationUpdateInDto userInformationDto = new UserInformationUpdateInDto();
        userInformationDto.setAddress("Address");
        userInformationDto.setBusinessName("businessName");
        userInformationDto.setContactNumber(930983L);
        userInformationDto.setCountry("country");
        userInformationDto.setEmailId("r@gamil.com");
        userInformationDto.setGender("male");
        userInformationDto.setLocation(null);
        userInformationDto.setPincode(34534534);
        userInformationDto.setState("M.P");
        userInformationDto.setUserName("username");
        userInformationDto.setBusinessContactNumber(2144412134L);
        userInformationDto.setLocationId(4L);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(Constants.UPDATED_USER_DETAILS);
        User user = new User();
        user.setAddress("address");
        user.setBusinessId(3L);
        user.setEmailId("email");

        when(userService.updateUserDetails(2L, userInformationDto)).thenReturn(responseDto);
        when(validatorService.validateUserId(3L)).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/user/update/{userId}", 2L)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent").content(OBJECTMAPPPER.writeValueAsBytes(userInformationDto)))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseDto), mvcResult.getResponse().getContentAsString());
    }
    
    @Test
    public void deleteByUserIdTest() throws JsonProcessingException, Exception {


        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(Constants.USER_DELETED_SUCCESSFULLY);
        User user = new User();
        user.setAddress("address");
        user.setBusinessId(3L);
        user.setEmailId("email");

        when(userService.deleteUserById(3L)).thenReturn(responseDto);
        when(validatorService.validateUserId(3L)).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/{userId}", 3L)
                .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("user-agent", "userAgent"))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseDto), mvcResult.getResponse().getContentAsString());
    }

}
