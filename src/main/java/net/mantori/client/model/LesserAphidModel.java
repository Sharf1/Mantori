package net.mantori.client.model;

import com.google.common.collect.ImmutableList;
import net.mantori.entity.BaseAphidEntity;
import net.mantori.entity.LesserAphid;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;

public class LesserAphidModel<T extends LesserAphid> extends AnimalModel<T> {
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart left_wing;
    private final ModelPart right_wing;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart left_front_leg;
    private final ModelPart right_front_leg;
    private final ModelPart left_hind_leg;
    private final ModelPart right_hind_leg;

    public LesserAphidModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.left_wing = root.getChild("left_wing");
        this.right_wing = root.getChild("right_wing");
        this.neck = root.getChild("neck");
        this.head = root.getChild("head");
        this.left_front_leg = root.getChild("left_front_leg");
        this.right_front_leg = root.getChild("right_front_leg");
        this.left_hind_leg = root.getChild("left_hind_leg");
        this.right_hind_leg = root.getChild("right_hind_leg");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(42, 0).cuboid(-4.0F, -20.0F, -4.0F, 8.0F, 6.0F, 9.0F, new Dilation(0.0F))
                .uv(42, 25).cuboid(-3.5F, -19.75F, 5.0F, 7.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData tail = partdefinition.addChild("tail", ModelPartBuilder.create().uv(20, 50).cuboid(-2.0F, 0.0F, 8.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(42, 39).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 4.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 25).cuboid(-2.5F, -0.5F, 3.0F, 5.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.5F, 14.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData left_wing = partdefinition.addChild("left_wing", ModelPartBuilder.create().uv(0, 25).cuboid(0.0F, -1.0F, 0.0F, 9.0F, 1.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -4.0F));

        ModelPartData right_wing = partdefinition.addChild("right_wing", ModelPartBuilder.create().uv(0, 0).cuboid(-9.0F, -1.0F, 0.0F, 9.0F, 1.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, -4.0F));

        ModelPartData neck = partdefinition.addChild("neck", ModelPartBuilder.create().uv(0, 50).cuboid(-2.5F, -2.25F, -10.0F, 5.0F, 5.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.5F, -3.0F, -0.3491F, 0.0F, 0.0F));

        ModelPartData head = partdefinition.addChild("head", ModelPartBuilder.create().uv(30, 50).cuboid(-3.0F, -23.25F, -18.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(42, 15).cuboid(-2.5F, -22.75F, -22.0F, 5.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData mandibulla = head.addChild("mandibulla", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = mandibulla.addChild("cube_r1", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, -1.0F, -4.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -20.25F, -18.5F, 0.1745F, 0.1745F, 0.0262F));

        ModelPartData cube_r2 = mandibulla.addChild("cube_r2", ModelPartBuilder.create().uv(8, 2).cuboid(0.0F, -1.0F, -4.0F, 0.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -20.25F, -18.5F, 0.1745F, -0.1745F, 0.0262F));

        ModelPartData antennae = head.addChild("antennae", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = antennae.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -18.0F, -2.0F, 0.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -20.25F, -16.0F, -0.6545F, 0.0F, -0.1309F));

        ModelPartData cube_r4 = antennae.addChild("cube_r4", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -18.0F, -2.0F, 0.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -20.25F, -16.0F, -0.6545F, 0.0F, 0.1309F));

        ModelPartData horn = head.addChild("horn", ModelPartBuilder.create().uv(39, 58).cuboid(0.0F, -34.25F, -19.5F, 0.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(8, 0).cuboid(0.0F, -34.25F, -19.5F, 0.0F, 6.0F, 8.0F, new Dilation(0.0F))
                .uv(8, 0).cuboid(-4.0F, -35.25F, -19.5F, 8.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_front_leg = partdefinition.addChild("left_front_leg", ModelPartBuilder.create().uv(8, 14).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(27, 62).cuboid(-1.5F, 5.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 8.0F, -1.0F));

        ModelPartData right_front_leg = partdefinition.addChild("right_front_leg", ModelPartBuilder.create().uv(8, 14).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(27, 62).cuboid(-1.5F, 5.0F, -1.5F, 3.0F, 11.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 8.0F, -1.0F));

        ModelPartData left_hind_leg = partdefinition.addChild("left_hind_leg", ModelPartBuilder.create().uv(0, 35).cuboid(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 5.0F, new Dilation(0.0F))
                .uv(47, 62).cuboid(-1.5F, 10.0F, 2.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(2.5F, 6.0F, 9.5F));

        ModelPartData cube_r5 = left_hind_leg.addChild("cube_r5", ModelPartBuilder.create().uv(56, 50).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        ModelPartData right_hind_leg = partdefinition.addChild("right_hind_leg", ModelPartBuilder.create().uv(0, 35).cuboid(-2.0F, 0.0F, -2.5F, 4.0F, 6.0F, 5.0F, new Dilation(0.0F))
                .uv(47, 62).cuboid(-1.5F, 10.0F, 2.5F, 3.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, 6.0F, 9.5F));

        ModelPartData cube_r6 = right_hind_leg.addChild("cube_r6", ModelPartBuilder.create().uv(56, 50).cuboid(-1.5F, 0.0F, -2.0F, 3.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 4.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 128, 128);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(head, neck);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(body, tail, right_front_leg, right_hind_leg, left_front_leg, left_hind_leg, left_wing, right_wing);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        this.neck.pitch = headPitch * 0.017453292F;
        this.neck.yaw = headYaw * 0.017453292F;
        this.right_hind_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.left_hind_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.right_front_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
        this.left_front_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }

    @Override
    public void animateModel(T entity, float f, float g, float h) {

    }
}