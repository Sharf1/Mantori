package net.mantori.client.render.layer;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mantori.Mantori;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.entity.AphidMarking;
import net.mantori.entity.GreaterAphid;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class GreaterAphidMarkingRenderer extends FeatureRenderer<GreaterAphid, GreaterAphidModel<GreaterAphid>> {
    private static final Map TEXTURES = Util.make(Maps.newEnumMap(AphidMarking.class), (textures) -> {
        textures.put(AphidMarking.BASIC, new Identifier(Mantori.MOD_ID, "textures/entity/greater_overlay.png"));
    });

    public GreaterAphidMarkingRenderer(FeatureRendererContext<GreaterAphid, GreaterAphidModel<GreaterAphid>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, GreaterAphid horseEntity, float f, float g, float h, float j, float k, float l) {
        Identifier identifier = (Identifier)TEXTURES.get(horseEntity.getMarking());
        if (identifier != null && !horseEntity.isInvisible()) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(identifier));
            this.getContextModel().render(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(horseEntity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
