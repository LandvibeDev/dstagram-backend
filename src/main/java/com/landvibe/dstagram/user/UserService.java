package com.landvibe.dstagram.user;

import com.landvibe.dstagram.user.model.DstagramUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<DstagramUser> getUsers() {
        return userRepository.findAll();
    }

    public DstagramUser save(DstagramUser user) {
        return userRepository.save(user);
    }
}
