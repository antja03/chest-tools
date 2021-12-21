package me.antja03.chesttools.menu;

import me.antja03.lwm.LightweightMenu;
import me.antja03.lwm.component.ClickComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ToolsMenu extends LightweightMenu {

    public ToolsMenu() {
        super("Chest Tools", 1);

        setAllowDragging(false);
        addMenuComponents(
                new ClickComponent(this, 0, Material.STICK, "Material Converter", (player, menu) -> {
                    ItemStack itemStack = new ItemStack(Material.STICK, 1);

                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("Material Converter");
                    itemStack.setItemMeta(itemMeta);

                    player.getInventory().addItem(itemStack);
                    player.closeInventory();
                    return true;
                }),
                new ClickComponent(this, 1, Material.BLAZE_ROD, "Chest Clearer", (player, menu) -> {
                    ItemStack itemStack = new ItemStack(Material.BLAZE_ROD, 1);

                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("Chest Clearer");
                    itemStack.setItemMeta(itemMeta);

                    player.getInventory().addItem(itemStack);
                    player.closeInventory();
                    return true;
                })
        );
    }

}
