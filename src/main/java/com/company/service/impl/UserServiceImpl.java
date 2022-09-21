package com.company.service.impl;


import com.company.repository.UserRepository;
import com.company.repository.entity.User;
import com.company.service.EncryptionService;
import com.company.service.UserService;
import com.company.service.dto.ObjectMapperService;
import com.company.service.dto.UserDto;
import com.company.service.exception.EntityNotFoundException;
import com.company.service.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service("userService")
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ObjectMapperService mapper;

    private final EncryptionService encryptionService;

    @Override
    public UserDto create(UserDto user) {
        log.debug("Create user={} in database user", user);
        validateCreate(user);
        String originalPassword = user.getPassword();
        String hashedPassword = encryptionService.digest(originalPassword);
        user.setPassword(hashedPassword);
        User userCreated = mapper.toEntity(user);
        userRepository.save(userCreated);
        return mapper.toDto(userCreated);
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Get user by id={} from database users", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " wasn't found"));
        return mapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.debug("Get user by email={} from database users", email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User with email:" + email + " doesn't exist");
        }
        return mapper.toDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        log.debug("Get all users from database users");
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<UserDto> getUserByLastName(String lastName) {
        log.debug("Get user by LastName={} from database users", lastName);
        List<User> users = userRepository.findByLastName(lastName);
        if (users == null) {
            throw new EntityNotFoundException("Users with lastname: " + lastName + " don't exist");
        }
        return users.stream().map(mapper::toDto).toList();
    }

    @Override
    public UserDto login(String email, String password) {
        log.debug("Get user by email and password from database users");
        String hashedPassword = encryptionService.digest(password);
        User user = userRepository.findByEmailAndPassword(email, hashedPassword);
        if (user == null) {
            throw new EntityNotFoundException("User with email:" + email + " doesn't exist");
        }
        return mapper.toDto(user);
    }

    @Override
    public UserDto registration(UserDto user) {
        log.debug("Registration user");
        validateCreate(user);
        String originalPassword = user.getPassword();
        String hashedPassword = encryptionService.digest(originalPassword);
        user.setPassword(hashedPassword);
        User userCreated = mapper.toEntityRegistration(user);
        userRepository.save(userCreated);
        return mapper.toDto(userCreated);
    }

    @Override
    public UserDto updateRegistration(UserDto user) {
        log.debug("Update user={} in database users", user);
        validateUpdate(user);
        User userUpdated = mapper.toEntityRegistration(user);
        userRepository.save(userUpdated);
        return mapper.toDto(userUpdated);
    }

    @Override
    public UserDto update(UserDto user) {
        log.debug("Update user={} in database users", user);
        validateUpdate(user);
        User userUpdated = mapper.toEntity(user);
        userRepository.save(userUpdated);
        return mapper.toDto(userUpdated);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete user by id={} in database users", id);
        userRepository.deleteById(id);
    }


    private void validateCreate(UserDto user) {
        if (!(Objects.equals(user.getPassword(), user.getConfirmPassword()) && user.getPassword().matches("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,}$"))) {
            throw new ValidateException("Password must contain at least one uppercase letter, one lowercase letter and at least 6 characters");
        }
        if (user.getAge() <= 0) {
            throw new ValidateException("Age is not valid. Age can't be less 0");
        }
    }

    private void validateUpdate(UserDto user) {
        if (user == null) {
            throw new ValidateException("Users can't be empty...");
        }
        if (user.getAge() <= 0) {
            throw new ValidateException("Age is not valid. Age can't be less 0");
        }
    }
}


