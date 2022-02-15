package net.mantori.client.render;

import net.mantori.client.model.GreaterAphidModel;
import net.mantori.entity.BaseAphidEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class BaseAphidRenderer <T extends BaseAphidEntity, M extends GreaterAphidModel<T>> extends MobEntityRenderer<T, M> {

    private final float scale;

    public BaseAphidRenderer(EntityRendererFactory.Context ctx, M model, float scale) {
        super(ctx, model, 0.75F);
        this.scale = scale;
    }

    protected void scale(T horseBaseEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(this.scale, this.scale, this.scale);
        super.scale(horseBaseEntity, matrixStack, f);
    }
}
