package me.ethius.template.mixins;

import com.mojang.authlib.GameProfile;
import me.ethius.template.Template;
import me.ethius.template.events.EventMove;
import me.ethius.template.events.EventTick;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity extends AbstractClientPlayerEntity {

    @Shadow
    @Final
    protected MinecraftClient client;
    public MixinClientPlayerEntity(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At("HEAD"), method = "move", cancellable = true)
    public void move(MovementType movementType_1, Vec3d vec3d_1, CallbackInfo info) {
        EventMove event = new EventMove(movementType_1, vec3d_1);
        Template.eventBus.post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        EventTick event = new EventTick();
        Template.eventBus.post(event);
        if (event.isCancelled())
            info.cancel();
    }
}
