package br.eng.eaa.infra.api.controller;

import br.eng.eaa.infra.db.dto.request.MenuRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuApiController {

    private final MenuService menuService;

    public MenuApiController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuResponseDto> save(@RequestBody MenuRequestDto menu) {
        MenuResponseDto savedMenu = menuService.save(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMenu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponseDto> findById(@PathVariable UUID id) {
        MenuResponseDto menu = menuService.findById(id);
        return ResponseEntity.ok(menu);
    }

    @PutMapping("/update")
    public ResponseEntity<MenuResponseDto> update(@RequestBody MenuRequestDto menu) {
        MenuResponseDto updatedMenu = menuService.update(menu);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MenuResponseDto>> findAll() {
        List<MenuResponseDto> menus = menuService.findAll();
        return ResponseEntity.ok(menus);
    }
}
