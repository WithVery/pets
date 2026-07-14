package group.shawarma.domain.repo;

import group.shawarma.domain.model.MenuItem;
import group.shawarma.domain.model.MenuSection;

import java.util.List;

public interface MenuItemRepo {
    MenuItem saveMenuItem(MenuItem menuItem);
    MenuItem updateMenuItem(MenuItem menuItem);
    MenuItem getMenuItemById(Long id);
    List<MenuItem> getMenuItemsBySection(MenuSection section);
    void deleteMenuItem(MenuItem menuItem);
}
