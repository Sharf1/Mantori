package net.mantori.client.render;

import net.mantori.client.model.GreaterAphidModel;
import net.mantori.client.model.LesserAphidModel;
import net.mantori.entity.BaseAphidEntity;
import net.mantori.entity.LesserAphid;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class BaseLesserAphidRenderer <T extends LesserAphid, M extends LesserAphidModel<T>> extends MobEntityRenderer<T, M> {
    private final float scale;

    public BaseLesserAphidRenderer(EntityRendererFactory.Context ctx, M model, float scale) {
        super(ctx, model, 0.75F);
        this.scale = scale;
    }

    protected void scale(T baseEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(this.scale, this.scale, this.scale);
        super.scale(baseEntity, matrixStack, f);
    }
}