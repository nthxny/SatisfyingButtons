package vice.example_mod;

import dev.architectury.registry.registries.DeferredRegister;

#if PRE_CURRENT_MC_1_19_2
import dev.architectury.registry.registries.Registries;
import net.minecraft.core.Registry;
#elif POST_CURRENT_MC_1_20_1
import net.minecraft.core.registries.Registries;
#endif

import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.item.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

public class ExampleMod
{
	public static final String MOD_ID = "example_mod";
	public static ModConfig Config;

	#if PRE_CURRENT_MC_1_19_2
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registry.ITEM_REGISTRY);
	#elif POST_CURRENT_MC_1_20_1
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    #endif


	public static void init() {
		EntityDataSerializers.registerSerializer(PlayerData.PLAYER_DATA_SERIALIZER);

		AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		Config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
	}
}
