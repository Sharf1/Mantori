package net.mantori.entity;

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
import net.minecraft.inventory.Inventory;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        if (!this.items.getStack(1).isEmpty()) {
            nbt.put("ArmorItem", this.items.getStack(1).writeNbt(new NbtCompound()));
        }

    }

    public ItemStack getArmorType() {
        return this.getEquippedStack(EquipmentSlot.CHEST);
    }

    private void equipArmor(ItemStack stack) {
        this.equipStack(EquipmentSlot.CHEST, stack);
        this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0F);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(nbt.getInt("Variant"));
        if (nbt.contains("ArmorItem", 10)) {
            ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("ArmorItem"));
            if (!itemStack.isEmpty() && this.isHorseArmor(itemStack)) {
                this.items.setStack(1, itemStack);
            }
        }

        this.updateSaddle();
    }

    private void setVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    private int getVariant() {
        return (Integer) this.dataTracker.get(VARIANT);
    }

    private void setVariant(HorseColor color, HorseMarking marking) {
        this.setVariant(color.getIndex() & 255 | marking.getIndex() << 8 & '\uff00');
    }

    public HorseColor getColor() {
        return HorseColor.byIndex(this.getVariant() & 255);
    }

    public HorseMarking getMarking() {
        return HorseMarking.byIndex((this.getVariant() & '\uff00') >> 8);
    }

    protected void updateSaddle() {
        if (!this.world.isClient) {
            super.updateSaddle();
            this.setArmorTypeFromStack(this.items.getStack(1));
            this.setEquipmentDropChance(EquipmentSlot.CHEST, 0.0F);
        }
    }

    private void setArmorTypeFromStack(ItemStack stack) {
        this.equipArmor(stack);
        if (!this.world.isClient) {
            this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).removeModifier(HORSE_ARMOR_BONUS_ID);
            if (this.isHorseArmor(stack)) {
                int i = ((HorseArmorItem) stack.getItem()).getBonus();
                if (i != 0) {
                    this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).addTemporaryModifier(new EntityAttributeModifier(HORSE_ARMOR_BONUS_ID, "Horse armor bonus", (double) i, EntityAttributeModifier.Operation.ADDITION));
                }
            }
        }

    }

    protected void playWalkSound(BlockSoundGroup group) {
        super.playWalkSound(group);
        if (this.random.nextInt(10) == 0) {
            this.playSound(SoundEvents.ENTITY_HORSE_BREATHE, group.getVolume() * 0.6F, group.getPitch());
        }

    }

    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    @Nullable
    protected SoundEvent getEatSound() {
        return SoundEvents.ENTITY_HORSE_EAT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        super.getHurtSound(source);
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    protected SoundEvent getAngrySound() {
        super.getAngrySound();
        return SoundEvents.ENTITY_HORSE_ANGRY;
    }

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

    public boolean canBreedWith(AnimalEntity other) {
        if (other == this) {
            return false;
        } else if (!(other instanceof DonkeyEntity) && !(other instanceof HorseEntity)) {
            return false;
        } else {
            return this.canBreed() && ((BaseAphidEntity) other).canBreed();
        }
    }

    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        BaseAphidEntity BaseAphidEntity;
        HorseEntity horseEntity = (HorseEntity) entity;
        BaseAphidEntity = ModEntityTypes.GREATER_APHID_ENTITY_TYPE.create(world);
        int i = this.random.nextInt(9);
        HorseColor horseColor;
        if (i < 4) {
            horseColor = this.getColor();
        } else if (i < 8) {
            horseColor = horseEntity.getColor();
        } else {
            horseColor = Util.getRandom(HorseColor.values(), this.random);
        }

        int j = this.random.nextInt(5);
        HorseMarking horseMarking;
        if (j < 2) {
            horseMarking = this.getMarking();
        } else if (j < 4) {
            horseMarking = horseEntity.getMarking();
        } else {
            horseMarking = (HorseMarking) Util.getRandom(HorseMarking.values(), this.random);
        }

        this.setVariant(horseColor, horseMarking);

        this.setChildAttributes(entity, BaseAphidEntity);
        return BaseAphidEntity;
    }

    public boolean hasArmorSlot() {
        return true;
    }

    public boolean isHorseArmor(ItemStack item) {
        return item.getItem() instanceof HorseArmorItem;
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        HorseColor horseColor;
        if (entityData instanceof HorseEntity.HorseData) {
            horseColor = ((HorseEntity.HorseData) entityData).color;
        } else {
            horseColor = (HorseColor) Util.getRandom(HorseColor.values(), this.random);
            entityData = new HorseEntity.HorseData(horseColor);
        }

        this.setVariant(horseColor, (HorseMarking) Util.getRandom(HorseMarking.values(), this.random));
        return super.initialize(world, difficulty, spawnReason, (EntityData) entityData, entityNbt);
    }

    static {
        VARIANT = DataTracker.registerData(HorseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    public static class HorseData extends PassiveData {
        public final HorseColor color;

        public HorseData(HorseColor color) {
            super(true);
            this.color = color;
        }
    }
}