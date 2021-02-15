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
            description = "Remove GL's from chat."
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

    @Property(
            type = PropertyType.SLIDER, name = "Message to say after",
            description = "Select the time message to send GL after",
            category = "General",
            subcategory = "General",
            min = 1,
            max = 5
    )
    private int autoGLTime = 1;

    @Property(
            type = PropertyType.SLIDER, name = "Delay",
            description = "The delay between the selected time message and sending of GL (Seconds)",
            category = "General",
            subcategory = "General",
            min = 0,
            max = 20
    )
    private int autoGLDelay = 5;




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

    public int getAutoGLDelay() {
        return autoGLDelay;
    }

    public int getAutoGLTime() {
        return autoGLTime;
    }
}
