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
        import java.util.Arrays;
//* IMPORTS: BUKKIT
        import org.bukkit.entity.EntityType;
        import org.bukkit.event.entity.EntityDamageByEntityEvent;
        import org.bukkit.event.entity.EntityExplodeEvent;
        import org.bukkit.event.hanging.HangingBreakByEntityEvent;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.EventPriority;
        import org.bukkit.event.HandlerList;
        import org.bukkit.event.Listener;
        import org.bukkit.event.vehicle.VehicleDamageEvent;
        import org.bukkit.event.vehicle.VehicleDestroyEvent;
        
        import org.bukkit.plugin.java.JavaPlugin;
//* IMPORTS: OTHER
        //* NOT NEEDED

public class MobListener implements Listener
{
        JavaPlugin plugin;
        ArrayList<EntityType> protectedEntities;
        
        public MobListener(JavaPlugin p)
        {
                plugin = p;
                
                // Initialize the list of protected entities
                protectedEntities = new ArrayList();
                protectedEntities.add(EntityType.ARMOR_STAND);
                protectedEntities.add(EntityType.BOAT);
                protectedEntities.add(EntityType.CHEST_BOAT);
                protectedEntities.add(EntityType.DROPPED_ITEM);
                protectedEntities.add(EntityType.EXPERIENCE_ORB);
                protectedEntities.add(EntityType.FALLING_BLOCK);
                protectedEntities.add(EntityType.GLOW_ITEM_FRAME);
                protectedEntities.add(EntityType.ITEM_FRAME);
                protectedEntities.add(EntityType.LEASH_HITCH);
                protectedEntities.add(EntityType.MARKER);
                protectedEntities.add(EntityType.MINECART);
                protectedEntities.add(EntityType.MINECART_CHEST);
                protectedEntities.add(EntityType.MINECART_COMMAND);
                protectedEntities.add(EntityType.MINECART_FURNACE);
                protectedEntities.add(EntityType.MINECART_HOPPER);
                protectedEntities.add(EntityType.MINECART_MOB_SPAWNER);
                protectedEntities.add(EntityType.MINECART_TNT);
                protectedEntities.add(EntityType.PAINTING);
        }
        
        public void enable()
        {
                plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }
        
        public void disable()
        {
                HandlerList.unregisterAll(this);
        }
        
        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void handleCreeperExplosionBlocks(EntityExplodeEvent event)
        {
                // Do nothing if there is no event or the source is not a creeper
                if(event == null || event.getEntityType() != EntityType.CREEPER) return;

                // Empty the block list
                event.blockList().clear();
        }
        
        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void handleCreeperExplosionEntities(EntityDamageByEntityEvent event)
        {
                // Do nothing if there is no event or the source is not a creeper
                if(event == null || event.getDamager().getType() != EntityType.CREEPER) return;
                
                // Do nothing if the target is unprotected
                if (!protectedEntities.contains(event.getEntityType())) return;

                // Prevent this entity from being damaged
                event.setCancelled(true);
        }
        
        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void handleCreeperExplosionHangingEntities(HangingBreakByEntityEvent event)
        {
                // Do nothing if there is no event or the source is not a creeper
                if(event == null || event.getRemover().getType() != EntityType.CREEPER) return;
                
                // Do nothing if the target is unprotected
                if (!protectedEntities.contains(event.getEntity().getType())) return;

                // Prevent this entity from being damaged
                event.setCancelled(true);
        }
        
        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void handleCreeperExplosionVehicles(VehicleDamageEvent event)
        {
                // Do nothing if there is no event or the source is not a creeper
                if(event == null || event.getAttacker().getType() != EntityType.CREEPER) return;
                
                // Do nothing if the target is unprotected
                if (!protectedEntities.contains(event.getVehicle().getType())) return;

                // Prevent this entity from being damaged
                event.setCancelled(true);
        }
        
        @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
        public void handleCreeperExplosionDestroyedVehicles(VehicleDestroyEvent event)
        {
                // Do nothing if there is no event or there is no source
                if(event == null || event.getAttacker() == null) return;
                
                // Do nothing if the source is not a creeper
                if(event.getAttacker().getType() != EntityType.CREEPER) return;
                
                // Do nothing if the target is unprotected
                if (!protectedEntities.contains(event.getVehicle().getType())) return;

                // Prevent this entity from being damaged
                event.setCancelled(true);
        }
}
