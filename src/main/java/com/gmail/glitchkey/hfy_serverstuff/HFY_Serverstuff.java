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
        import org.bukkit.plugin.java.JavaPlugin;
//* IMPORTS: OTHER
        //* NOT NEEDED

public class HFY_Serverstuff extends JavaPlugin
{
        BlockListener blockListener;
        MobListener mobListener;
        RecipeHandler recipeHandler;
        
        public void onLoad()
        {
                // Initialize the listeners and other handlers
                if (mobListener == null) mobListener = new MobListener(this);
                if (blockListener == null) blockListener = new BlockListener(this);
                if (recipeHandler == null) recipeHandler = new RecipeHandler(this);
                
                logInfo("HFY-Serverstuff loaded.");
        }
        
        public void onEnable()
        {
                // Enable the listeners and other handlers
                blockListener.enable();
                mobListener.enable();
                recipeHandler.enable();
                
                logInfo("HFY-Serverstuff enabled.");
        }
        
        public void onDisable()
        {
                // Disable the listeners and other handlers
                blockListener.disable();
                mobListener.disable();
                recipeHandler.disable();
                
                logInfo("HFY-Serverstuff disabled.");
        }
        
        public void logInfo(String msg)
        {
                getLogger().info(msg);
        }
}
