package space.maxus.refsb.util;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * Utility class related to player
 */
public class PlayerHelper {
    /**
     * Sends actionbar message to player
     * @param player player to which actionbar will be sent
     * @param legacy legact actionbar text with ampersand (&) signs as color codes
     */
    public static void sendActionbar(
            Player player,
            String legacy)
    {
        player.sendActionBar(LegacyComponentSerializer
                .legacyAmpersand()
                .deserialize(legacy));
    }

    /**
     * Gets NMS player from vanilla player
     */
    public static EntityPlayer getNMSPlayer(Player player) {
            return ((CraftPlayer) player).getHandle();
    }

    /**
     * Sends a packet to player's connections
     * @param player NMS player
     * @param packet packet to be sent
     */
    public static void sendPacket(EntityPlayer player, Packet<?> packet) {
        player.b.sendPacket(packet);
    }
}
