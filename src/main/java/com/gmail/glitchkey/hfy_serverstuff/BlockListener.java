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
        
        public BlockListener(JavaPlugin p)
        {
                plugin = p;
                
                // Initialize a random
                random = new Random();
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
                
                // Do nothing unless the event passes a random check
                if (random.nextFloat() > chance) return;
                
                // Create and drop sand block
                ItemStack sand = new ItemStack(Material.SAND);
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), sand);
        }
}
