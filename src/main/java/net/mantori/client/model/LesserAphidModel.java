package net.mantori.client.model;

import net.mantori.Mantori;
import net.mantori.client.render.LesserAphidRenderer;
import net.mantori.entity.custom.LesserAphidEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class LesserAphidModel extends DefaultedEntityGeoModel<LesserAphidEntity> {

    public LesserAphidModel() {
        super(new Identifier(Mantori.MOD_ID, "entity/lesser_aphid"), true);
    }


    @Override
    public Identifier getModelResource(LesserAphidEntity entity) {
        return new Identifier(Mantori.MOD_ID, "geo/lesser.geo.json");
    }

    @Override
    public Identifier getTextureResource(LesserAphidEntity entity) {
        if (entity.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/lesser_aphid/lesser_child.png");
        else return LesserAphidRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public Identifier getAnimationResource(LesserAphidEntity animatable) {
        return new Identifier(Mantori.MOD_ID, "animations/lesser.animation.json");
    }
}
