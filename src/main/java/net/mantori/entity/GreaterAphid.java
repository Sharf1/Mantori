package net.mantori.entity;

import net.mantori.sounds.ModSounds;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GreaterAphid extends BaseAphidEntity {
    private static final UUID HORSE_ARMOR_BONUS_ID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
    private static final TrackedData<Integer> VARIANT;

    public GreaterAphid(EntityType<? extends GreaterAphid> entityType, World world) {
        super(entityType, world);
    }

    protected void initAttributes() {
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(this.getChildHealthBonus());
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(this.getChildMovementSpeedBonus());
        this.getAttributeInstance(EntityAttributes.HORSE_JUMP_STRENGTH).setBaseValue(this.getChildJumpStrengthBonus());
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(VARIANT, 0);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant());
    }

    private void equipArmor(ItemStack stack) {
        this.equipStack(EquipmentSlot.CHEST, stack);
        this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(nbt.getInt("Variant"));
    }

    private void setVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    private int getVariant() {
        return this.dataTracker.get(VARIANT);
    }

    private void setVariant(AphidColor color, AphidMarking marking) {
        this.setVariant(color.getIndex() & 255 | marking.getIndex() << 8 & '\uff00');
    }

    public AphidColor getColor() {
        return AphidColor.byIndex(this.getVariant() & 255);
    }

    public AphidMarking getMarking() {
        return AphidMarking.byIndex((this.getVariant() & '\uff00') >> 8);
    }

    protected void playWalkSound(BlockSoundGroup group) {
        super.playWalkSound(group);
        if (this.random.nextInt(10) == 0) {
            this.playSound(ModSounds.BREATHE, group.getVolume() * 0.6F, group.getPitch());
        }
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

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!this.isBaby()) {
            if (this.hasPassengers()) {
                return super.interactMob(player, hand);
            }
        }

        if (!itemStack.isEmpty()) {
            if (this.isBreedingItem(itemStack)) {
                return this.interactHorse(player, itemStack);
            }

            ActionResult actionResult = itemStack.useOnEntity(player, this, hand);
            if (actionResult.isAccepted()) {
                return actionResult;
            }

            if (!this.isTame()) {
                this.playAngrySound();
                return ActionResult.success(this.world.isClient);
            }
        }

        if (this.isBaby()) {
            return super.interactMob(player, hand);
        } else {
            this.putPlayerOnBack(player);
            return ActionResult.success(this.world.isClient);
        }
    }

    @Override
    public boolean canBreedWith(AnimalEntity other) {
        if (other == this) {
            return false;
        } else if (!(other instanceof DonkeyEntity) && !(other instanceof GreaterAphid)) {
            return false;
        } else {
            return this.canBreed() && ((BaseAphidEntity) other).canBreed();
        }
    }

    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        BaseAphidEntity BaseAphidEntity;
        GreaterAphid horseEntity = (GreaterAphid) entity;
        BaseAphidEntity = ModEntityTypes.GREATER_APHID_ENTITY_TYPE.create(world);
        int i = this.random.nextInt(9);
        AphidColor horseColor;
        if (i < 4) {
            horseColor = this.getColor();
        } else if (i < 8) {
            horseColor = horseEntity.getColor();
        } else {
            horseColor = Util.getRandom(AphidColor.values(), this.random);
        }

        int j = this.random.nextInt(5);
        AphidMarking horseMarking;
        if (j < 2) {
            horseMarking = this.getMarking();
        } else if (j < 4) {
            horseMarking = horseEntity.getMarking();
        } else {
            horseMarking = Util.getRandom(AphidMarking.values(), this.random);
        }

        this.setVariant(horseColor, horseMarking);

        this.setChildAttributes(entity, BaseAphidEntity);
        return BaseAphidEntity;
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        AphidColor horseColor;
        if (entityData instanceof AphidData) {
            horseColor = ((AphidData) entityData).color;
        } else {
            horseColor = Util.getRandom(AphidColor.values(), this.random);
            entityData = new AphidData(horseColor);
        }

        this.setVariant(horseColor, Util.getRandom(AphidMarking.values(), this.random));
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    static {
        VARIANT = DataTracker.registerData(GreaterAphid.class, TrackedDataHandlerRegistry.INTEGER);
    }

    public static class AphidData extends PassiveData {
        public final AphidColor color;

        public AphidData(AphidColor color) {
            super(true);
            this.color = color;
        }
    }
}