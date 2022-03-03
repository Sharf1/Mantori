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
    private final ModelPart head;
    private final ModelPart right_front_leg;
    private final ModelPart left_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;
    private final ModelPart wing;

    public GreaterAphidModel(ModelPart root) {
        this.body = root.getChild("body");
        this.wing = root.getChild("wing");
        this.tail = root.getChild("tail");
        this.head = root.getChild("head");
        this.right_front_leg = root.getChild("right_front_leg");
        this.left_front_leg = root.getChild("left_front_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.right_hind_leg = root.getChild("right_hind_leg");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -5.75F, -15.75F, 14.0F, 12.0F, 16.0F, new Dilation(0.0F))
                .uv(0, 28).cuboid(-5.5F, -5.25F, 0.25F, 11.0F, 10.0F, 15.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -5.25F, 8.75F));

        ModelPartData wing = partdefinition.addChild("wing", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 29.25F, -8.75F));

        ModelPartData cube_r1 = wing.addChild("cube_r1", ModelPartBuilder.create().uv(84, 0).cuboid(-4.0F, 0.0F, -1.0F, 5.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -35.0F, -2.0F, 0.3491F, -0.6981F, 0.0524F));

        ModelPartData cube_r2 = wing.addChild("cube_r2", ModelPartBuilder.create().uv(28, 28).cuboid(-1.0F, 0.0F, -1.0F, 5.0F, 0.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -35.0F, -2.0F, 0.3491F, 0.6981F, -0.0524F));

        ModelPartData tail = partdefinition.addChild("tail", ModelPartBuilder.create().uv(24, 82).cuboid(-4.0F, -6.8479F, -2.2901F, 8.0F, 7.0F, 6.0F, new Dilation(0.0F))
                .uv(73, 0).cuboid(-4.0F, -6.8479F, 13.7099F, 8.0F, 7.0F, 2.0F, new Dilation(0.0F))
                .uv(50, 18).cuboid(-4.5F, -7.3479F, 3.7099F, 9.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.5F, 24.0F, -0.3491F, 0.0F, 0.0F));


        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(44, 0).cuboid(-5.0F, -14.5F, -16.5F, 10.0F, 7.0F, 9.0F, new Dilation(0.0F))
                .uv(78, 9).cuboid(-4.0F, -14.0F, -22.5F, 8.0F, 6.0F, 7.0F, new Dilation(0.0F))
                .uv(52, 79).cuboid(0.0F, -27.0F, -20.5F, 0.0F, 13.0F, 9.0F, new Dilation(0.0F))
                .uv(0, 9).cuboid(-4.0F, -28F, -20.5F, 8.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.5F, -3.5F));

        ModelPartData neck = head.addChild("neck", ModelPartBuilder.create().uv(37, 38).cuboid(-4.5F, -5F, -15F, 9.0F, 8.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.7418F, 0.0F, 0.0F));

        ModelPartData mandibulla = head.addChild("mandibulla", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -12.0F, -13.5F));

        ModelPartData cube_r3 = mandibulla.addChild("cube_r3", ModelPartBuilder.create().uv(0, 24).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -3.0F, 0.22F, 0.1278F, 0.0285F));

        ModelPartData cube_r4 = mandibulla.addChild("cube_r4", ModelPartBuilder.create().uv(0, 24).cuboid(0.0F, -2.0F, -4.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 2.0F, -3.0F, 0.22F, -0.1278F, -0.0285F));

        ModelPartData antennae = head.addChild("antennae", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.5F, 2.5F));

        ModelPartData cube_r5 = antennae.addChild("cube_r5", ModelPartBuilder.create().uv(70, 83).cuboid(0.0F, -19.0F, -2.5F, 0.0F, 19.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -30.5F, -16.0F, -0.6527F, -0.0531F, -0.0693F));

        ModelPartData cube_r6 = antennae.addChild("cube_r6", ModelPartBuilder.create().uv(70, 83).cuboid(0.0F, -19.0F, -2.5F, 0.0F, 19.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -30.5F, -16.0F, -0.6527F, 0.0531F, 0.0693F));

        ModelPartData left_front_leg = partdefinition.addChild("left_front_leg", ModelPartBuilder.create().uv(34, 61).cuboid(-3.5F, -7.0F, -4.5F, 7.0F, 12.0F, 9.0F, new Dilation(0.0F))
                .uv(66, 61).cuboid(-2.5F, 5.0F, -3.5F, 5.0F, 20.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, 19.0F, 3.5F, 5.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(6.5F, -1.0F, 0.5F));

        ModelPartData right_front_leg = partdefinition.addChild("right_front_leg", ModelPartBuilder.create().uv(34, 61).cuboid(-3.5F, -7.0F, -4.5F, 7.0F, 12.0F, 9.0F, new Dilation(0.0F))
                .uv(66, 61).cuboid(-2.5F, 5.0F, -3.5F, 5.0F, 20.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, 19.0F, 3.5F, 5.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, -1.0F, 0.5F));

        ModelPartData left_hind_leg = partdefinition.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 53).cuboid(-3.5F, -7.0F, -5.0F, 7.0F, 12.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 75).cuboid(-2.5F, 7.5F, 3.5F, 5.0F, 17.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -0.5F, 18.0F));

        ModelPartData cube_r7 = left_hind_leg.addChild("cube_r7", ModelPartBuilder.create().uv(80, 28).cuboid(-2.5F, 0.0F, -4.0F, 5.0F, 13.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.6981F, 0.0F, 0.0F));

        ModelPartData right_hind_leg = partdefinition.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 53).cuboid(-3.5F, -7.0F, -5.0F, 7.0F, 12.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 75).cuboid(-2.5F, 7.5F, 3.5F, 5.0F, 17.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -0.5F, 18.0F));

        ModelPartData cube_r8 = right_hind_leg.addChild("cube_r8", ModelPartBuilder.create().uv(80, 28).cuboid(-2.5F, 0.0F, -4.0F, 5.0F, 13.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.6981F, 0.0F, 0.0F));
        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    public void setAngles(T horseBaseEntity, float f, float g, float h, float i, float j) {
        float legYOffset = 0.0F;
        float legZOffset = 0.0F;

        this.body.pivotY = -6.0F;
        this.left_front_leg.pivotY = legYOffset;
        this.right_front_leg.pivotY = legYOffset;

        this.left_front_leg.pivotZ = legZOffset;
        this.right_front_leg.pivotZ = legZOffset;

        this.head.pivotY = -14.0F;
        this.head.pivotZ = -14.0F;

        this.tail.pivotY = -3F;
        this.tail.pivotZ = 24.0F;
        this.tail.pitch = -0.36F;
    }

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
        this.body.pitch = o * -0.2853982F + p * this.body.pitch;
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
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(body, tail, right_front_leg, right_hind_leg, left_front_leg, left_hind_leg, head);
    }
}