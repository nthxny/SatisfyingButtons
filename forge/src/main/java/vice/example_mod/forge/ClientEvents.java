package vice.example_mod.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vice.example_mod.ExampleMod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "example_mod", value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {


}
