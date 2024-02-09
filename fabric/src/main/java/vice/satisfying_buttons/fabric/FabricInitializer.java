package vice.satisfying_buttons.fabric;

import vice.satisfying_buttons.SatisfyingButtons;
import net.fabricmc.api.ModInitializer;

public class FabricInitializer implements ModInitializer {
    @Override
    public void onInitialize() {
        SatisfyingButtons.init();
    }
}