package net.mantori.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent COOKED_APHID_MEAT = new FoodComponent.Builder().hunger(8).saturationModifier(0.8f).meat().build();
    public static final FoodComponent APHID_MEAT = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).meat().build();
    public static final FoodComponent HONEYGLAZED_APHID_MEAT = new FoodComponent.Builder().hunger(10).saturationModifier(1f).meat().build();
    public static final FoodComponent HONEYDEW_BOTTLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).build();
}
