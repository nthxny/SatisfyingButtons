package vice.satisfying_buttons;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.architectury.registry.registries.DeferredRegister;

#if PRE_CURRENT_MC_1_19_2
import com.mojang.blaze3d.vertex.PoseStack;
import dev.architectury.registry.registries.Registries;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Registry;
#elif POST_CURRENT_MC_1_20_1
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.math.Color;
import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.CommonColors;
#endif

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import vice.satisfying_buttons.accessors.IAbstractButtonAccessor;

import static net.minecraft.client.gui.components.AbstractWidget.WIDGETS_LOCATION;

public class SatisfyingButtons
{
	public static final String MOD_ID = "satisfying_buttons";
	public static vice.satisfying_buttons.ModConfig Config;

	#if PRE_CURRENT_MC_1_19_2
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(MOD_ID, Registry.SOUND_EVENT_REGISTRY);
	public static final RegistrySupplier<SoundEvent> BUTTON_HOVER = SOUND_EVENTS.register("button_hover", () -> new SoundEvent(new ResourceLocation(MOD_ID, "button_hover")));
	public static final RegistrySupplier<SoundEvent> BUTTON_HOVER_REVERSE = SOUND_EVENTS.register("button_hover_reverse", () -> new SoundEvent(new ResourceLocation(MOD_ID, "button_hover_reverse")));

	#elif POST_CURRENT_MC_1_20_1
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(MOD_ID, Registries.SOUND_EVENT);

	public static final RegistrySupplier<SoundEvent> BUTTON_HOVER = SOUND_EVENTS.register("button_hover", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "button_hover")));
	public static final RegistrySupplier<SoundEvent> BUTTON_HOVER_REVERSE = SOUND_EVENTS.register("button_hover_reverse", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "button_hover_reverse")));
	#endif




	public static void init() {
		SOUND_EVENTS.register();
		AutoConfig.register(vice.satisfying_buttons.ModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		Config = AutoConfig.getConfigHolder(vice.satisfying_buttons.ModConfig.class).getConfig();
	}


	#if PRE_CURRENT_MC_1_19_2
	public static void renderButtonOverlay(PoseStack graphics, AbstractWidget ths)
	#elif POST_CURRENT_MC_1_20_1
	public static void renderButtonOverlay(GuiGraphics graphics, AbstractButton ths)
	#endif
	{
		var accessor = (IAbstractButtonAccessor) ths;
		var millis = Util.getMillis();
		var hovertime = accessor.satisfyingButtons$getHoverTime();

		var ticks = millis - hovertime;
		if (ticks <= 0 || !ths.isHovered#if POST_CURRENT_MC_1_20_1()#endif)
			return;

		if (Config.client.FadeInVanillaWidgetTexture)
		{
			var alpha = Mth.clamp(1f / ((float) Config.client.FadeInVanillaWidgetTextureOnHoverTime / ticks), 0f, 1f);
			setColor(graphics, 1.0F, 1.0F, 1.0F, alpha);
			RenderSystem.enableBlend();
			RenderSystem.enableDepthTest();

			#if PRE_CURRENT_MC_1_19_2
			RenderSystem.setShader(GameRenderer::getPositionTexShader);
			RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);
			ths.blit(graphics, ths.x, ths.y, 0, 46 + 2 * 20, ths.getWidth() / 2, ths.getHeight());
			ths.blit(graphics, ths.x + ths.getWidth() / 2, ths.y, 200 - ths.getWidth() / 2, 46 + 2 * 20, ths.getWidth() / 2, ths.getHeight());
			#elif POST_CURRENT_MC_1_20_1
			graphics.blitNineSliced(WIDGETS_LOCATION, ths.getX(), ths.getY(), ths.getWidth(), ths.getHeight(), 20, 4, 200, 20, 0, 46 + 2 * 20);
			#endif


			setColor(graphics, 1.0F, 1.0F, 1.0F, 1.0F);
		}

		if (Config.client.DarkenButtonOnHover)
		{
			var overlayAlpha = Mth.clamp(1f / ((float) Config.client.DarkenButtonOnHoverTime / ticks), 0f, 1f);
			var color = Config.client.DarkenButtonColor;
			var lerped = FastColor.ARGB32.color(
					(int) Mth.lerp(overlayAlpha, 0, FastColor.ARGB32.alpha(color)),
					FastColor.ARGB32.red(color),
					FastColor.ARGB32.green(color),
					FastColor.ARGB32.blue(color));


			#if PRE_CURRENT_MC_1_19_2
			GuiComponent.fill(graphics, ths.x, ths.y, ths.x + ths.getWidth(), ths.y +ths.getHeight(), lerped);
			#elif POST_CURRENT_MC_1_20_1
			graphics.fill(ths.getX(), ths.getY(), ths.getX() + ths.getWidth(), ths.getY() +ths.getHeight(), lerped);
			#endif
		}
	}



	#if PRE_CURRENT_MC_1_19_2
	private static void setColor(PoseStack graphics, float v, float v1, float v2, float alpha) { RenderSystem.setShaderColor(v, v1, v2, alpha); }
	#elif POST_CURRENT_MC_1_20_1
	private static void setColor(GuiGraphics graphics, float v, float v1, float v2, float alpha) { graphics.setColor(v, v1, v2, alpha); }
	#endif



}
