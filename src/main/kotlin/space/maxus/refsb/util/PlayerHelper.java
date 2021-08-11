package space.maxus.refsb.util;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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
}
