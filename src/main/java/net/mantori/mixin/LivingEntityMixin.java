package net.mantori.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.mantori.interfaces.LivingEntityOffGroundSpeedView;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, LivingEntityOffGroundSpeedView {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Unique
	private float offGroundSpeed = 0.7f;
	
	@Unique
	public void setOffGroundSpeed(float speed) {
		this.offGroundSpeed = speed;
	}
	@Inject(method = "getOffGroundSpeed", at = @At("HEAD"), cancellable = true)
	public void onGetOffGroundSpeed(CallbackInfoReturnable<Float> cir) {
		cir.setReturnValue(this.getControllingPassenger() instanceof PlayerEntity ? offGroundSpeed : 0.02f);
	}

}
