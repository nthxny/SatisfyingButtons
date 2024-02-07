package vice.example_mod.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vice.example_mod.ExampleMod;
import vice.example_mod.accessors.PlayerDataAccessor;
import vice.example_mod.PlayerData;

@Mixin({Player.class})
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerDataAccessor
{
    @Unique
    private static final EntityDataAccessor<PlayerData> example_mod$DATA_ACCESSOR = SynchedEntityData.defineId(Player.class, PlayerData.PLAYER_DATA_SERIALIZER);
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) { super(entityType, level); }

    @Unique
    private PlayerData example_mod$player_data = new PlayerData();
    @Override @Unique
    public PlayerData example_mod$getPlayerData() { return ((Player) (LivingEntity)this).getEntityData().get(example_mod$DATA_ACCESSOR); }


    @Inject(at = {@At("HEAD")}, method = {"tick"})
    private void onTick(CallbackInfo info)
    {

    }


    @Inject(at = {@At("TAIL")}, method = {"addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"})
    private void onWriteCustomData(CompoundTag nbt, CallbackInfo info) {
        nbt.put(ExampleMod.MOD_ID, example_mod$player_data.save(new CompoundTag()));
    }

    @Inject(at = {@At("TAIL")}, method = {"readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"})
    private void onReadCustomData(CompoundTag nbt, CallbackInfo info) {
        if (example_mod$player_data == null)
            example_mod$player_data = new PlayerData();

        PlayerData.read(example_mod$player_data, nbt.getCompound(ExampleMod.MOD_ID));
        example_mod$trackData();
    }

    @Unique
    private void example_mod$trackData() {

        #if PRE_CURRENT_MC_1_19_2
        this.entityData.set(example_mod$DATA_ACCESSOR, example_mod$player_data);
        #elif POST_CURRENT_MC_1_20_1
        this.entityData.set(example_mod$DATA_ACCESSOR, example_mod$player_data, true);
        #endif


    }

    @Inject(at = {@At("TAIL")}, method = {"defineSynchedData"})
    private void onInitDataTracker(CallbackInfo info) {
        if (example_mod$player_data == null)
            example_mod$player_data = new PlayerData();

        this.entityData.define(example_mod$DATA_ACCESSOR, example_mod$player_data);
    }
}