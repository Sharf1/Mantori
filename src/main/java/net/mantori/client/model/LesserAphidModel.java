package net.mantori.client.model;

import net.mantori.Mantori;
import net.mantori.client.render.LesserAphidRenderer;
import net.mantori.entity.custom.LesserAphidEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class LesserAphidModel extends  AnimatedGeoModel<LesserAphidEntity>{

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(LesserAphidEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
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
