package net.mantori.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.mantori.client.model.GreaterAphidModel;
import net.mantori.client.render.GreaterAphidRenderer;
import net.mantori.entity.ModEntityTypes;

@Environment(EnvType.CLIENT)
public class MantoriClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GREATER_APHID, GreaterAphidModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityTypes.GREATER_APHID_ENTITY_TYPE, GreaterAphidRenderer::new);
    }
}
