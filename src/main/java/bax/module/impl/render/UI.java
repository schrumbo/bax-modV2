package bax.module.impl.render;

import bax.module.Category;
import bax.module.Module;
import bax.module.ModuleInfo;
import bax.module.impl.render.clickGUI.ClickGUIScreen;
import bax.module.impl.render.customModMenu.CustomModMenu;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
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
        GL11.glScaled(1.5, 1.5, 1.5);
        fr.drawString("Bax", 1, 1, -1);
        GL11.glPopMatrix();
    }

    /**
     * renders the CLickGUIScreen
     * @param key any Key pressed by the user
     */
    @Override
    public void onKey(int key) {
        if (key ==Keyboard.KEY_RSHIFT){
            mc.displayGuiScreen(new CustomModMenu());
        }
    }
}
