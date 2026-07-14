package group.shawarma.domain.interactor;

import group.shawarma.domain.model.MenuItem;
import group.shawarma.domain.model.MenuSection;
import group.shawarma.domain.repo.MenuItemRepo;

import java.util.List;

public class MenuItemInteractor {
    private final MenuItemRepo menuItemRepo;

    public MenuItemInteractor(MenuItemRepo menuItemRepo) {
        this.menuItemRepo = menuItemRepo;
    }

    public MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItemRepo.saveMenuItem(menuItem);
    }

    public MenuItem updateMenuItem(MenuItem menuItem) {
        return menuItemRepo.updateMenuItem(menuItem);
    }

    public List<MenuItem> getMenuItemBySection(MenuSection menuSection) {
        return menuItemRepo.getMenuItemsBySection(menuSection);
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepo.getMenuItemById(id);
    }


}
