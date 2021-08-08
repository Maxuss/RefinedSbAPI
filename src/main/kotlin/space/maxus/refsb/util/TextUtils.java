package space.maxus.refsb.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class TextUtils {
    public static TextComponent text(String text) {
        return Component.text(text);
    }
    public static TextColor rgb(int r, int g, int b) { return TextColor.color(r,g,b);}
    public static TextComponent legacy(String legacy) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(legacy); }
}
