package net.mantori.entity.goal;

import net.mantori.entity.BaseAphidEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class AphidBondWithPlayerGoal extends Goal {
    private final BaseAphidEntity baseAphid;
    private final double speed;
    private double targetX;
    private double targetY;
    private double targetZ;

    public AphidBondWithPlayerGoal(BaseAphidEntity horse, double speed) {
        this.baseAphid = horse;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.baseAphid.isTame() && this.baseAphid.hasPassengers()) {
            Vec3d vec3d = NoPenaltyTargeting.find(this.baseAphid, 5, 4);
            if (vec3d == null) {
                return false;
            } else {
                this.targetX = vec3d.x;
                this.targetY = vec3d.y;
                this.targetZ = vec3d.z;
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void start() {
        this.baseAphid.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
    }

    @Override
    public boolean shouldContinue() {
        return !this.baseAphid.isTame() && !this.baseAphid.getNavigation().isIdle() && this.baseAphid.hasPassengers();
    }

    @Override
    public void tick() {
        if (!this.baseAphid.isTame() && this.baseAphid.getRandom().nextInt(this.getTickCount(50)) == 0) {
            Entity entity = this.baseAphid.getPassengerList().get(0);
            if (entity == null) {
                return;
            }

            if (entity instanceof PlayerEntity) {
                int i = this.baseAphid.getTemper();
                int j = this.baseAphid.getMaxTemper();
                if (j > 0 && this.baseAphid.getRandom().nextInt(j) < i) {
                    this.baseAphid.bondWithPlayer((PlayerEntity)entity);
                    return;
                }

                this.baseAphid.addTemper(5);
            }

            this.baseAphid.removeAllPassengers();
            this.baseAphid.playAngrySound();
            this.baseAphid.world.sendEntityStatus(this.baseAphid, (byte)6);
        }

    }
}
