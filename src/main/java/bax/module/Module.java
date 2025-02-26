package bax.module;

import bax.Bax;
import bax.event.Event2D;
import bax.event.EventKey;
import bax.event.EventUpdate;
import lombok.Getter;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.commons.lang3.Validate;

@Getter
public abstract class Module implements Subscriber {
    private final String name, description;
    private final Category category;
    private final   boolean enabledByDefault;
    private boolean toggled;

    protected final Minecraft mc = Bax.INSTANCE.getMc();
    protected final FontRenderer fr = Bax.INSTANCE.getFr();

    /**
     * creates a module
     */
    public Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        Validate.notNull(info, "confused annotation exception");
        this.name = info.name();
        this.description = info.description();
        this.category = info.category();
        this.enabledByDefault = info.enabled();

        if(enabledByDefault) toggle();
    }

    /**
     * changes the value of toggled boolean
     */
    public void toggle(){
        setEnabled(!toggled);
    }

    /**
     * Enables
     * @param state true if u want to enable
     */
    public void setEnabled(boolean state){
        if (this.toggled == state){
            return;
        }
        this.toggled = state;
        if (state){
            Bax.BUS.subscribe(this);
            Bax.BUS.subscribe(eventKeyListener);
            Bax.BUS.subscribe(event2DListener);
            Bax.BUS.subscribe(eventUpdateListener);
            onEnable();
        }else{
            Bax.BUS.unsubscribe(this);
            Bax.BUS.unsubscribe(eventKeyListener);
            Bax.BUS.unsubscribe(event2DListener);
            Bax.BUS.unsubscribe(eventUpdateListener);
            onDisable();
        }
    }

    //implementation in child classes
    public void onEnable(){}

    public void onDisable(){}
    public void onUpdate(){}
    public void on2D(ScaledResolution sr){}
    public void onKey(int key){}

    /**
     * tracks updates
     */
    @Subscribe
    private final Listener<EventUpdate> eventUpdateListener = new Listener<>(e -> {
        if (toggled){
            onUpdate();
        }
    });

    /**
     * tracks 2D events
     */
    @Subscribe
    private final Listener<Event2D> event2DListener = new Listener<>(e -> {
        if (toggled){
            on2D(e.getSr());
        }
    });


    /**
     * tracks user inputs
     */
    @Subscribe
    private final Listener<EventKey> eventKeyListener = new Listener<>(e -> {
        if (toggled){
            onKey(e.getKey());
        }
    });

}
