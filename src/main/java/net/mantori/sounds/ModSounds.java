package net.mantori.sounds;

import net.mantori.Mantori;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent WING_FLAP = registerSoundEvent("aphid.wing_flap");
    public static SoundEvent AMBIENT = registerSoundEvent("aphid.ambient");
    public static SoundEvent LESSER_AMBIENT = registerSoundEvent("aphid.lesser.ambient");
    public static SoundEvent ANGRY = registerSoundEvent("aphid.angry");
    public static SoundEvent HONK = registerSoundEvent("goose.honk");
    public static SoundEvent BREATHE = registerSoundEvent("aphid.breathe");
    public static SoundEvent DEATH = registerSoundEvent("aphid.death");
    public static SoundEvent EAT = registerSoundEvent("aphid.eat");
    public static SoundEvent GALLOP = registerSoundEvent("aphid.gallop");
    public static SoundEvent HURT = registerSoundEvent("aphid.hurt");
    public static SoundEvent JUMP = registerSoundEvent("aphid.jump");
    public static SoundEvent LAND = registerSoundEvent("aphid.land");
    public static SoundEvent FOOTSTEPS = registerSoundEvent("aphid.footstep");
    public static SoundEvent FOOTSTEPS_WOOD = registerSoundEvent("aphid.footstep.wood");

    public static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Mantori.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    };

    public static void registerSounds() {
        System.out.println("Registering ModSounds for " + Mantori.MOD_ID);
    }
}
