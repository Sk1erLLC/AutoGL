package club.sk1er.autogl.listener;

import club.sk1er.autogl.AutoGL;
import club.sk1er.autogl.config.AutoGLConfig;
import club.sk1er.mods.core.util.MinecraftUtils;
import club.sk1er.mods.core.util.Multithreading;
import club.sk1er.vigilance.data.Property;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AutoGLListener {

    private boolean invoked;

    @SubscribeEvent
    public void worldSwap(WorldEvent.Unload event) {
        invoked = false;
    }

    public String setAutoGLTime() {
        int data = AutoGL.instance.getAutoGLConfig().getAutoGLTime();
        if (data > 1 && data < 6) {
            return "The game starts in " + data + " seconds!";
        }
        return "The game starts in 1 second!";
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String s = event.message.getUnformattedText().toLowerCase(Locale.ENGLISH);
        if (AutoGL.instance.getAutoGLConfig().isAntiGLEnabled() && invoked) {
            for (String primaryString : getPrimaryStrings()) {
                if (s.contains(primaryString.toLowerCase(Locale.ENGLISH)))
                    event.setCanceled(true);
            }
        }

        String unformattedText = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getUnformattedText());
        if (!MinecraftUtils.isHypixel() || !AutoGL.instance.getAutoGLConfig().isAutoGLEnabled() || AutoGL.instance.isRunning()) {
            return;
        }

        String msg = setAutoGLTime();
        int delay = AutoGL.instance.getAutoGLConfig().getAutoGLDelay();

        if (unformattedText.startsWith(msg)) {
            AutoGL.instance.setRunning(true);
            invoked = true;
            Multithreading.schedule(() -> {
                try {
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(
                            "/achat " + (getPrimaryString())
                    );
                    end();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, delay, TimeUnit.SECONDS);
        }
    }

    private void end() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AutoGL.instance.setRunning(false);
    }

    private String getPrimaryString() {
        int autoGLPhrase = AutoGL.instance.getAutoGLConfig().getAutoGLPhrase();
        String[] primaryStrings = getPrimaryStrings();
        if (autoGLPhrase > 0 && autoGLPhrase < primaryStrings.length) {
            return primaryStrings[autoGLPhrase];
        }

        return "gl";
    }

    private String[] getPrimaryStrings() {
        try {
            Property autoGLPhrase = AutoGLConfig.class.getDeclaredField("autoGLPhrase").getAnnotation(Property.class);
            return autoGLPhrase.options();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return new String[0];
    }

}
