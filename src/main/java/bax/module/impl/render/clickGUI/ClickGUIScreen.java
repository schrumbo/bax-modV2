package bax.module.impl.render.clickGUI;

import bax.Bax;
import bax.module.Category;
import bax.module.Module;
import bax.module.ModuleInfo;
import bax.module.impl.render.util.RenderUtil;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@ModuleInfo(
        name = "ClickGUI",
        description = "renders the clickGUI",
        category = Category.RENDER
)
public class ClickGUIScreen extends GuiScreen {
    private final FontRenderer fr = Bax.INSTANCE.getFr();
    private final ScaledResolution sr = new ScaledResolution(Bax.INSTANCE.getMc());

    private final int boxW = 200;
    private final int boxH = 124;
    private final int boxX = (sr.getScaledWidth() / 2) - (boxW / 2);
    private final int boxY = (sr.getScaledHeight() / 2) - (boxH / 2);
    private int scrollOffset = 0;

    private final int menuW = boxW;
    private final int menuH = 20;
    private final int menuX = boxX; // Dieselbe X-Koordinate wie box
    private final int menuY = boxY + boxH; // 8 Pixel Abstand unter dem box

    private List<Module> modules;
    private Category catSelected = Category.MOVEMENT;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(boxX, boxY, boxW, boxH, 0xFF222222);
        RenderUtil.drawRect(menuX, menuY, menuW, menuH, 0xDD244442);

        int xOffset = menuX + 4;
        for(Category category : Category.values()){
            fr.drawString(category.name(), xOffset, menuY + 6, -1);
            xOffset += fr.getStringWidth(category.name()) + 8;
        }

        modules = Arrays.asList(Bax.INSTANCE.getModuleManager().getModules(catSelected));
        renderModuleList();
        updateScrollOffset();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    private void renderModuleList(){
        int moduleHeight = 24;
        int yOffset = boxY + 4;

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        scissor(boxX, boxY, boxW, boxH);

        for(Module module : modules ){
            int moduleY1 = yOffset;
            int moduleY2 = yOffset + moduleHeight;
            if (moduleY2 > boxY && moduleY1 < (boxY + boxH)){
                RenderUtil.drawRect(boxX + 5, yOffset, boxW - 10, 20, 0xDD555555);
                GL11.glPushMatrix();
                GL11.glScaled(0.75, 0.75, 0.75);
                fr.drawString(module.getName(), (float) ((boxX + 7) * 1.3335), (float) ((yOffset + 1) * 1.3335), -1, false);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                GL11.glScaled(0.5, 0.5, 0.5);
                fr.drawSplitString(module.getDescription(), (boxX + 7) * 2, (yOffset + fr.FONT_HEIGHT) * 2, (boxW * 2) - 24, 0xFFDDDDDD);
                GL11.glPopMatrix();

                RenderUtil.drawHollowRect(boxW + 75, yOffset + 6, 5, 5, 1, -1);
                RenderUtil.drawRect(boxW+ 76, yOffset + 7, 4, 4, module.isToggled() ? 0xFF3FF34F : 0xFA484848);
            }
            yOffset += moduleHeight;
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    private void scissor(int x, int y, int w, int h){
        int scaleFactor = sr.getScaleFactor();
        GL11.glScissor(x * scaleFactor,
                (sr.getScaledHeight() - (y + h)),
                w * scaleFactor,
                h * scaleFactor);
    }

    private void updateScrollOffset(){
        int moduleHeigth = 24;
        int visibleModules = 5;
        int totalModules = modules.size();
        int maxScroll = Math.max(0, totalModules * moduleHeigth - (visibleModules * moduleHeigth));
        if(scrollOffset < 0) {
            scrollOffset = 0;
        }
        if (scrollOffset > maxScroll){
            scrollOffset = maxScroll;
        }
    }

}
