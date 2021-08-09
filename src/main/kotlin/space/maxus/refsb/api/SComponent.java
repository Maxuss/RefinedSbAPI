package space.maxus.refsb.api;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;

/**
 * Based on {@link net.kyori.adventure.text.Component} of Adventure package <br/>
 * This interface always creates straight (non-italic) text, especially useful for item names/lore<br/>
 * Example:<br/>
 * <code>
 * // Java<br/>
 * TextComponent comp = SComponent.text("A text!").color(TextColor.color(0, 0, 255)); <br/>
 * // Kotlin<br/>
 * val comp = SComponent.text("A text!").color(TextColor.color(0, 0, 255)); <br/>
 * </code>
 * @author maxus
 * @since 0.3
 */
public interface SComponent extends Component {
    /**
     * Get text representation component
     * @param text text that should be included
     * @return generated component
     */
    @NotNull
    static TextComponent text(String text) {
        return Component.text(text).decoration(TextDecoration.ITALIC, false);
    }
}
