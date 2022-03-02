package net.mantori.client.render;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mantori.Mantori;
import net.mantori.client.ModModelLayers;
import net.mantori.client.model.LesserAphidModel;
import net.mantori.entity.LesserAphidColor;
import net.mantori.entity.LesserAphid;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class LesserAphidRenderer extends BaseLesserAphidRenderer<LesserAphid, LesserAphidModel<LesserAphid>> {
    private static final Map<LesserAphidColor, @Nullable Object> TEXTURES = Util.make(Maps.newEnumMap(LesserAphidColor.class), (enumMap) -> {
        enumMap.put(LesserAphidColor.BLACK, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_black.png"));
        enumMap.put(LesserAphidColor.BLUEGREEN, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_bluegreen.png"));
        enumMap.put(LesserAphidColor.BROWN, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_brown.png"));
        enumMap.put(LesserAphidColor.CYAN, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_cyan.png"));
        enumMap.put(LesserAphidColor.GRAY, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_gray.png"));
        enumMap.put(LesserAphidColor.GREEN, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_green.png"));
        enumMap.put(LesserAphidColor.NATURAL, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_natural.png"));
        enumMap.put(LesserAphidColor.ORANGE, new Identifier(Mantori.MOD_ID, "textures/entity/lesser_orange.png"));
    });

    public LesserAphidRenderer(EntityRendererFactory.Context context) {
        super(context, new LesserAphidModel<>(context.getPart(ModModelLayers.LESSER_APHID)), 0.7F);
    }

    public Identifier getTexture(LesserAphid lesserAphid) {
        if (lesserAphid.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/lesser_child.png");
        else return (Identifier)TEXTURES.get(lesserAphid.getColor());
    }
}