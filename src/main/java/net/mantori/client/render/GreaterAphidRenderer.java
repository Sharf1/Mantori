package net.mantori.client.render;

import com.google.common.collect.Maps;
import net.mantori.Mantori;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.variants.GreaterAphidVariant;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class GreaterAphidRenderer extends GeoEntityRenderer<GreaterAphidEntity> {
    public static final Map<GreaterAphidVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GreaterAphidVariant.class), (map) -> {
                map.put(GreaterAphidVariant.NATURAL,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_natural.png"));
                map.put(GreaterAphidVariant.BLUE,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_blue.png"));
                map.put(GreaterAphidVariant.GREEN,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_green.png"));
                map.put(GreaterAphidVariant.ORANGE,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_orange.png"));
                map.put(GreaterAphidVariant.PEACH,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_peach.png"));
                map.put(GreaterAphidVariant.YELLOW,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_yellow.png"));
                map.put(GreaterAphidVariant.RED,
                        new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_red.png"));
            });

    public GreaterAphidRenderer(EntityRendererFactory.Context ctx) {
        super(ctx,new GreaterAphidModel());
    }

    @Override
    public Identifier getTextureLocation(GreaterAphidEntity entity) {
        if (entity.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_child.png");
        else return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderLayer getRenderType(GreaterAphidEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4f,0.4f,0.4f);
        } else {
            stack.scale(1f,1f,1f);
        }


        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
