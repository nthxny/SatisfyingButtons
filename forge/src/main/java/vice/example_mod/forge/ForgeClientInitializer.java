package vice.example_mod.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vice.example_mod.ExampleModClient;

@Mod.EventBusSubscriber(modid = "example_mod", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeClientInitializer {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ExampleModClient.init();
    }
}