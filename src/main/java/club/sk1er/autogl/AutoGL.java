package club.sk1er.autogl;

import club.sk1er.autogl.command.AutoGLCommand;
import club.sk1er.autogl.config.AutoGLConfig;
import club.sk1er.autogl.listener.AutoGLListener;
import club.sk1er.modcore.ModCoreInstaller;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "autogl", name = "AutoGL", version = "1.1")
public class AutoGL {

    private AutoGLConfig autoGLConfig;
    private boolean running;

    @Mod.Instance("autogl")
    public static AutoGL instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);

        autoGLConfig = new AutoGLConfig();
        autoGLConfig.preload();

        running = false;

        ClientCommandHandler.instance.registerCommand(new AutoGLCommand());
        MinecraftForge.EVENT_BUS.register(new AutoGLListener());
    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public AutoGLConfig getAutoGLConfig() {
        return autoGLConfig;
    }
}
