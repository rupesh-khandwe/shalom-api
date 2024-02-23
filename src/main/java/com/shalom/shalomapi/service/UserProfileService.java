package com.shalom.shalomapi.service;

import com.shalom.shalomapi.model.CustomUser;
import com.shalom.shalomapi.model.IEditUserProfile;
import com.shalom.shalomapi.model.IUser;
import com.shalom.shalomapi.model.UserProfile;
import com.shalom.shalomapi.repository.UserProfileRepository;
import org.apache.commons.text.WordUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserProfileService implements UserDetailsService {

    @Autowired
    private UserProfileRepository userProfileRepo;

/*
    @Autowired
    private PasswordEncoder bcryptEncoder;
*/
    public void saveUserProfile(UserProfile userProfile) {
        //userProfile.setPassword(bcryptEncoder.encode(userProfile.getPassword()));
        UserProfile userProf = new UserProfile();
        userProf.setUserName(userProfile.getUserName());
        userProf.setPassword(new BCryptPasswordEncoder().encode(userProfile.getPassword()));
        userProf.setEmail(userProfile.getEmail());
        userProf.setFirstName(WordUtils.capitalizeFully(userProfile.getFirstName()));
        userProf.setFirstName(WordUtils.capitalizeFully(userProfile.getMiddleName()));
        userProf.setFirstName(WordUtils.capitalizeFully(userProfile.getLastName()));
        userProf.setGender(userProfile.getGender());
        userProf.setPhone1(userProfile.getPhone1());
        userProf.setPhone2(userProfile.getPhone2());
        userProf.setAddressLine1(userProfile.getAddressLine1());
        userProf.setAddressLine2(userProfile.getAddressLine2());
        userProf.setCountryId(userProfile.getCountryId());
        userProf.setStateId(userProfile.getStateId());
        userProf.setCityId(userProfile.getCityId());
        userProf.setRegionId(userProfile.getRegionId());
        userProf.setCreatedOn((new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        userProfileRepo.save(userProf);
    }

   /* public UserDetails getUserByNameAndPassword(String userName, String password) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepo.findByUserNameAndPassword(userName, password);
        if(userProfile == null){
            throw new UsernameNotFoundException("Invalid user-id and password");
        } else {
            return new User("rupesh", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        }
    }*/

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepo.findByUserName(username);
        if(userProfile == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        } else {
//            return new org.springframework.security.core.userdetails.User(userProfile.getUserName(), userProfile.getPassword(),
//                    new ArrayList<>());
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;
            return new CustomUser(userProfile.getUserName(),
                    userProfile.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    new ArrayList<>(),
                    userProfile.getUserId(),
                    userProfile.getFirstName(),
                    userProfile.getLastName()
                    );
        }
    }

    public IEditUserProfile findUserProfile(Long userId){
        return userProfileRepo.findUserProfileByUserId(userId);
    }

    public void saveOrUpdateUserProfile(UserProfile userProfile) {
        try {
            userProfileRepo.saveOrUpdate(userProfile.getUserId(), userProfile.getEmail(), userProfile.getFirstName(), userProfile.getMiddleName(), userProfile.getLastName(),
                    userProfile.getPhone1(), userProfile.getPhone2(), userProfile.getAddressLine1(), userProfile.getAddressLine2(), userProfile.getStateId(),
                    userProfile.getCityId(), userProfile.getRegionId());
        } catch(ConstraintViolationException ex){
            System.out.println(ex.getStackTrace());
        }
    }

    public List<IUser> getUsers(Long userId){
        return userProfileRepo.getUsers(userId);
    }

}
