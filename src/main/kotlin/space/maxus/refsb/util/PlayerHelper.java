package space.maxus.refsb.util;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerHelper {
    public static void sendActionbar(
            Player player,
            String legacy)
    {
        player.sendActionBar(LegacyComponentSerializer
                .legacyAmpersand()
                .deserialize(legacy));
    }

    public static EntityPlayer getNMSPlayer(Player player) {
            return ((CraftPlayer) player).getHandle();
    }

    public static void sendPacket(EntityPlayer player, Packet<?> packet) {
        player.b.sendPacket(packet);
    }
}
