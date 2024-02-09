package vice.satisfying_buttons;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


@Config(name = SatisfyingButtons.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class ModConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.TransitiveObject()
    public Client client = new Client();

    @Config(name = "client")
    public static final class Client implements ConfigData {

        @ConfigEntry.Gui.Tooltip()
        @Comment("Whether button hover animations are enabled or not. " +
                 "Turning this off toggles off all graphics changes.")
        public boolean AnimationEnabled = true;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Whether buttons should have a darkened overlay.")
        public boolean DarkenButtonOnHover = true;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Time in milliseconds for hover fade.")
        public int DarkenButtonOnHoverTime = 300;


        @ConfigEntry.ColorPicker
        @ConfigEntry.Gui.Tooltip()
        @Comment("ARGB packed color (default: 1610612736 (0,0,0,96))")
        public int DarkenButtonColor = 1610612736;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Whether the vanilla widget texture (white border by default) " +
                 "should fade in instead of applying immediately.")
        public boolean FadeInVanillaWidgetTexture = true;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Time in milliseconds for hover fade.")
        public int FadeInVanillaWidgetTextureOnHoverTime = 300;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Whether button sounds are enabled or not. Turning this off toggles off all audio changes.")
        public boolean SoundEnabled = true;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Whether a secondary, quieter sound should play when a button is unhovered..")
        public boolean ButtonUnhoverSoundEnabled = true;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Button sound Volume (range 0f - 1f).")
        public float ButtonSoundVolume = 0.5f;

        @ConfigEntry.Gui.Tooltip()
        @Comment("Button sound pitch (Default 1f)")
        public float ButtonSoundPitch = 1f;

    }
}