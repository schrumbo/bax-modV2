package bax.module.impl.render.clickGUI;

import bax.Bax;
import bax.module.Category;
import bax.module.Module;
import bax.module.ModuleInfo;
import bax.module.impl.render.util.RenderUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.logging.log4j.core.pattern.ConverterKeys;

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

    private final int menuW = boxW;
    private final int menuH = 20;
    private final int menuX = boxX; // Dieselbe X-Koordinate wie box
    private final int menuY = boxY + boxH; // 8 Pixel Abstand unter dem box

    private List<Module> modules;
    private Category catSelected = Category.RENDER;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawRect(boxX, boxY, boxW, boxH, 0xFF222222);
        RenderUtil.drawRect(menuX, menuY, menuW, menuH, 0xDD244442);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }



}
