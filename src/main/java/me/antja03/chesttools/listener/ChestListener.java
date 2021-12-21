package me.antja03.chesttools.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class ChestListener implements Listener {

    private HashMap<Material, Material> convertableMaterials = new HashMap<Material, Material>() {{
        put(Material.COAL, Material.COAL_BLOCK);
        put(Material.IRON_INGOT, Material.IRON_BLOCK);
        put(Material.GOLD_INGOT, Material.GOLD_BLOCK);
        put(Material.REDSTONE, Material.REDSTONE_BLOCK);
        put(Material.INK_SACK, Material.LAPIS_BLOCK);
        put(Material.EMERALD, Material.EMERALD_BLOCK);
        put(Material.DIAMOND, Material.DIAMOND_BLOCK);
    }};

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_BLOCK)
            return;

        Block block = event.getClickedBlock();
        BlockState blockState = block.getState();

        if (!(blockState instanceof Chest))
            return;

        Chest chest = (Chest) blockState;
        Player player = event.getPlayer();
        ItemStack heldItem = player.getInventory().getItemInMainHand();
        String displayName = heldItem.getItemMeta().getDisplayName();

        if (displayName.equals("Chest Clearer")) {
            chest.getInventory().clear();
            player.sendMessage("Cleared");
            event.setCancelled(true);
        } else if (displayName.equals("Material Converter")) {
            convertChestMaterials(chest);
            player.sendMessage("Converted");
            event.setCancelled(true);
        }
    }

    private void convertChestMaterials(Chest chest) {
        Inventory inventory = chest.getInventory();

        for (Material material : convertableMaterials.keySet()) {
            int totalOfMaterial = 0;

            for (ItemStack itemStack : inventory.getContents()) {
                if (itemStack == null)
                    continue;

                if (itemStack.getType().equals(material)) {
                    totalOfMaterial += itemStack.getAmount();
                    inventory.remove(itemStack);
                }
            }

            if (totalOfMaterial < 9)
                continue;

            int remainder = totalOfMaterial % 9;
            // Used math.floor to make sure nobody would get an extra block due to rounding or anything like that.
            // Not sure if double -> int casts automatically round down
            int blocks = (int) Math.floor(totalOfMaterial / 9.0);

            while (blocks > 0) {
                int count = blocks - 64 >= 0 ? 64 : blocks;
                blocks -= count;
                inventory.addItem(new ItemStack(convertableMaterials.get(material), count));
            }

            inventory.addItem(new ItemStack(material, remainder));
        }
    }

}
