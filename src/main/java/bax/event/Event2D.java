package bax.event;

import bax.event.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.gui.ScaledResolution;

@Getter
@AllArgsConstructor
public class Event2D extends Event {
    private final ScaledResolution sr;
}
