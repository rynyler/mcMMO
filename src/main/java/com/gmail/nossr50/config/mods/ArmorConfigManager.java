package com.gmail.nossr50.config.mods;

import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.ModManager;

import java.io.File;
import java.util.regex.Pattern;

public class ArmorConfigManager {
    public ArmorConfigManager(mcMMO plugin) {
        Pattern pattern = Pattern.compile("armor\\.(?:.+)\\.yml");
        File dataFolder = new File(mcMMO.getModDirectory());
        File vanilla = new File(dataFolder, "armor.default.yml");
        ModManager modManager = mcMMO.getModManager();

        if (!vanilla.exists()) {
            plugin.saveResource(plugin.getDataFolder().getName() + File.separator + "armor.default.yml", false);
        }

        for (String fileName : dataFolder.list()) {
            if (!pattern.matcher(fileName).matches()) {
                continue;
            }

            File file = new File(dataFolder, fileName);

            if (file.isDirectory()) {
                continue;
            }

            modManager.registerCustomArmor(new CustomArmorConfig(fileName));
        }
    }
}
