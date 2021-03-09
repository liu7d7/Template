package me.ethius.template;

import com.google.common.eventbus.EventBus;
import me.ethius.template.modules.ModuleManager;
import me.ethius.template.utils.config.LoadConfig;
import me.ethius.template.utils.config.SaveConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;

public class Template implements ModInitializer {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static LoadConfig loadConfig;
    public static SaveConfig saveConfig;

    public static final String NAME = "Template ";
    public static final String VERSION = "1.0a ";

    public static EventBus eventBus = new EventBus();

    @Override
    public void onInitialize() {
        ModuleManager.init();
        System.out.println("\n" +
                "\n" +
                "\n" +
                "$$$$$$$$\\                                $$\\            $$\\               \n" +
                "\\__$$  __|                               $$ |           $$ |              \n" +
                "   $$ | $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\  $$ | $$$$$$\\ $$$$$$\\    $$$$$$\\  \n" +
                "   $$ |$$  __$$\\ $$  _$$  _$$\\ $$  __$$\\ $$ | \\____$$\\\\_$$  _|  $$  __$$\\ \n" +
                "   $$ |$$$$$$$$ |$$ / $$ / $$ |$$ /  $$ |$$ | $$$$$$$ | $$ |    $$$$$$$$ |\n" +
                "   $$ |$$   ____|$$ | $$ | $$ |$$ |  $$ |$$ |$$  __$$ | $$ |$$\\ $$   ____|\n" +
                "   $$ |\\$$$$$$$\\ $$ | $$ | $$ |$$$$$$$  |$$ |\\$$$$$$$ | \\$$$$  |\\$$$$$$$\\ \n" +
                "   \\__| \\_______|\\__| \\__| \\__|$$  ____/ \\__| \\_______|  \\____/  \\_______|\n" +
                "                               $$ |                                       \n" +
                "                               $$ |                                       \n" +
                "                               \\__|                                       \n" +
                Template.NAME + Template.VERSION + "has been loaded.\n" +
                "You have successfully started your skidding process in Minecraft Java 1.16.\n" +
                "Created and maintained by Ethius.\n" +
                "~9/3/2021\n");
        mc.execute(this::updateTitle);
    }

    private void updateTitle() {
        final Window window = mc.getWindow();
        window.setTitle(NAME + VERSION);
    }


}
