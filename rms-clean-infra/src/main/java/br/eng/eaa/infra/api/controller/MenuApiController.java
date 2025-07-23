package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.db.dto.request.MenuRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.service.MenuService;
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
@RequestMapping("/api/v1/menus")
public class MenuApiController {

    Logger logger = LoggerFactory.getLogger(MenuApiController.class);

    private final MenuService menuService;

    public MenuApiController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuResponseDto> save(@RequestBody MenuRequestDto menu) {
        logger.info("Saving menu: {} - {}", menu, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        MenuResponseDto savedMenu = menuService.save(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMenu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponseDto> findById(@PathVariable UUID id) {
        logger.info("Finding menu by ID: {} - {}", id, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        MenuResponseDto menu = menuService.findById(id);
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/update")
    public ResponseEntity<MenuResponseDto> update(@RequestBody MenuRequestDto menu) {
        logger.info("Updating menu: {} - {}", menu, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        MenuResponseDto updatedMenu = menuService.update(menu);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        logger.info("Deleting menu with ID: {} - {}", id, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        Boolean isDeleted = menuService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MenuResponseDto>> findAll() {
        logger.info("Finding all menus - {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        List<MenuResponseDto> menus = menuService.findAll();
        return ResponseEntity.ok(menus);
    }
}
