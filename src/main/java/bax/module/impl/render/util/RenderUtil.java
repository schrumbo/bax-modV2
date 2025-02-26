package bax.module.impl.render.util;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
    /**
     * draws a basic rectangle
     * @param x pos
     * @param y pos
     * @param w width
     * @param h height
     * @param color color
     */
    public static void drawRect(int x, int y, int w, int h, int color){
        Gui.drawRect(x, y, x + w, y + h, color);
    }

}


