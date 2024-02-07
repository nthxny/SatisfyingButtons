package vice.example_mod.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import vice.example_mod.ExampleMod;
import vice.example_mod.ExampleModClient;

public class FabricClientInitializer implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ExampleModClient.init();
    }
}