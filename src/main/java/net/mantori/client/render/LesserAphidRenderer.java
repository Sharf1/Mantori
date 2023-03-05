package net.mantori.client.render;

import com.google.common.collect.Maps;
import net.mantori.Mantori;
import net.mantori.client.model.LesserAphidModel;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.custom.LesserAphidEntity;
import net.mantori.entity.variants.LesserAphidVariant;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

import java.util.Map;

public class LesserAphidRenderer extends GeoEntityRenderer<LesserAphidEntity> {
    public static final Map<LesserAphidVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(LesserAphidVariant.class), (map) -> {
                map.put(LesserAphidVariant.NATURAL,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_natural.png"));
                map.put(LesserAphidVariant.BLUE,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_bluegreen.png"));
                map.put(LesserAphidVariant.GREEN,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_green.png"));
                map.put(LesserAphidVariant.ORANGE,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_orange.png"));
                map.put(LesserAphidVariant.BLACK,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_black.png"));
                map.put(LesserAphidVariant.BROWN,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_brown.png"));
                map.put(LesserAphidVariant.CYAN,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_cyan.png"));
                map.put(LesserAphidVariant.GRAY,
                        new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_gray.png"));
            });

    public LesserAphidRenderer(EntityRendererFactory.Context ctx) {
        super(ctx,new LesserAphidModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureLocation(LesserAphidEntity entity) {
        if (entity.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_child.png");
        else return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public RenderLayer getRenderType(LesserAphidEntity animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
    @Override
    public void preRender(MatrixStack stack, LesserAphidEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer,
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
    public void render(LesserAphidEntity entity, float entityYaw, float partialTicks, MatrixStack stack, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);

        if (entity instanceof MobEntity) {
            Entity leashHolder = ((MobEntity) entity).getHoldingEntity();
            if (leashHolder != null) {
                this.renderLeash(entity, partialTicks, stack, bufferIn, leashHolder);
            }
        }
    }
}
