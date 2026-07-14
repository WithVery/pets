package group.shawarma.data.repoImpls.collectionFrw;

import group.shawarma.domain.model.MenuItem;
import group.shawarma.domain.model.MenuSection;
import group.shawarma.domain.repo.MenuItemRepo;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepoImpl implements MenuItemRepo {

    private List<MenuItem> items = new ArrayList<>();

    @Override
    public MenuItem saveMenuItem(MenuItem menuItem) {
        items.add(menuItem);
        return menuItem;
    }

    @Override
    public MenuItem updateMenuItem(MenuItem menuItem) {
        int index = items.indexOf(menuItem);
        if(index != -1) {
            items.set(index, menuItem);
        }
        return menuItem;
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return items.stream().filter(item -> item.getId().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public List<MenuItem> getMenuItemsBySection(MenuSection section) {
        return items.stream()
                .filter(item->item.getMenuSection().name().equals(section.name()))
                .toList();
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        items.remove(menuItem);
    }
}
