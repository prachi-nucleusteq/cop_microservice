package cop.project.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cop.project.user.dbo.Business;
import cop.project.user.dbo.User;
import cop.project.user.exception.NotFoundException;
import cop.project.user.repository.BusinessRepository;
import cop.project.user.repository.UserRepository;
import cop.project.user.utils.Constants;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidatorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;


    public User validateUserId(Long userId) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            log.info(String.format(Constants.INVALID_USER_ID + ",throwing NotFoundException", userId));
            throw new NotFoundException(String.format(Constants.INVALID_USER_ID, userId));
        }
        
        return optionalUser.get();
    }

    public Business validateBusinessId(Long businessId) throws NotFoundException {
        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
    
        if (!optionalBusiness.isPresent()) {
            log.info(String.format(Constants.USER_NOT_BUSINESS_USER + ",throwing NotFoundException", businessId));
            throw new NotFoundException(String.format(Constants.USER_NOT_BUSINESS_USER, businessId));
        }
        
        return optionalBusiness.get();
    }

}

