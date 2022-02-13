package net.mantori.sounds;

import net.mantori.Mantori;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static SoundEvent WING_FLAP = registerSoundEvent("wing_flap");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Mantori.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    };

    public static void registerSounds() {
        System.out.println("Registering ModSounds for" + Mantori.MOD_ID);
    }
}
