package bax.module.impl.movement;

import bax.module.Category;
import bax.module.Module;
import bax.module.ModuleInfo;
//TODO DO NOT USE THIS SHIT, ITS AWFUL
@ModuleInfo(
        name = "Flight",
        description = "lets u fly",
        category = Category.MOVEMENT,
        enabled = true
)
public class Flight extends Module {
    @Override
    public void onUpdate() {
        if (mc.thePlayer != null){
            mc.thePlayer.capabilities.isFlying = true;

            if (mc.gameSettings.keyBindJump.isPressed()){
                mc.thePlayer.motionY += 0.2;
            }
            if (mc.gameSettings.keyBindSneak.isPressed()){
                mc.thePlayer.motionY -=0.2;
            }
            if (mc.gameSettings.keyBindForward.isPressed()){
                mc.thePlayer.capabilities.setFlySpeed(0.25f);
            }
        }
    }

    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;
    }
}
