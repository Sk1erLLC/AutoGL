package club.sk1er.autogl.command;

import club.sk1er.autogl.AutoGL;
import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.util.MinecraftUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class AutoGLCommand extends CommandBase {
    /**
     * Gets the name of the command
     */
    @Override
    public String getCommandName() {
        return "autogl";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender user the command is being sent from
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    /**
     * Callback when the command is invoked
     *
     * @param sender user the command is being sent from
     * @param args   arguments provided via command
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            ModCore.getInstance().getGuiHandler().open(AutoGL.instance.getAutoGLConfig().gui());
        } else {
            MinecraftUtils.sendMessage(EnumChatFormatting.RED + "AutoGL has no arguments. Usage: /autogl");
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
