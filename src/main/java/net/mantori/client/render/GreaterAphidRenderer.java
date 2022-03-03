package net.mantori.client.render;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mantori.Mantori;
import net.mantori.client.ModModelLayers;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.client.render.layer.GreaterAphidMarkingRenderer;
import net.mantori.entity.GreaterAphidColor;
import net.mantori.entity.GreaterAphid;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class GreaterAphidRenderer extends BaseAphidRenderer<GreaterAphid, GreaterAphidModel<GreaterAphid>> {
    private static final Map<GreaterAphidColor, @Nullable Object> TEXTURES = Util.make(Maps.newEnumMap(GreaterAphidColor.class), (enumMap) -> {
        enumMap.put(GreaterAphidColor.GREATER_NATURAL, new Identifier(Mantori.MOD_ID, "textures/entity/greater_natural.png"));
    });

    public GreaterAphidRenderer(EntityRendererFactory.Context context) {
        super(context, new GreaterAphidModel(context.getPart(ModModelLayers.GREATER_APHID)), 1.1F);
        this.addFeature(new GreaterAphidMarkingRenderer(this));
    }

    public Identifier getTexture(GreaterAphid greaterAphid) {
        if (greaterAphid.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/greater_child.png");
        else return (Identifier)TEXTURES.get(greaterAphid.getColor());
    }
}
