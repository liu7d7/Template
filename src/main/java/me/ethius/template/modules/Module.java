package me.ethius.template.modules;

import me.ethius.template.Template;
import me.ethius.template.utils.client.Setting;
import net.minecraft.client.MinecraftClient;

import java.util.List;

public class Module {

    protected static MinecraftClient mc = Template.mc;

    public boolean opened = false;

    public String name;
    public String desc;
    public int key;
    public boolean toggled;
    public Category category;
    public List<Setting> settings;
    public static final int KEY_UNBOUND = -2;

    public Module(String name, String desc, int key, Category category) {
        this.name = name;
        this.desc = desc;
        this.key = key;
        this.category = category;
    }

    public enum Category {
        combat, misc, player, render, world
    }

    public void onEnable() {
        Template.eventBus.register(this);
    }

    public void onDisable() {
        Template.eventBus.unregister(this);
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) onEnable();
        else onDisable();
        Template.saveConfig.timedSave();
    }

    public void toggleNoSave() {
        toggled = !toggled;
        if (toggled) onEnable();
        else onDisable();
    }

    public void setToggled(boolean b) {
        toggled = b;
        if (toggled) onEnable();
        else onDisable();
        Template.saveConfig.timedSave();
    }

    public String getName() {
        return name;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

}
