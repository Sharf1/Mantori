package net.mantori.entity.custom;

import java.util.Arrays;

import org.jetbrains.annotations.Nullable;

import net.mantori.entity.ModEntities;
import net.mantori.entity.variants.GreaterAphidVariant;
import net.mantori.interfaces.LivingEntityOffGroundSpeedView;
import net.mantori.item.ModItems;
import net.mantori.mixin.HorseBaseEntityAccessor;
import net.mantori.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HorseBondWithPlayerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.EntityView;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GreaterAphidEntity extends AbstractHorseEntity implements GeoEntity {
    private static final Item[] BREEDING_INGREDIENT = {ModItems.BEETLEBERRY};
    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(GreaterAphidEntity.class, TrackedDataHandlerRegistry.INTEGER);
    protected int soundTicks;
    protected int eatingTicks = 0;


    public GreaterAphidEntity(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0f);
        // added for 1.20.x
        ((LivingEntityOffGroundSpeedView) this).setOffGroundSpeed(this.getMovementSpeed());;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.2));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, Ingredient.ofItems(ModItems.BEETLEBERRY), false));

    }

    @Override
    protected void initAttributes(Random random) {
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(this.getChildHealthBonus());
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(this.getChildMovementSpeedBonus());
        this.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(this.getChildJumpStrengthBonus());
    }
    protected float getChildHealthBonus() {
        return 20.0f + (float)this.random.nextInt(11) + (float)this.random.nextInt(9);
    }

    protected double getChildJumpStrengthBonus() {
        return (double)1.5f + this.random.nextDouble() * 0.7 + this.random.nextDouble() * 0.4 + this.random.nextDouble() * 0.4;
    }

    protected double getChildMovementSpeedBonus() {
        return ((double)0.9f + this.random.nextDouble() * 0.3 + this.random.nextDouble() * 0.3 + this.random.nextDouble() * 0.3) * 0.25;
    }
    public void updatePassengerPosition(Entity passenger) {
        super.updatePassengerPosition(passenger);
        if (passenger instanceof MobEntity) {
            MobEntity mobEntity = (MobEntity) passenger;
            this.bodyYaw = mobEntity.bodyYaw;
        }

            float f = MathHelper.sin(this.bodyYaw * 0.017453292F);
            float g = MathHelper.cos(this.bodyYaw * 0.017453292F);
            float h = 0.4F;
            float i = 0.33F;
            passenger.setPosition(this.getX() + (double) (h * f), this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset() + (double) i, this.getZ() - (double) (h * g));
            if (passenger instanceof LivingEntity) {
                ((LivingEntity) passenger).bodyYaw = this.bodyYaw;
        }
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return true;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.95F;
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        if (fallDistance > 1.0F) {
            this.playSound(ModSounds.LAND, 0.4F, 1.0F);
        }

        return false;
    }

    public static boolean canSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return !world.getBiome(pos).toString().matches("minecraft:the_end");
    }

    public static boolean canSpawnIgnoreLightLevel(EntityType<? extends GreaterAphidEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return canMobSpawn(type, world, spawnReason, pos, random) && canSpawn(type, world, spawnReason, pos, random);
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.ARTHROPOD;
    }




    @Override
    public boolean canBreedWith(AnimalEntity other) {
        return other != this && other instanceof GreaterAphidEntity &&  this.isInLove() && other.isInLove();
    }

    private static boolean shouldBabyBeDifferent(Random random) {
        return random.nextInt(30) == 0;
    }

    protected void setAttributes(PassiveEntity mate, GreaterAphidEntity child) {
        double d = this.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) + mate.getAttributeBaseValue(EntityAttributes.GENERIC_MAX_HEALTH) + (double)this.getChildHealthBonus();
        child.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(d / 3.0D);
        double e = this.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH) + mate.getAttributeBaseValue(EntityAttributes.HORSE_JUMP_STRENGTH) + this.getChildJumpStrengthBonus();
        child.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(e / 3.0D);
        double f = this.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) + mate.getAttributeBaseValue(EntityAttributes.GENERIC_MOVEMENT_SPEED) + this.getChildMovementSpeedBonus();
        child.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(f / 3.0D);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        GreaterAphidEntity greaterAphid = ModEntities.GREATER_APHID.create(world);
        if (greaterAphid != null) {
            GreaterAphidVariant variant = GreaterAphidEntity.shouldBabyBeDifferent(this.random) ? GreaterAphidVariant.getRandomUnnatural(this.random) : (this.random.nextBoolean() ? this.getVariant() : ((GreaterAphidEntity)entity).getVariant());
            greaterAphid.setVariant(variant);
            greaterAphid.setPersistent();
        }
        this.setAttributes(entity, greaterAphid);
        return greaterAphid;
    }


    /* VARIANTS */
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        boolean bl = false;
        if (entityData instanceof GreaterAphidEntity.GreaterData) {
            if (((GreaterAphidEntity.GreaterData)entityData).getSpawnedCount() >= 2) {
                bl = true;
            }
        } else {
            entityData = new GreaterAphidEntity.GreaterData(GreaterAphidVariant.getRandomNatural(this.getWorld().random), GreaterAphidVariant.getRandomNatural(this.getWorld().random));
        }
        this.setVariant(((GreaterAphidEntity.GreaterData)entityData).getRandomVariant(this.getWorld().random));
        if (bl) {
            this.setBreedingAge(-24000);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }


    public static class GreaterData
            extends PassiveEntity.PassiveData {
        public final GreaterAphidVariant[] variants;

        public GreaterData(GreaterAphidVariant... variants) {
            super(false);
            this.variants = variants;
        }

        public GreaterAphidVariant getRandomVariant(Random random) {
            return this.variants[random.nextInt(this.variants.length)];
        }
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(VARIANT_KEY, this.getVariant().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(GreaterAphidVariant.VARIANTS[nbt.getInt(VARIANT_KEY)]);
    }


    public GreaterAphidVariant getVariant() {
        return GreaterAphidVariant.VARIANTS[this.dataTracker.get(VARIANT)];
    }

    private void setVariant(GreaterAphidVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }

    public static final String VARIANT_KEY = "Variant";



    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
    }


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!this.isBaby()) {
            if (this.isTame() && player.shouldCancelInteraction()) {
                this.openInventory(player);
                return ActionResult.success(this.getWorld().isClient);
            }

            if (this.hasPassengers()) {
                return super.interactMob(player, hand);
            }
        }

        if (!itemStack.isEmpty()) {
            if (this.isBreedingItem(itemStack)) {
                return this.interactBird(player, itemStack);
            }

            ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
            if (actionResult.isAccepted()) {
                return actionResult;
            }

            if (!this.isTame()) {
                this.playAngrySound();
                return ActionResult.success(this.getWorld().isClient);
            }
        }

        if (this.isBaby()) {
            return super.interactMob(player, hand);
        } else {
            this.putPlayerOnBack(player);
            return ActionResult.success(this.getWorld().isClient);
        }
    }
    public ActionResult interactBird(PlayerEntity player, ItemStack stack) {
        boolean bl = this.receiveFood(player, stack);
        if (!player.getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if (this.getWorld().isClient) {
            return ActionResult.CONSUME;
        }
        return bl ? ActionResult.SUCCESS : ActionResult.PASS;
    }

    protected boolean receiveFood(PlayerEntity player, ItemStack item) {
        boolean bl = false;
        float health = 0.0f;
        int temper = 0;
        if (item.isOf(ModItems.BEETLEBERRY)) {
            health = 10.0f;
            temper = 10;
            if (!this.getWorld().isClient && this.isTame() && this.getBreedingAge() == 0 && !this.isInLove()) {
                bl = true;
                this.lovePlayer(player);
            }
        }
        if (this.getHealth() < this.getMaxHealth() && health > 0.0f) {
            this.heal(health);
            bl = true;
        }
        if (temper > 0 && (bl || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
            bl = true;
            if (!this.getWorld().isClient) {
                this.addTemper(temper);
            }
        }
        if (bl) {
            this.playEatingAnimation();
            this.emitGameEvent(GameEvent.EAT);
        }
        return bl;
    }
    private void playEatingAnimation() {
        SoundEvent soundEvent;
        ((HorseBaseEntityAccessor)this).invokeSetEating();
        this.eatingTicks = 10;
        if (!this.isSilent() && (soundEvent = this.getEatSound()) != null) {
            this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundCategory(), 1.0f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.2f);
        }
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return (Arrays.stream(BREEDING_INGREDIENT).anyMatch(stack::isOf));
    }

    @Override
    public boolean hasArmorSlot() {
        return false;
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile() && this.hasPassengers() || this.isEatingGrass() || this.isAngry();
    }

    private boolean jumping;

    @Override
    public void setJumpStrength(int strength) {
        if (strength < 0) {
            strength = 0;
        } else {
            this.jumping = true;
        }
        this.jumpStrength = strength >= 90 ? 1.5f : 0.7f + 0.7f * (float)strength / 90.0f;
    }


    @Override
    public void startJumping(int height) {
        this.jumping = true;
        this.playJumpSound();
    }

    @Override
    public void stopJumping() {
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isAlive()) {
            //LivingEntity livingEntity = this.getPrimaryPassenger();
        	LivingEntity livingEntity = this.getControllingPassenger();
            if (this.hasPassengers() && livingEntity != null) {
                this.setYaw(livingEntity.getYaw());
                this.prevYaw = this.getYaw();
                this.setPitch(livingEntity.getPitch() * 0.5F);
                this.setRotation(this.getYaw(), this.getPitch());
                this.bodyYaw = this.getYaw();
                this.headYaw = this.bodyYaw;
                float f = livingEntity.sidewaysSpeed * 0.5F;
                float g = livingEntity.forwardSpeed;
                if (g <= 0.0F) {
                    g *= 0.25F;
                    this.soundTicks = 0;
                }

                if (this.isOnGround() && this.jumpStrength == 0.0F && this.isAngry() && !this.jumping) {
                    f = 0.0F;
                    g = 0.0F;
                }

                if (this.jumpStrength > 0.0F && !this.isInAir() && this.isOnGround()) {
                    double d = this.getJumpStrength() * (double)this.jumpStrength * (double)this.getJumpVelocityMultiplier();
                    double e = d + this.getJumpBoostVelocityModifier();
                    Vec3d vec3d = this.getVelocity();
                    this.setVelocity(vec3d.x, e, vec3d.z);
                    this.setInAir(true);
                    this.velocityDirty = true;
                    if (g > 0.0F) {
                        float h = MathHelper.sin(this.getYaw() * 0.017453292F);
                        float i = MathHelper.cos(this.getYaw() * 0.017453292F);
                        this.setVelocity(this.getVelocity().add((double)(-0.4F * h * this.jumpStrength), 0.0, (double)(0.4F * i * this.jumpStrength)));
                    }

                    this.jumpStrength = 0.0F;
                }

                //this.airStrafingSpeed = this.getMovementSpeed() * 0.1F;
                ((LivingEntityOffGroundSpeedView) this).setOffGroundSpeed(this.getMovementSpeed() * 0.1f);
                //
                if (this.isLogicalSideForUpdatingMovement()) {
                    this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                    super.travel(new Vec3d((double)f, movementInput.y, (double)g));
                } else if (livingEntity instanceof PlayerEntity) {
                    this.setVelocity(Vec3d.ZERO);
                }

                if (this.isOnGround()) {
                    this.jumpStrength = 0.0F;
                    this.setInAir(false);
                }

                this.updateLimbs(false);
                this.tryCheckBlockCollision();
            } else {
                //this.airStrafingSpeed = 0.02F;
            	((LivingEntityOffGroundSpeedView) this).setOffGroundSpeed(0.02f);
                super.travel(movementInput);
            }
        }
    }

    protected void playJumpSound() {
        this.playSound(ModSounds.JUMP, 0.5f, 1.0f);
    }


    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return ModSounds.AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return ModSounds.DEATH;
    }

    @Nullable
    protected SoundEvent getEatSound() {
        return ModSounds.EAT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        super.getHurtSound(source);
        return ModSounds.HURT;
    }

    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return ModSounds.ANGRY;
    }


    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!state.isLiquid()) {
            BlockState blockState = this.getWorld().getBlockState(pos.up());
            BlockSoundGroup blockSoundGroup = state.getSoundGroup();
            if (blockState.isOf(Blocks.SNOW)) {
                blockSoundGroup = blockState.getSoundGroup();
            }

            if (this.hasPassengers() && this.playExtraHorseSounds) {
                ++this.soundTicks;
                if (this.soundTicks > 5 && this.soundTicks % 3 == 0) {
                    this.playWalkSound(blockSoundGroup);
                } else if (this.soundTicks <= 5) {
                    this.playSound(ModSounds.FOOTSTEPS_WOOD, blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
                }
            } else if (blockSoundGroup == BlockSoundGroup.WOOD) {
                this.playSound(ModSounds.FOOTSTEPS_WOOD, blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
            } else {
                this.playSound(ModSounds.FOOTSTEPS, blockSoundGroup.getVolume() * 0.15F, blockSoundGroup.getPitch());
            }

        }
    }

    protected void playWalkSound(BlockSoundGroup group) {
        this.playSound(ModSounds.GALLOP, group.getVolume() * 0.15F, group.getPitch());
    }





    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                DefaultAnimations.genericWalkRunIdleController(this)
        );
            }




    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

	@Override
	public /* synthetic */ EntityView method_48926() {
		return super.getWorld();
	}

}
