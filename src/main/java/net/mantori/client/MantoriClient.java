package net.mantori.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.mantori.block.ModBlocks;
import net.mantori.client.render.GreaterAphidRenderer;
import net.mantori.client.render.LesserAphidRenderer;
import net.mantori.entity.ModEntities;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MantoriClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BEETLEBERRY_BUSH, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.LESSER_APHID, LesserAphidRenderer::new);
        EntityRendererRegistry.register(ModEntities.GREATER_APHID, GreaterAphidRenderer::new);
        }
}
