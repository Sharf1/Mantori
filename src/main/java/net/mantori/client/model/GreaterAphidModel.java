package net.mantori.client.model;

import net.mantori.Mantori;
import net.mantori.client.render.GreaterAphidRenderer;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GreaterAphidModel extends DefaultedEntityGeoModel<GreaterAphidEntity>{

    public GreaterAphidModel() {
        super(new Identifier(Mantori.MOD_ID, "entity/greater_aphid"), true);
    }

    @Override
    public void setCustomAnimations(GreaterAphidEntity entity, long uniqueID, AnimationState animationState) {
        super.setCustomAnimations(entity, uniqueID, animationState);

        CoreGeoBone saddle = getAnimationProcessor().getBone("saddle");
        saddle.setHidden(!entity.isSaddled());
    }

    @Override
    public Identifier getModelResource(GreaterAphidEntity entity) {
        return new Identifier(Mantori.MOD_ID, "geo/greater.geo.json");
    }

    @Override
    public Identifier getTextureResource(GreaterAphidEntity entity) {
        if (entity.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_child.png");
        else return GreaterAphidRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public Identifier getAnimationResource(GreaterAphidEntity animatable) {
        return new Identifier(Mantori.MOD_ID, "animations/greater.animation.json");
    }
}
