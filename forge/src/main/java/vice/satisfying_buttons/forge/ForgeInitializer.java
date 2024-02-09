package vice.satisfying_buttons.forge;

import dev.architectury.platform.forge.EventBuses;
import vice.satisfying_buttons.SatisfyingButtons;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SatisfyingButtons.MOD_ID)
public class ForgeInitializer
{
    public ForgeInitializer() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(SatisfyingButtons.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        SatisfyingButtons.init();
    }
}