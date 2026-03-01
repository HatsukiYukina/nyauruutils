package org.nyauru.nyauruutils.neoforge;

import org.nyauru.nyauruutils.Nyauruutils;
import net.neoforged.fml.common.Mod;

@Mod(Nyauruutils.MOD_ID)
public final class NyauruutilsNeoForge {
    public NyauruutilsNeoForge() {
        // Run our common setup.
        Nyauruutils.init();
    }
}
