package me.ethius.template.utils.config;

import com.google.gson.*;
import me.ethius.template.modules.Module;
import me.ethius.template.modules.ModuleManager;
import me.ethius.template.utils.client.Setting;
import me.ethius.template.utils.client.Timer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
 * @author  ChiquitaV2
 */
public class SaveConfig {

    public static Timer saveTimer;

    public SaveConfig() {
        try {
            saveConfig();
            saveModules();
            saveTimer = new Timer();
            timedSave();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String folderName = "Neptune/";
    public static String moduleName = "Modules/";

    public void saveConfig() throws IOException {
        if (!Files.exists(Paths.get(folderName))) {
            Files.createDirectories(Paths.get(folderName));
        }
        if (!Files.exists(Paths.get(folderName + moduleName))) {
            Files.createDirectories(Paths.get(folderName + moduleName));
        }
    }

    public void saveModules() {
        for (Module module : ModuleManager.modules) {
            saveIndividualModule(module);
        }
    }

    public void saveIndividualModule(Module module) {
        try {
            makeFile(moduleName, module.getName());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream(folderName + moduleName + module.getName() + ".json"), StandardCharsets.UTF_8);
            JsonObject moduleObject = new JsonObject();
            JsonObject settingObject = new JsonObject();
            JsonObject toggledObject = new JsonObject();
            JsonObject hiddenObject = new JsonObject();
            JsonObject bindObject = new JsonObject();
            moduleObject.add("Module", new JsonPrimitive(module.getName()));
            toggledObject.add("Toggled", new JsonPrimitive(module.toggled));
            bindObject.add("Bind", new JsonPrimitive(module.key));

            if (module.settings != null) {
                for (Setting settingsList : module.settings) {
                    if (settingsList.isCheck()) {
                        settingObject.add(settingsList.getName(), new JsonPrimitive(((Boolean) settingsList.getValBoolean())));
                    }  else if (settingsList.isSlider()) {
                        settingObject.add(settingsList.getName(), new JsonPrimitive((((Double) settingsList.getValDouble()))));
                    }  else if (settingsList.isCombo()) {
                        //ahhhaha NullPointerException fjsadkflsakjfsajflksjf klsjf fuck idk i can't stop ig
                        settingObject.add(settingsList.getName(), new JsonPrimitive(settingsList.getValString().toString()));
                    }
                }

            }

            moduleObject.add("Toggled", toggledObject);
            moduleObject.add("Hidden", hiddenObject);
            moduleObject.add("Bind", bindObject);
            moduleObject.add("Settings", settingObject);
            String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
            fileOutputStreamWriter.write(jsonString);
            fileOutputStreamWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeFile(String location, String name) throws IOException {
        if (location != null) {
            if (!Files.exists(Paths.get(folderName + location + name + ".json"))) {
                Files.createFile(Paths.get(folderName + location + name + ".json"));
            }
            else {
                File file = new File(folderName + location + name + ".json");

                if (file.delete()) {
                    Files.createFile(Paths.get(folderName + location + name + ".json"));
                }
            }
        } else {
            if (!Files.exists(Paths.get(folderName + name + ".json"))) {
                Files.createFile(Paths.get(folderName + name + ".json"));
            }
            else {
                File file = new File(folderName + name + ".json");

                file.delete();

                Files.createFile(Paths.get(folderName +name + ".json"));
            }
        }

    }

    public void timedSave(){
        if (saveTimer.passed(5000))
        {
            saveModules();
            saveTimer.reset();
        }
    }
}
