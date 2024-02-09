package vice.satisfying_buttons.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vice.satisfying_buttons.SatisfyingButtons;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "satisfying_buttons", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {


}
