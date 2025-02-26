package bax.module.impl.render;

import bax.module.Category;
import bax.module.Module;
import bax.module.ModuleInfo;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

@ModuleInfo(
        name = "UI",
        description = "the ui the player sees",
        category = Category.RENDER,
        enabled = true
)
public class UI extends Module {
    @Override
    public void on2D(ScaledResolution sr) {
        GL11.glPushMatrix();
        GL11.glScaled(1.5, 1.5,1.5);
        fr.drawString("Bax", 1, 1, -1);
        GL11.glPopMatrix();
    }
}
