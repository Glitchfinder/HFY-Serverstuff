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
        //* NOT NEEDED
//* IMPORTS: BUKKIT
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.ShapedRecipe;
        import org.bukkit.inventory.ShapelessRecipe;
        import org.bukkit.inventory.StonecuttingRecipe;
        import org.bukkit.Material;
        import org.bukkit.NamespacedKey;
        import org.bukkit.plugin.java.JavaPlugin;
//* IMPORTS: OTHER
        //* NOT NEEDED

public class RecipeHandler
{
        JavaPlugin plugin;
        
        public RecipeHandler(JavaPlugin p)
        {
                plugin = p;
        }
        
        public void enable()
        {
                flintToGravel();
                cobbleToGravel();
                gravelToSand();
                dyeSand();
        }
        
        public void disable()
        {
                // Do nothing here because removing recipes may cause data loss
        }
        
        public void flintToGravel()
        {
                // Create initial recipe
                ItemStack item = new ItemStack(Material.GRAVEL);
                NamespacedKey key = new NamespacedKey(plugin, "flint_to_gravel");
                ShapedRecipe recipe = new ShapedRecipe(key, item);
                
                // Set the crafting requirements
                recipe.shape("ff", "ff");
                recipe.setIngredient('f', Material.FLINT);
                
                // Add the recipe
                plugin.getServer().addRecipe(recipe);
        }
        
        public void cobbleToGravel()
        {
                // Create initial recipe
                ItemStack item = new ItemStack(Material.GRAVEL);
                NamespacedKey key = new NamespacedKey(plugin, "cobble_to_gravel");
                StonecuttingRecipe recipe = new StonecuttingRecipe(key, item, Material.COBBLESTONE);
                
                // Add the recipe
                plugin.getServer().addRecipe(recipe);
        }
        
        public void gravelToSand()
        {
                // Create initial recipe
                ItemStack item = new ItemStack(Material.SAND);
                NamespacedKey key = new NamespacedKey(plugin, "gravel_to_sand");
                StonecuttingRecipe recipe = new StonecuttingRecipe(key, item, Material.GRAVEL);
                
                // Add the recipe
                plugin.getServer().addRecipe(recipe);
        }
        
        public void dyeSand()
        {
                // Add recipes for red-tinted dyes to make red sand
                handleDyeSandRecipe(Material.BROWN_DYE, "brown_dye_to_red_sand");
                handleDyeSandRecipe(Material.MAGENTA_DYE, "magenta_dye_to_red_sand");
                handleDyeSandRecipe(Material.ORANGE_DYE, "orange_dye_to_red_sand");
                handleDyeSandRecipe(Material.PINK_DYE, "pink_dye_to_red_sand");
                handleDyeSandRecipe(Material.PURPLE_DYE, "purple_dye_to_red_sand");
                handleDyeSandRecipe(Material.RED_DYE, "red_dye_to_red_sand");
                handleDyeSandRecipe(Material.YELLOW_DYE, "yellow_dye_to_red_sand");
        }
        
        public void handleDyeSandRecipe(Material dye, String name)
        {
                // Create initial recipe
                ItemStack item = new ItemStack(Material.RED_SAND);
                NamespacedKey key = new NamespacedKey(plugin, name);
                ShapelessRecipe recipe = new ShapelessRecipe(key, item);
                
                // Set the crafting requirements
                recipe.addIngredient(1, Material.SAND);
                recipe.addIngredient(1, dye);
                
                // Add the recipe
                plugin.getServer().addRecipe(recipe);
        }
}
