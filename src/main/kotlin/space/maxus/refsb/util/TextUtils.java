package space.maxus.refsb.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class TextUtils {
    public static TextComponent text(String text) {
        return Component.text(text).decoration(TextDecoration.ITALIC, false);
    }
    public static TextColor rgb(int r, int g, int b) { return TextColor.color(r,g,b);}
    public static Component legacy(String legacy) {
        Component comp = LegacyComponentSerializer.legacyAmpersand().deserialize(legacy);
        return comp.decoration(TextDecoration.ITALIC, false);
    }
    public static Component noItalic(Component in) {
        return in.decoration(TextDecoration.ITALIC, false);
    }
}
