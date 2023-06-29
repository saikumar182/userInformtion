package com.example.userInformation.service;
import com.example.userInformation.entity.User;
import com.example.userInformation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
//    public Page<User> getAllUsers(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }

    public List<User> getUsersByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
    public List<User> getUsersSortedByField(String field, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(field).ascending());

        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent();
    }


    public Page<User> getAllDetails(int pageNumber, int pageSize, String sortAttribute) {
        Sort sort = Sort.by(sortAttribute);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    public boolean checkUserAcceptance(String firstName, String lastName) {
        boolean isAcceptable = userRepository.findByFirstNameOrLastName(firstName, lastName).isEmpty();
        return isAcceptable;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}















