package vice.example_mod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;

public class PlayerData
{
    public static final EntityDataSerializer<PlayerData> PLAYER_DATA_SERIALIZER = new EntityDataSerializer<>(){
        @Override
        public void write(FriendlyByteBuf buffer, PlayerData value)
        {
            buffer.writeNbt(value.save(new CompoundTag()));
        }

        @Override
        public PlayerData read(FriendlyByteBuf buffer) {
            var instance = new PlayerData();
            PlayerData.read(instance, buffer.readNbt());
            return instance;
        }

        @Override
        public PlayerData copy(PlayerData value)
        {
            var tag = new CompoundTag();
            var instance = new PlayerData();

            value.save(tag);
            PlayerData.read(instance, tag);
            return instance;
        }
    };

    public String example;

    public CompoundTag save(CompoundTag tag) {
        tag.putString("example", example);
        return tag;
    }

    public static void read(PlayerData instance, CompoundTag tag) {
        instance.example = tag.getString("example");
    }

}