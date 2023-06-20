package cop.project.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private ValidatorService ValidatorService;

    public ResponseDto saveUser(UserInformationDto userInformationDto) {

        User userObject = new User();

        Business business = new Business();
        if (userInformationDto.getIsBusiness()) {

            business.setBusinessName(userInformationDto.getBusinessName());
            business.setBusinessAddress(userInformationDto.getAddress());
            business.setBusinesscontactNumber(userInformationDto.getBusinessContactNumber());
            business.setEmailId(userInformationDto.getEmailId());

            business = businessRepository.save(business);

            for (Location loc : userInformationDto.getLocation()) {
                Location location = new Location();
                location.setBusinessId(business.getId());
                location.setCountry(loc.getCountry());
                location.setLocation(loc.getLocation());
                location.setState(loc.getState());
                location.setPincode(loc.getPincode());

                locationRepository.save(location);
            }
        }
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

        userObject = userRepository.save(userObject);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(Constants.SUCCESS);
        return responseDto;
    }

    public ResponseDto loginUser(LoginDto loginDto) throws NotFoundException {

        User user = userRepository.findByEmail(loginDto.getEmailId());

        if (Objects.isNull(user)) {
            log.info(String.format(Constants.INVALID_USER + ",throwing NotFoundException", loginDto.getEmailId()));
            throw new NotFoundException(String.format(Constants.INVALID_USER, loginDto.getEmailId()));
        } else if (!Objects.equals(loginDto.getPassword(), user.getPassword())) {
            log.info(String.format(Constants.INCORRECT_PASSWORD + ",throwing NotFoundException",
                    loginDto.getPassword()));
            throw new NotFoundException(String.format(Constants.INCORRECT_PASSWORD, loginDto.getPassword()));
        }
        ResponseDto responseOutDto = new ResponseDto();
        responseOutDto.setMessage(Constants.LOGIN);

        return responseOutDto;
    }

    public UserDetailsOutDto getDetailsById(Long userId) throws Exception {

        User user = ValidatorService.validateUserId(userId);
        UserDetailsOutDto userDetailsOutDto = new UserDetailsOutDto();
        Business business = new Business();

        if (user.getIsBusiness()) {
            business = ValidatorService.validateBusinessId(user.getBusinessId());
            userDetailsOutDto.setBusinessAddress(business.getBusinessAddress());
            userDetailsOutDto.setBusinesscontactNumber(business.getBusinesscontactNumber());
            userDetailsOutDto.setBusinessName(business.getBusinessName());

            List<Location> location = locationRepository.findByBusinessId(business.getId());
            List<Location> locationlist = new ArrayList<Location>();

            for (Location loc : location) {
                Location location1 = new Location();
                location1.setId(loc.getId());
                location1.setBusinessId(business.getId());
                location1.setCountry(loc.getCountry());
                location1.setLocation(loc.getLocation());
                location1.setState(loc.getState());
                location1.setPincode(loc.getPincode());

                locationlist.add(location1);
            }
            userDetailsOutDto.setLocation(locationlist);
        }

        userDetailsOutDto.setUserName(user.getUserName());
        userDetailsOutDto.setEmailId(user.getEmailId());
        userDetailsOutDto.setContactNumber(user.getContactNumber());
        userDetailsOutDto.setBusinessName(business.getBusinessName());
        userDetailsOutDto.setAddress(user.getAddress());
        userDetailsOutDto.setPincode(user.getPincode());
        userDetailsOutDto.setState(user.getState());
        userDetailsOutDto.setCountry(user.getCountry());

        return userDetailsOutDto;
    }

    public ResponseDto updateUserDetails(Long userId, UserInformationUpdateInDto userInformationDto) throws Exception {
        User userObject = ValidatorService.validateUserId(userId);

        if (userObject.getIsBusiness()) {
            Business business = ValidatorService.validateBusinessId(userObject.getBusinessId());

            business.setBusinessName(userInformationDto.getBusinessName());
            business.setBusinessAddress(userInformationDto.getAddress());
            business.setBusinesscontactNumber(userInformationDto.getBusinessContactNumber());
            business.setEmailId(userInformationDto.getEmailId());
            businessRepository.save(business);

            Location location = new Location();
            if (Objects.nonNull(userInformationDto.getLocationId())) {
                Optional<Location> optionalLocation = locationRepository.findById(userInformationDto.getLocationId());
                location = optionalLocation.get();
                for (Location loc : userInformationDto.getLocation()) {
                    location.setBusinessId(loc.getBusinessId());
                    location.setCountry(loc.getCountry());
                    location.setLocation(loc.getLocation());
                    location.setState(loc.getState());
                    location.setPincode(loc.getPincode());
                    location.setBusinessId(business.getId());
                }
            } else {
                for (Location loc : userInformationDto.getLocation()) {
                    location.setBusinessId(loc.getBusinessId());
                    location.setCountry(loc.getCountry());
                    location.setLocation(loc.getLocation());
                    location.setState(loc.getState());
                    location.setPincode(loc.getPincode());
                    location.setBusinessId(business.getId());
                }
            }

            locationRepository.save(location);
        }

        userObject.setAddress(userInformationDto.getAddress());
        userObject.setUserName(userInformationDto.getUserName());
        userObject.setEmailId(userInformationDto.getEmailId());
        userObject.setGender(userInformationDto.getGender());
        userObject.setContactNumber(userInformationDto.getContactNumber());
        userObject.setCountry(userInformationDto.getCountry());
        userObject.setState(userInformationDto.getState());
        userObject.setPincode(userInformationDto.getPincode());

        userRepository.save(userObject);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(Constants.UPDATED_USER_DETAILS);

        return responseDto;
    }

    public ResponseDto deleteUserById(Long userId) throws Exception {

        ValidatorService.validateUserId(userId);
        userRepository.deleteById(userId);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(Constants.USER_DELETED_SUCCESSFULLY);
        return responseDto;
    }

}
