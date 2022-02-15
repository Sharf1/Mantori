package net.mantori.client.model;

import com.google.common.collect.ImmutableList;
import net.mantori.Mantori;
import net.mantori.entity.BaseAphidEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GreaterAphidModel<T extends BaseAphidEntity> extends AnimalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;

    public GreaterAphidModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.right_front_leg = root.getChild("right_front_leg");
        this.left_front_leg = root.getChild("left_front_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.right_hind_leg = root.getChild("right_hind_leg");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, -35.0F, -7.0F, 16.0F, 13.0F, 14.0F, new Dilation(0.0F))
                .uv(0, 27).cuboid(7.0F, -34.0F, -6.0F, 15.0F, 11.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData wings = body.addChild("wings", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = wings.addChild("cube_r1", ModelPartBuilder.create().uv(46, 0).cuboid(1.7931F, 0.2045F, -6.2024F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -28.0F, 3.0F, -1.7453F, -0.7243F, -0.3491F));

        ModelPartData cube_r2 = wings.addChild("cube_r2", ModelPartBuilder.create().uv(46, 0).cuboid(1.7931F, 0.2045F, 6.2024F, 9.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -28.0F, -3.0F, 1.7453F, 0.7243F, -0.3491F));

        ModelPartData tail = partdefinition.addChild("tail", ModelPartBuilder.create().uv(38, 59).cuboid(-2.3941F, -6.5778F, -4.0F, 10.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(82, 32).cuboid(9.6059F, -6.0778F, -3.5F, 6.0F, 7.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 50).cuboid(3.6059F, -7.0778F, -4.5F, 10.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 21.5F, -1.5708F, -1.2217F, 1.5708F));

        ModelPartData neck = partdefinition.addChild("neck", ModelPartBuilder.create().uv(47, 43).cuboid(-19.1609F, -9.2291F, -3.5F, 14.0F, 9.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, -5.5F, -1.5708F, -0.7418F, 1.5708F));

        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(66, 67).cuboid(-8.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(83, 53).cuboid(-15.0F, -11.5F, -3.0F, 7.0F, 7.0F, 6.0F, new Dilation(0.0F))
                .uv(87, 19).cuboid(-14.0F, -24.5F, 0.0F, 9.0F, 13.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, -11.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData mandibula = head.addChild("mandibula", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 5.0F, 4.0F));

        ModelPartData mandibulla_r1 = mandibula.addChild("mandibulla_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5627F, -9.1877F, 0.2631F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, -3.5F, -1.0F, 0.0F, 0.1745F, -0.2182F));

        ModelPartData mandibulla_r2 = mandibula.addChild("mandibulla_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.5627F, -9.1877F, -0.2631F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, -3.5F, -7.0F, 0.0F, -0.1745F, -0.2182F));

        ModelPartData antennae = head.addChild("antennae", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 5.0F, -4.0F));

        ModelPartData antennae_r1 = antennae.addChild("antennae_r1", ModelPartBuilder.create().uv(0, 87).cuboid(-6.787F, -25.0096F, -0.8104F, 7.0F, 19.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -7.0F, 8.0F, -0.1309F, 0.0F, 0.48F));

        ModelPartData antennae_r2 = antennae.addChild("antennae_r2", ModelPartBuilder.create().uv(0, 87).cuboid(-6.5652F, -25.1241F, 0.8255F, 7.0F, 19.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, -7.0F, 0.0F, 0.1309F, 0.0F, 0.48F));

        ModelPartData right_front_leg = partdefinition.addChild("right_front_leg", ModelPartBuilder.create().uv(60, 0).cuboid(-4.5F, -7.0F, -3.5F, 9.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 68).cuboid(-4.0F, 5.0F, -3.0F, 8.0F, 13.0F, 6.0F, new Dilation(0.0F))
                .uv(78, 83).cuboid(-3.5F, 18.0F, -2.5F, 7.0F, 7.0F, 5.0F, new Dilation(0.0F))
                .uv(92, 0).cuboid(3.5F, 20.0F, -2.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-6.5F, -1.0F, -2.5F, 0.0F, -1.5708F, 0.0F));

        ModelPartData left_front_leg = partdefinition.addChild("left_front_leg", ModelPartBuilder.create().uv(60, 0).cuboid(-4.5F, -7.0F, -3.5F, 9.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 68).cuboid(-4.0F, 5.0F, -3.0F, 8.0F, 13.0F, 6.0F, new Dilation(0.0F))
                .uv(78, 83).cuboid(-3.5F, 18.0F, -2.5F, 7.0F, 7.0F, 5.0F, new Dilation(0.0F))
                .uv(92, 0).cuboid(3.5F, 20.0F, -2.5F, 3.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(6.5F, -1.0F, -2.5F, 0.0F, -1.5708F, 0.0F));

        ModelPartData left_hind_leg = partdefinition.addChild("left_hind_leg", ModelPartBuilder.create().uv(53, 20).cuboid(-5.5F, -7.0F, -3.5F, 10.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(28, 75).cuboid(3.0F, 7.0F, -2.5F, 7.0F, 17.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(4.5F, 0.0F, 16.5F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r3 = left_hind_leg.addChild("cube_r3", ModelPartBuilder.create().uv(52, 83).cuboid(0.4995F, -5.3623F, -2.5F, 8.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 7.0F, 0.0F, 0.0F, 0.0F, -0.6981F));

        ModelPartData right_hind_leg = partdefinition.addChild("right_hind_leg", ModelPartBuilder.create().uv(53, 20).cuboid(-5.5F, -7.0F, -3.5F, 10.0F, 12.0F, 7.0F, new Dilation(0.0F))
                .uv(28, 75).cuboid(3.0F, 7.0F, -2.5F, 7.0F, 17.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, 0.0F, 16.5F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r4 = right_hind_leg.addChild("cube_r4", ModelPartBuilder.create().uv(52, 83).cuboid(0.4995F, -5.3623F, -2.5F, 8.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 7.0F, 0.0F, 0.0F, 0.0F, -0.6981F));
        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

    @Override
    public void animateModel(T entity, float f, float g, float h) {
        super.animateModel(entity, f, g, h);
        float i = MathHelper.lerpAngle(entity.prevBodyYaw, entity.bodyYaw, h);
        float j = MathHelper.lerpAngle(entity.prevHeadYaw, entity.headYaw, h);
        float k = MathHelper.lerp(h, entity.prevPitch, entity.getPitch());
        float l = j - i;
        float m = k * 0.017453292F;
        if (l > 20.0F) {
            l = 20.0F;
        }

        if (l < -20.0F) {
            l = -20.0F;
        }

        if (g > 0.2F) {
            m += MathHelper.cos(f * 0.4F) * 0.15F * g;
        }

        float n = entity.getEatingGrassAnimationProgress(h);
        float o = entity.getAngryAnimationProgress(h);
        float p = 1.0F - o;
        float q = entity.getEatingAnimationProgress(h);
        boolean bl = entity.tailWagTicks != 0;
        float r = (float)entity.age + h;
        this.head.pivotY = 4.0F;
        this.head.pivotZ = -12.0F;
        this.body.pitch = 0.0F;
        this.head.pitch = 0.5235988F + m;
        this.head.yaw = l * 0.017453292F;
        float s = entity.isTouchingWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + 3.1415927F);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (0.5235988F + m + q * MathHelper.sin(r) * 0.05F);
        this.head.pitch = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
        this.head.yaw = o * l * 0.017453292F + (1.0F - Math.max(o, n)) * this.head.yaw;
        this.head.pivotY = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.head.pivotY;
        this.head.pivotZ = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.head.pivotZ;
        this.body.pitch = o * -0.7853982F + p * this.body.pitch;
        float w = 0.2617994F * o;
        float x = MathHelper.cos(r * 0.6F + 3.1415927F);
        this.left_front_leg.pivotY = 2.0F * o + 14.0F * p;
        this.left_front_leg.pivotZ = -6.0F * o - 10.0F * p;
        this.right_front_leg.pivotY = this.left_front_leg.pivotY;
        this.right_front_leg.pivotZ = this.left_front_leg.pivotZ;
        float y = (-1.0471976F + x) * o + u * p;
        float z = (-1.0471976F - x) * o - u * p;
        this.left_hind_leg.pitch = w - t * 0.5F * g * p;
        this.right_hind_leg.pitch = w + t * 0.5F * g * p;
        this.left_front_leg.pitch = y;
        this.right_front_leg.pitch = z;
        this.tail.pitch = 0.5235988F + g * 0.75F;
        this.tail.pivotY = -5.0F + g;
        this.tail.pivotZ = 2.0F + g * 2.0F;
        if (bl) {
            this.tail.yaw = MathHelper.cos(r * 0.7F);
        } else {
            this.tail.yaw = 0.0F;
        }
        boolean bl2 = entity.isBaby();
        this.right_hind_leg.visible = !bl2;
        this.left_hind_leg.visible = !bl2;
        this.right_front_leg.visible = !bl2;
        this.left_front_leg.visible = !bl2;
        this.body.pivotY = bl2 ? 10.8F : 0.0F;
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(head, neck);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(body, tail, right_front_leg, right_hind_leg, left_front_leg, left_hind_leg);
    }
}