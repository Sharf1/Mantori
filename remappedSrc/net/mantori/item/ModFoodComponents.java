package net.mantori.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent COOKED_APHID_MEAT = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).meat().build();
    public static final FoodComponent APHID_MEAT = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).meat().build();
    public static final FoodComponent HONEYGLAZED_APHID_MEAT = new FoodComponent.Builder().hunger(10).saturationModifier(1f).meat().build();
    public static final FoodComponent END_WING = new FoodComponent.Builder().hunger(7).saturationModifier(0.7f).meat().build();
    public static final FoodComponent ELYTRA_SHARD = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).meat().build();
    public static final FoodComponent GLAZED_WING = new FoodComponent.Builder().hunger(9).saturationModifier(0.9f).meat().build();
    public static final FoodComponent HONEYDEW_BOTTLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).meat().build();
    public static final FoodComponent BEETLEBERRY = new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build();
    public static final FoodComponent DRAGONS_BREATH = new FoodComponent.Builder().hunger(2).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 12000, 0), 1.0f).build();
    public static final FoodComponent HOT_SAUCE = new FoodComponent.Builder().hunger(4).saturationModifier(0.9F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 250, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 15000, 0), 1.0f).build();
    public static final FoodComponent HOT_WING = new FoodComponent.Builder().hunger(15).saturationModifier(1.5F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 400, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 30000, 0), 1.0f).build();
    public static final FoodComponent BEETLEBERRY_JAM = new FoodComponent.Builder().hunger(6).saturationModifier(0.4f).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1), 1.0F).build();
}
