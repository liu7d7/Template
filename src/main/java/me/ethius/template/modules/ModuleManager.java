package me.ethius.template.modules;

import me.ethius.template.modules.misc.ExampleModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        add(new ExampleModule());
    }

    public static void add(Module module) {
        modules.add(module);
    }

}
