package net.mantori.client.model;

import net.mantori.Mantori;
import net.mantori.client.render.GreaterAphidRenderer;
import net.mantori.entity.custom.GreaterAphidEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GreaterAphidModel extends  AnimatedGeoModel<GreaterAphidEntity>{


    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(GreaterAphidEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        IBone saddle = this.getAnimationProcessor().getBone("saddle");
        saddle.setHidden(!entity.isSaddled());


        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }

    @Override
    public Identifier getModelLocation(GreaterAphidEntity entity) {
        return new Identifier(Mantori.MOD_ID, "geo/greater.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GreaterAphidEntity entity) {
        if (entity.isBaby()) return new Identifier(Mantori.MOD_ID, "textures/entity/greater_aphid/greater_child.png");
        else return GreaterAphidRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public Identifier getAnimationFileLocation(GreaterAphidEntity animatable) {
        return new Identifier(Mantori.MOD_ID, "animations/greater.animation.json");
    }
}
