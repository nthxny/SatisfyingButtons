package vice.example_mod.fabric;

import vice.example_mod.ExampleMod;
import net.fabricmc.api.ModInitializer;

public class FabricInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        ExampleMod.init();
    }
}