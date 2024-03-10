/*
 * Copyright (c) 2024 Sean Porter <glitchkey@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.gmail.glitchkey.hfy_serverstuff;

//* IMPORTS: JDK/JRE
        import java.util.ArrayList;
        import java.util.Random;
//* IMPORTS: BUKKIT
        import org.bukkit.enchantments.Enchantment;
        import org.bukkit.event.block.BlockBreakEvent;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.EventPriority;
        import org.bukkit.event.HandlerList;
        import org.bukkit.event.Listener;
        import org.bukkit.GameMode;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.meta.ItemMeta;
        import org.bukkit.Material;
        import org.bukkit.plugin.java.JavaPlugin;
//* IMPORTS: OTHER
        //* NOT NEEDED

public class BlockListener implements Listener
{
        JavaPlugin plugin;
        Random random;
        
        ArrayList<Material> glassBlocks;
        ArrayList<Material> glassPanes;
        
        public BlockListener(JavaPlugin p)
        {
                plugin = p;
                
                // Initialize a random
                random = new Random();
                
                // Initialize the glass block list
                glassBlocks = new ArrayList();
                glassBlocks.add(Material.BLACK_STAINED_GLASS);
                glassBlocks.add(Material.BLUE_STAINED_GLASS);
                glassBlocks.add(Material.BROWN_STAINED_GLASS);
                glassBlocks.add(Material.CYAN_STAINED_GLASS);
                glassBlocks.add(Material.GLASS);
                glassBlocks.add(Material.GRAY_STAINED_GLASS);
                glassBlocks.add(Material.GREEN_STAINED_GLASS);
                glassBlocks.add(Material.LIGHT_BLUE_STAINED_GLASS);
                glassBlocks.add(Material.LIGHT_GRAY_STAINED_GLASS);
                glassBlocks.add(Material.LIME_STAINED_GLASS);
                glassBlocks.add(Material.MAGENTA_STAINED_GLASS);
                glassBlocks.add(Material.ORANGE_STAINED_GLASS);
                glassBlocks.add(Material.PINK_STAINED_GLASS);
                glassBlocks.add(Material.PURPLE_STAINED_GLASS);
                glassBlocks.add(Material.RED_STAINED_GLASS);
                glassBlocks.add(Material.WHITE_STAINED_GLASS);
                glassBlocks.add(Material.YELLOW_STAINED_GLASS);
                
                // Initialize the glass pane list
                glassPanes = new ArrayList();
                glassPanes.add(Material.BLACK_STAINED_GLASS_PANE);
                glassPanes.add(Material.BLUE_STAINED_GLASS_PANE);
                glassPanes.add(Material.BROWN_STAINED_GLASS_PANE);
                glassPanes.add(Material.CYAN_STAINED_GLASS_PANE);
                glassPanes.add(Material.GLASS_PANE);
                glassPanes.add(Material.GRAY_STAINED_GLASS_PANE);
                glassPanes.add(Material.GREEN_STAINED_GLASS_PANE);
                glassPanes.add(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                glassPanes.add(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
                glassPanes.add(Material.LIME_STAINED_GLASS_PANE);
                glassPanes.add(Material.MAGENTA_STAINED_GLASS_PANE);
                glassPanes.add(Material.ORANGE_STAINED_GLASS_PANE);
                glassPanes.add(Material.PINK_STAINED_GLASS_PANE);
                glassPanes.add(Material.PURPLE_STAINED_GLASS_PANE);
                glassPanes.add(Material.RED_STAINED_GLASS_PANE);
                glassPanes.add(Material.WHITE_STAINED_GLASS_PANE);
                glassPanes.add(Material.YELLOW_STAINED_GLASS_PANE);
        }
        
        public void enable()
        {
                plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }
        
        public void disable()
        {
                HandlerList.unregisterAll(this);
        }
        
        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        public void handleGlassDrops(BlockBreakEvent event)
        {
                // Do nothing if there is no event or player
                if(event == null || event.getPlayer() == null) return;
                
                // Get the block material
                Material m = event.getBlock().getType();
                
                // Do nothing if the block is not glass
                if (!glassPanes.contains(m) && !glassBlocks.contains(m)) return;
                
                // Do nothing if the player is in creative mode
                if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return;
                
                // Get the player's weapon
                ItemStack weapon = event.getPlayer().getInventory().getItemInMainHand();
                
                // Do nothing if the weapon has silk touch
                if (weapon.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 0) return;
                
                // calculate the chance of a drop
                int enchant = weapon.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                float chance = 0.25f;
                chance += 0.25f * ((float) enchant);
                
                // modify the chance for glass panes
                if (glassPanes.contains(m)) chance *= 0.375f;
                
                // Do nothing unless the event passes a random check
                if (random.nextFloat() > chance) return;
                
                // Create and drop sand block
                ItemStack sand = new ItemStack(Material.SAND);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), sand);
        }
}
