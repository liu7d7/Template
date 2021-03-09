package me.ethius.template.events;

import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class EventMove extends Event {

    public MovementType type;
    public Vec3d vec3d;

    public EventMove(MovementType type, Vec3d vec3d) {
        this.type = type;
        this.vec3d = vec3d;
    }
}
