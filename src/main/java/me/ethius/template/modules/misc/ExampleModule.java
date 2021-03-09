package me.ethius.template.modules.misc;

import me.ethius.template.modules.Module;
import me.ethius.template.utils.client.Setting;

import java.util.Arrays;
import java.util.List;

public class ExampleModule extends Module {

    final List<String> modes = Arrays.asList("hi", "there", "amateur", "client", "developer");

    public final Setting exampleSetting = new Setting("Fitmc", true);
    public final Setting exampleSetting2 = new Setting("SalC1", "hi", modes);
    public final Setting exampleSetting3 = new Setting("FitMC", 0.5, 0.1, 7, 0.1, false);

    final List<Setting> settings = Arrays.asList(exampleSetting, exampleSetting2, exampleSetting3);

    public ExampleModule() {
        super("ExampleModule", "smh", KEY_UNBOUND, Category.misc);
        super.settings = settings;
    }



}
