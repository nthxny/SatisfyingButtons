package vice.satisfying_buttons.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vice.satisfying_buttons.SatisfyingButtonsClient;

@Mod.EventBusSubscriber(modid = "satisfying_buttons", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeClientInitializer {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        SatisfyingButtonsClient.init();
    }
}