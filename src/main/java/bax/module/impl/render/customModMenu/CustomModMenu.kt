package bax.module.impl.render.customModMenu

import bax.module.Category
import bax.module.ModuleInfo
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.opengl.GL11

@ModuleInfo(
    name = "ModMenu",
    description =  "renders the main mod menu",
    category = Category.RENDER
)
class CustomModMenu : GuiScreen(){
    override fun initGui() {
        super.initGui()

        
    }


    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawRoundedRect(width / 2, height / 2, width * 3 / 4, height * 3 / 4, 20, 0xFF888888.toInt())
        drawCenteredString(fontRendererObj, "Mod Menu", width / 2, height / 4 + 10, 0xFFFFFF)

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    /**
     * If Gui is running the game is not paused
     */
    override fun doesGuiPauseGame(): Boolean {
        return false
    }

    /**
     * draws a Rectangle with rounded corners
     */
    private fun drawRoundedRect( x: Int, y: Int, x2: Int, y2: Int, radius: Int, color: Int){
        val z = 0
        val alpha = (color shr 24) and 0xFF
        val red = (color shr 16) and 0xFF
        val green = (color shr 8) and 0xFF
        val blue = color and 0xFF

        //transparency if wanted
        //GlStateManager.enableBlend()
        //GlStateManager.enableTexture2D()
        //GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0)

        //set color
        GlStateManager.color(red / 255f, green / 255f, blue / 255f, alpha / 255f)

        //main shape
        GL11.glBegin(GL11.GL_QUADS)
        //Center part without corners
        GL11.glVertex3f((x + radius).toFloat(), y.toFloat(), z.toFloat())
        GL11.glVertex3f((x2 - radius).toFloat(), y.toFloat(), z.toFloat())
        GL11.glVertex3f((x2 - radius).toFloat(), y2.toFloat(), z.toFloat())
        GL11.glVertex3f((x + radius).toFloat(), y2.toFloat(), z.toFloat())
        GL11.glEnd()

        //draw corners
        //top left
        drawCorner(x + radius, y + radius, radius, Math.PI, 1.5 * Math.PI)
        //top right
        drawCorner(x2 - radius, y + radius, radius, 1.5 * Math.PI, 2 * Math.PI)
        //bottom left
        drawCorner(x2 - radius, y2 - radius, radius, 0.0, 0.5 * Math.PI)
        //bottom right
        drawCorner(x + radius, y2 - radius, radius, 0.5 * Math.PI, Math.PI)

        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()

    }

    /**
     * draws rounded corner as a helper function for drawRoundedRect
     */
    private fun drawCorner(cx: Int, cy: Int, r: Int, start: Double, end: Double){
        GL11.glBegin(GL11.GL_TRIANGLE_FAN)

        //Center point
        GL11.glVertex2f(cx.toFloat(), cy.toFloat())

        var angle = start
        while(angle <= end){

            GL11.glVertex2f(
                (cx + Math.cos(angle) * r).toFloat(),
                (cy + Math.sin(angle) * r).toFloat()
            )

            angle += Math.PI / 16
        }
        GL11.glEnd()
    }



}