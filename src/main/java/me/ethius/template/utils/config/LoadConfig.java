package me.ethius.template.utils.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.ethius.template.modules.Module;
import me.ethius.template.modules.ModuleManager;
import me.ethius.template.utils.client.Setting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * @author  ChiquitaV2
 */

public class LoadConfig {

    public static String folderName = SaveConfig.folderName;
    public static String moduleName = SaveConfig.moduleName;

    public LoadConfig() {
        loadModules();
    }

    public void loadModules() {
        String moduleLocation = folderName + moduleName;

        for (Module module : ModuleManager.modules) {
            try {
                if (!Files.exists(Paths.get(moduleLocation + module.getName() + ".json"))) {
                    return;
                }

                InputStream inputStream = Files.newInputStream(Paths.get(moduleLocation + module.getName() + ".json"));
                JsonObject moduleObject = new JsonParser().parse(new InputStreamReader(inputStream)).getAsJsonObject();

                if (moduleObject.get("Module") == null) {
                    return;
                }

                JsonObject toggleObject = moduleObject.get("Toggled").getAsJsonObject();
                JsonElement toggleElement = toggleObject.get("Toggled");
                // module.setToggled(toggleElement.getAsBoolean());
                if (toggleElement.getAsBoolean()) {
                    if(!module.toggled)
                        module.toggleNoSave();
                }
                else if (module.toggled)
                    module.toggle();

                JsonObject bindObject = moduleObject.get("Bind").getAsJsonObject();
                JsonElement bindElement = bindObject.get("Bind");
                module.key = (bindElement.getAsInt());

                JsonObject settingObject = moduleObject.get("Settings").getAsJsonObject();

                if (module.settings != null) {
                    for (Setting settingsList : module.settings) {
                        JsonElement valueElement = settingObject.get(settingsList.getName());
                        if (settingsList.isCheck() && valueElement != null) {
                            settingsList.setValBoolean(valueElement.getAsBoolean());
                        } else if (settingsList.isSlider() && valueElement != null) {
                            settingsList.setValDouble(valueElement.getAsDouble());
                        }  else if (settingsList.isCombo() && valueElement != null) {
                            if (valueElement.getAsString() != null){
                                settingsList.setValString(valueElement.getAsString());
                            }
                        }
                    }
                }


                inputStream.close();
            } catch (IOException e) {
                System.out.println(module.getName());
                e.printStackTrace();
            }
        }
    }


}
