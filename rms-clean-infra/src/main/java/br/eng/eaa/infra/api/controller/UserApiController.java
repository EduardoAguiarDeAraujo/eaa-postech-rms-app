package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.db.dto.request.UserRequestDto;
import br.eng.eaa.infra.db.dto.response.UserResponseDto;
import br.eng.eaa.infra.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {

    Logger logger = LoggerFactory.getLogger(UserApiController.class);
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDto) {
        logger.info("Creating user - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        UserResponseDto createdUser = userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable UUID id) {
        logger.info("Fetching user with ID - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userDto) {
        logger.info("Updating user - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        UserResponseDto updatedUser = userService.update(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        logger.info("Deleting user with ID - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        Boolean isDeleted =  userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        logger.info("Find all users - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
