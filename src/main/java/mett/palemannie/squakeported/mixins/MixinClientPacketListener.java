package mett.palemannie.squakeported.mixins;

import mett.palemannie.squakeported.ISquakeEntity;
import mett.palemannie.squakeported.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPacketListener.class)
public abstract class MixinClientPacketListener {
    @Unique
    private Player sqe$damagedPlayer;
    @Unique
    private int sqe$damageMotionDeadline;
    @Unique
    private boolean sqe$damageHasKnockback;

    @Redirect(method = "handleDamageEvent", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;handleDamageEvent(Lnet/minecraft/world/damagesource/DamageSource;)V"))
    private void sqe$rememberDamage(Entity entity, DamageSource source) {
        entity.handleDamageEvent(source);
        if (entity == Minecraft.getInstance().player) {
            // NO_IMPACT damage does not request a velocity update from the server.
            sqe$damagedPlayer = source.is(DamageTypeTags.NO_IMPACT) ? null : (Player) entity;
            sqe$damageMotionDeadline = entity.tickCount + 10;
            // In 1.21, knockback no longer requires an attacking entity.
            sqe$damageHasKnockback = !source.is(DamageTypeTags.NO_KNOCKBACK);
        }
    }

    @Redirect(method = "handleSetEntityMotion", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;lerpMotion(DDD)V"))
    private void sqe$preserveDamageMomentum(Entity entity, double x, double y, double z) {
        if (entity == sqe$damagedPlayer) {
            Player player = sqe$damagedPlayer;
            sqe$damagedPlayer = null;
            if (player == Minecraft.getInstance().player && player.tickCount <= sqe$damageMotionDeadline
                    && ModConfig.isEnabled() && player.isAlive() && !player.isPassenger()
                    && !player.getAbilities().flying && !player.isFallFlying()
                    && !player.hasEffect(MobEffects.LEVITATION) && !player.onClimbable()
                    && !player.isInLava() && (!player.isInWater() || ModConfig.sharkingEnabled())
                    && !((ISquakeEntity) player).shouldReturnMovement_Squake()) {
                // Quake velocity exists on the client; damage must not replace
                // that momentum with the server's independently simulated velocity.
                Vec3 current = player.getDeltaMovement();
                if (sqe$damageHasKnockback) {
                    x += current.x;
                    y += current.y;
                    z += current.z;
                } else {
                    x = current.x;
                    y = current.y;
                    z = current.z;
                }
            }
        }
        entity.lerpMotion(x, y, z);
    }
}
