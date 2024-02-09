package vice.satisfying_buttons.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import vice.satisfying_buttons.SatisfyingButtons;
import vice.satisfying_buttons.SatisfyingButtonsClient;

public class FabricClientInitializer implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        SatisfyingButtonsClient.init();
    }
}