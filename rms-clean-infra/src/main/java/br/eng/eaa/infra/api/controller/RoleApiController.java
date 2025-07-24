package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.db.dto.request.RoleRequestDto;
import br.eng.eaa.infra.db.dto.response.RoleResponseDto;
import br.eng.eaa.infra.service.RoleService;
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
@RequestMapping("/api/v1/roles")
public class RoleApiController {

    Logger logger = LoggerFactory.getLogger(RoleApiController.class);
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final RoleService roleService;

    public RoleApiController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDto> save(@RequestBody RoleRequestDto role){
        logger.info("Creating role - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        RoleResponseDto createdRole = roleService.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PutMapping("/update")
    public ResponseEntity<RoleResponseDto> update(@RequestBody RoleRequestDto role){
        logger.info("Updating role - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        RoleResponseDto updatedRole = roleService.update(role);
        return ResponseEntity.ok(updatedRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> findById(@PathVariable("id") String id){
        logger.info("Finding role by ID - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        RoleResponseDto role = roleService.findById(UUID.fromString(id));
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id){
        logger.info("Deleting role with ID - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        Boolean isDeleted = roleService.delete(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(isDeleted);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> findAll() {
        logger.info("Finding all roles - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        List<RoleResponseDto> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

}
