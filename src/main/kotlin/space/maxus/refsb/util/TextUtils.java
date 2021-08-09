package space.maxus.refsb.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class related to text
 */
public class TextUtils {
    /**
     * Creates simple text component
     * @param text text to be created
     */
    @NotNull
    public static TextComponent text(String text) {
        return Component.text(text).decoration(TextDecoration.ITALIC, false);
    }

    /**
     * Gets color from RGB values
     * @param r Red value
     * @param g Green value
     * @param b Blue value
     * @return Created TextColor
     */
    @NotNull
    @Contract(pure = true)
    public static TextColor rgb(int r, int g, int b) { return TextColor.color(r,g,b);}

    /**
     * Deserialies legacy string to component
     * @param legacy legacy text with ampersand as color codes
     */
    @NotNull
    public static Component legacy(String legacy) {
        Component comp = LegacyComponentSerializer.legacyAmpersand().deserialize(legacy);
        return comp.decoration(TextDecoration.ITALIC, false);
    }
    @NotNull
    @Contract(pure = true)
    public static Component noItalic(@NotNull Component in) {
        return in.decoration(TextDecoration.ITALIC, false);
    }
}
