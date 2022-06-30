package net.mantori.client.render;

import com.google.common.collect.Maps;
import net.mantori.Mantori;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.mantori.entity.variants.GreaterAphidVariant;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

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
        this.addLayer(new LayerGlowingAreasGeo<>(this, getGeoModelProvider()::getTextureLocation, getGeoModelProvider()::getModelLocation, RenderLayer::getEyes));
    }

    @Override
    public Identifier getTexture(GreaterAphidEntity entity) {
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


    private <E extends Entity> void renderLeash(GreaterAphidEntity entity, float partialTicks, MatrixStack poseStack,
                                                VertexConsumerProvider buffer, E leashHolder) {
        int u;
        poseStack.push();
        Vec3d vec3d = leashHolder.getLeashPos(partialTicks);
        double d = (double)(MathHelper.lerp(partialTicks, entity.bodyYaw, entity.prevBodyYaw) * ((float)Math.PI / 180)) + 1.5707963267948966;
        Vec3d vec3d2 = (entity).getLeashOffset();
        double e = Math.cos(d) * vec3d2.z + Math.sin(d) * vec3d2.x;
        double f = Math.sin(d) * vec3d2.z - Math.cos(d) * vec3d2.x;
        double g = MathHelper.lerp(partialTicks, entity.prevX, entity.getX()) + e;
        double h = MathHelper.lerp(partialTicks, entity.prevY, entity.getY()) + vec3d2.y;
        double i = MathHelper.lerp(partialTicks, entity.prevZ, entity.getZ()) + f;
        poseStack.translate(e, vec3d2.y, f);
        float j = (float)(vec3d.x - g);
        float k = (float)(vec3d.y - h);
        float l = (float)(vec3d.z - i);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderLayer.getLeash());
        Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
        float n = MathHelper.fastInverseSqrt(j * j + l * l) * 0.025f / 2.0f;
        float o = l * n;
        float p = j * n;
        BlockPos blockPos = new BlockPos(entity.getCameraPosVec(partialTicks));
        BlockPos blockPos2 = new BlockPos(leashHolder.getCameraPosVec(partialTicks));
        int q = this.getBlockLight(entity, blockPos);
        int r = leashHolder.isOnFire()?15: leashHolder.world.getLightLevel(LightType.BLOCK, blockPos2);
        int s = entity.world.getLightLevel(LightType.SKY, blockPos);
        int t = entity.world.getLightLevel(LightType.SKY, blockPos2);
        for (u = 0; u <= 24; ++u) {
            GreaterAphidRenderer.renderLeashPiece(vertexConsumer, matrix4f, j, k, l, q, r, s, t, 0.025f, 0.025f, o, p, u, false);
        }
        for (u = 24; u >= 0; --u) {
            GreaterAphidRenderer.renderLeashPiece(vertexConsumer, matrix4f, j, k, l, q, r, s, t, 0.025f, 0.0f, o, p, u, true);
        }
        poseStack.pop();
    }
    private static void renderLeashPiece(VertexConsumer vertexConsumer, Matrix4f positionMatrix, float f, float g, float h, int leashedEntityBlockLight, int holdingEntityBlockLight, int leashedEntitySkyLight, int holdingEntitySkyLight, float i, float j, float k, float l, int pieceIndex, boolean isLeashKnot) {
        float m = (float)pieceIndex / 24.0f;
        int n = (int)MathHelper.lerp(m, leashedEntityBlockLight, holdingEntityBlockLight);
        int o = (int)MathHelper.lerp(m, leashedEntitySkyLight, holdingEntitySkyLight);
        int p = LightmapTextureManager.pack(n, o);
        float q = pieceIndex % 2 == (isLeashKnot ? 1 : 0) ? 0.7f : 1.0f;
        float r = 0.5f * q;
        float s = 0.4f * q;
        float t = 0.3f * q;
        float u = f * m;
        float v = g > 0.0f ? g * m * m : g - g * (1.0f - m) * (1.0f - m);
        float w = h * m;
        vertexConsumer.vertex(positionMatrix, u - k, v + j, w + l).color(r, s, t, 1.0f).light(p).next();
        vertexConsumer.vertex(positionMatrix, u + k, v + i - j, w - l).color(r, s, t, 1.0f).light(p).next();
    }
}
