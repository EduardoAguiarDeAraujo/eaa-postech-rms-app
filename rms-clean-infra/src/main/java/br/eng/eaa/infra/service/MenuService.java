package br.eng.eaa.infra.service;

import br.eng.eaa.adapter.controller.MenuController;
import br.eng.eaa.application.model.request.MenuRequest;
import br.eng.eaa.application.model.response.MenuResponse;
import br.eng.eaa.infra.db.dto.request.MenuRequestDto;
import br.eng.eaa.infra.db.dto.response.MenuResponseDto;
import br.eng.eaa.infra.db.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService {

    private final MenuController menuController;

    public MenuService(MenuController menuController) {
        this.menuController = menuController;
    }

    public MenuResponseDto save(MenuRequestDto menuRequestDto) {
        MenuRequest menuRequest = MenuMapper.toRequest(menuRequestDto);
        MenuResponse savedMenu = menuController.save(menuRequest);
        return MenuMapper.toDto(savedMenu);
    }

    public MenuResponseDto findById(UUID id) {
        MenuResponse menuResponse = menuController.findById(id);
        return MenuMapper.toDto(menuResponse);
    }

    public MenuResponseDto update(MenuRequestDto requestDto) {
        MenuRequest menuRequest = MenuMapper.toRequest(requestDto);
        MenuResponse updatedMenu = menuController.update(menuRequest);
        return MenuMapper.toDto(updatedMenu);
    }

    public Boolean delete(UUID id) {
        return menuController.delete(id);
    }

    public List<MenuResponseDto> findAll() {
        List<MenuResponse> menus = menuController.findAll();
        return menus.stream().map(MenuMapper::toDto).toList();
    }

}
