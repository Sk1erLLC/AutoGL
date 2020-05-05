package club.sk1er.autogl.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

public class AutoGLConfig extends Vigilant {

    @Property(
        type = PropertyType.SWITCH,
        name = "AutoGL",
        category = "General",
        subcategory = "General",
        description = "Toggle AutoGL entirely."
    )
    private boolean autoGLEnabled = true;

    @Property(
        type = PropertyType.SWITCH,
        name = "AntiGL",
        category = "General",
        subcategory = "Miscellaneous",
        description = "Remove gl's from chat."
    )
    private boolean antiGLEnabled = false;
    @Property(
        type = PropertyType.SELECTOR,
        name = "Phrase",
        category = "General",
        subcategory = "General",
        description = "Choose what message is said on game completion",
        options = {"gl", "Good Luck", "GL", "Have a good game!", "glhf", "AutoGL By Sk1er"}
    )
    private int autoGLPhrase = 1;

    public AutoGLConfig() {
        super(new File("./config/autogl.toml"));
        initialize();
    }

    public boolean isAutoGLEnabled() {
        return autoGLEnabled;
    }

    public boolean isAntiGLEnabled() {
        return antiGLEnabled;
    }

    public int getAutoGLPhrase() {
        return autoGLPhrase;
    }
}
