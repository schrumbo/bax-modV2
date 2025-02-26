package bax.module.impl.movement;


import bax.module.Category;
import bax.module.Module;
import bax.module.ModuleInfo;

@ModuleInfo(
        name = "Toggle Sprint",
        description = "Keeps sprinting without holding the sprint key",
        category = Category.MOVEMENT,
        enabled = true
)
public class ToggleSprint extends Module {
    @Override
    public void onUpdate() {
        mc.gameSettings.keyBindSprint.setPressed(true);
    }
}
