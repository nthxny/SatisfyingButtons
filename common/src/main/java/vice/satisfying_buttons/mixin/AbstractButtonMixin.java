package vice.satisfying_buttons.mixin;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vice.satisfying_buttons.SatisfyingButtons;
import vice.satisfying_buttons.accessors.IAbstractButtonAccessor;

#if PRE_CURRENT_MC_1_19_2
import com.mojang.blaze3d.vertex.PoseStack;
#elif POST_CURRENT_MC_1_20_1
import net.minecraft.util.CommonColors;
import net.minecraft.client.gui.GuiGraphics;
#endif


#if PRE_CURRENT_MC_1_19_2
@Mixin(AbstractWidget.class)

#elif POST_CURRENT_MC_1_20_1
@Mixin(AbstractButton.class)
#endif

public class AbstractButtonMixin implements IAbstractButtonAccessor
{
    @Unique
    private boolean satisfying_buttons$wasHovered;

    @Unique
    private long satisfying_buttons$hoverOrFocusedStartTime;

    @Override
    public long satisfyingButtons$getHoverTime() { return satisfying_buttons$hoverOrFocusedStartTime; }

    #if PRE_CURRENT_MC_1_19_2
    @Inject(at = @At("RETURN"), method = "renderButton")
    public void render(PoseStack graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci)

    #elif POST_CURRENT_MC_1_20_1
    @Inject(at = @At("RETURN"), method = "renderWidget")
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci)
    #endif
    {
        #if PRE_CURRENT_MC_1_19_2
        if (!(((Object) this) instanceof AbstractButton))
            return;

        var ths = (AbstractButton) (Object) this;
        var isHovered = ths.isHovered;
        #elif POST_CURRENT_MC_1_20_1
        var ths = (AbstractButton) (Object) this;
        var isHovered = ths.isHovered();
        #endif

        if (!ths.active)
            return;

        if (isHovered && !satisfying_buttons$wasHovered)
        {
            if (SatisfyingButtons.Config.client.SoundEnabled)
            {
                // play hover sound
                var handler = Minecraft.getInstance().getSoundManager();
                handler.play(SimpleSoundInstance.forUI(SatisfyingButtons.BUTTON_HOVER.get(), SatisfyingButtons.Config.client.ButtonSoundPitch, SatisfyingButtons.Config.client.ButtonSoundVolume));
            }

            satisfying_buttons$hoverOrFocusedStartTime = Util.getMillis();
        }

        if (!isHovered && satisfying_buttons$wasHovered)
        {
            if (SatisfyingButtons.Config.client.SoundEnabled && SatisfyingButtons.Config.client.ButtonUnhoverSoundEnabled)
            {
                // play unhover sound
                var handler = Minecraft.getInstance().getSoundManager();
                handler.play(SimpleSoundInstance.forUI(SatisfyingButtons.BUTTON_HOVER_REVERSE.get(), SatisfyingButtons.Config.client.ButtonSoundPitch, SatisfyingButtons.Config.client.ButtonSoundVolume));
            }

            satisfying_buttons$hoverOrFocusedStartTime = 0;
        }

        if (SatisfyingButtons.Config.client.AnimationEnabled)
            SatisfyingButtons.renderButtonOverlay(graphics, ths);

        satisfying_buttons$wasHovered = isHovered;
    }

    #if PRE_CURRENT_MC_1_19_2
    @Inject(at = @At("HEAD"), method = "getYImage", cancellable = true)
    #elif POST_CURRENT_MC_1_20_1
    @Inject(at = @At("HEAD"), method = "getTextureY", cancellable = true)
    #endif
    public void getTextureY(CallbackInfoReturnable<Integer> cir)
    {
        // disable switching hovered vanilla texture immediately --- handle in renderOverlay
        if (SatisfyingButtons.Config.client.FadeInVanillaWidgetTexture)
        {
            var ths = (AbstractWidget) (Object) this;
            int i = ths.active ? 1 : 0;
            cir.setReturnValue(#if PRE_CURRENT_MC_1_19_2 i #elif POST_CURRENT_MC_1_20_1 46 + i * 20 #endif);
        }
    }
}