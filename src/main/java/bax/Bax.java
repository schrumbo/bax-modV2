package bax;


import bax.module.ModuleManager;
import bax.event.Event2D;
import bax.event.EventKey;
import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

@Getter
public enum Bax implements Subscriber {
    INSTANCE;

    private String clientName = "Bax";
    private String version = "v0.1";

    private final Minecraft mc = Minecraft.getMinecraft();
    private final FontRenderer fr = mc.fontRendererObj;

    private ModuleManager moduleManager;

    public static final EventBus BUS = EventManager.builder().setName("root/bax").setSuperListeners().build();
    public void init(){
        BUS.subscribe(this);
        Display.setTitle(clientName + " " + version);

        moduleManager = new ModuleManager();
    }

    public void shutdown(){
        BUS.unsubscribe(this);
    }



}
