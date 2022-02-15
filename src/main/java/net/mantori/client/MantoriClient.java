package net.mantori.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.mantori.Mantori;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.client.render.GreaterAphidRenderer;
import net.mantori.entity.GreaterAphid;
import net.mantori.entity.ModEntityTypes;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MantoriClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(MontoriModelLayers.GREATER_APHID, GreaterAphidModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.GREATER_APHID_ENTITY_TYPE, GreaterAphidRenderer::new);
    }
}
