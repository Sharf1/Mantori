package net.mantori.client.render;

import com.google.common.collect.Maps;
import net.mantori.Mantori;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.variants.GreaterAphidVariant;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

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
        super(ctx, new GreaterAphidModel());
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }

    @Override
    public Identifier getTexture(GreaterAphidEntity entity) {
        if (entity.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_child.png");
        else return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void preRender(MatrixStack stack, GreaterAphidEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer,
                          float partialTick, int packedLight, int packedOverlay, float red, float green, float blue,
                          float alpha) {
        super.preRender(stack, animatable, model, bufferSource, buffer, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
        if(animatable.isBaby()) {
            stack.scale(0.4f,0.4f,0.4f);
        } else {
            stack.scale(1f,1f,1f);
        }

    }


    @Override
    public void render(GreaterAphidEntity entity, float entityYaw, float partialTicks, MatrixStack stack, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);

        if (entity instanceof MobEntity) {
            Entity leashHolder = (entity).getHoldingEntity();
            if (leashHolder != null) {
                this.renderLeash(entity, partialTicks, stack, bufferIn, leashHolder);
            }
        }
    }
}
