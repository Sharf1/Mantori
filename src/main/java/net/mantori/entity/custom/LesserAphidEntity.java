package net.mantori.entity.custom;

import net.mantori.entity.ModEntities;
import net.mantori.entity.variants.LesserAphidVariant;
import net.mantori.item.ModItems;
import net.mantori.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public class LesserAphidEntity extends AnimalEntity implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);


    public float maxWingDeviation;
    private float field_28640 = 1.0f;
    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(LesserAphidEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public LesserAphidEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0f);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.7f));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 0.6D, Ingredient.ofItems(ModItems.BEETLEBERRY), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 0.8D));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.5));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.5));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.BEETLEBERRY;
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.GLASS_BOTTLE) && !this.isBaby()) {
            player.playSound(SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, 1.0F, 1.0F);
            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, ModItems.HONEYDEW_BOTTLE.getDefaultStack());
            player.setStackInHand(hand, itemStack2);
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    private static boolean shouldBabyBeDifferent(Random random) {
        return random.nextInt(50) == 0;
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        LesserAphidEntity lesserAphid = ModEntities.LESSER_APHID.create(world);
        if (lesserAphid != null) {
            LesserAphidVariant variant = LesserAphidEntity.shouldBabyBeDifferent(this.random) ? LesserAphidVariant.getRandomUnnatural(this.random) : (this.random.nextBoolean() ? this.getVariant() : ((LesserAphidEntity)entity).getVariant());
            lesserAphid.setVariant(variant);
            lesserAphid.setPersistent();
        }
        return lesserAphid;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return this.isBaby() ? dimensions.height * 0.95F : 1.3F;
    }

    @Override
    public EntityGroup getGroup() {
        return EntityGroup.ARTHROPOD;
    }





    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }



    @Override
    protected SoundEvent getAmbientSound() {return ModSounds.LESSER_AMBIENT;}
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(ModSounds.FOOTSTEPS, 0.15f, 1.0f);
    }

    @Override
    protected boolean hasWings() {
        return this.speed > this.field_28640;
    }

    @Override
    protected void addFlapEffects() {
        this.field_28640 = this.speed + this.maxWingDeviation / 2.0f;
    }



    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "locomotion_controller", 5, this::locomotion_predicate));
    }

    private <E extends IAnimatable> PlayState locomotion_predicate(AnimationEvent<E> event) {
        LesserAphidEntity lesserAphid = (LesserAphidEntity) event.getAnimatable();

        if (this.isInAir()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lesser.fly", true));
        } else if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lesser.walk", true));
        } else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lesser.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    public boolean isInAir() {
        return !this.onGround;
    }

    public static boolean canSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return !world.getBiome(pos).toString().matches("minecraft:the_end");
    }

    public static boolean canSpawnIgnoreLightLevel(EntityType<? extends LesserAphidEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return canMobSpawn(type, world, spawnReason, pos, random) && canSpawn(type, world, spawnReason, pos, random);
    }

    /* VARIANTS */
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        boolean bl = false;
        if (entityData instanceof LesserData) {
            if (((LesserData)entityData).getSpawnedCount() >= 2) {
                bl = true;
            }
        } else {
            entityData = new LesserData(LesserAphidVariant.getRandomNatural(this.world.random), LesserAphidVariant.getRandomNatural(this.world.random));
        }
        this.setVariant(((LesserData)entityData).getRandomVariant(this.world.random));
        if (bl) {
            this.setBreedingAge(-24000);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }


    public static class LesserData
            extends PassiveEntity.PassiveData {
        public final LesserAphidVariant[] variants;

        public LesserData(LesserAphidVariant... variants) {
            super(false);
            this.variants = variants;
        }

        public LesserAphidVariant getRandomVariant(Random random) {
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
        this.setVariant(LesserAphidVariant.VARIANTS[nbt.getInt(VARIANT_KEY)]);
    }


    public LesserAphidVariant getVariant() {
        return LesserAphidVariant.VARIANTS[this.dataTracker.get(VARIANT)];
    }

    private void setVariant(LesserAphidVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }

    public static final String VARIANT_KEY = "Variant";



    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
    }
}
