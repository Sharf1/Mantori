package net.mantori.client;

import com.google.common.collect.Sets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mantori.Mantori;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

import java.util.Set;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class ModModelLayers {
    private static final String MAIN = "main";
    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();

    public static final EntityModelLayer GREATER_APHID = registerMain("greater_aphid");
    public static final EntityModelLayer LESSER_APHID = registerMain("lesser_aphid");

    public ModModelLayers() {
    }

    private static EntityModelLayer registerMain(String id) {
        return register(id, MAIN);
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(new Identifier(Mantori.MOD_ID, id), layer);
    }

    private static EntityModelLayer createInnerArmor(String id) {
        return register(id, "inner_armor");
    }

    private static EntityModelLayer createOuterArmor(String id) {
        return register(id, "outer_armor");
    }

    public static EntityModelLayer createBoat(BoatEntity.Type type) {
        return create("boat/" + type.getName(), "main");
    }

    public static EntityModelLayer createSign(SignType type) {
        return create("sign/" + type.getName(), "main");
    }

    public static Stream<EntityModelLayer> getLayers() {
        return LAYERS.stream();
    }
}



