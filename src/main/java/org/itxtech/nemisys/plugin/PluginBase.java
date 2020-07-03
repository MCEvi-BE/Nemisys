package org.itxtech.nemisys.plugin;

import org.itxtech.nemisys.Server;
import org.itxtech.nemisys.command.Command;
import org.itxtech.nemisys.command.CommandSender;
import org.itxtech.nemisys.command.PluginIdentifiableCommand;
import org.itxtech.nemisys.utils.Config;
import org.itxtech.nemisys.utils.Utils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;


abstract public class PluginBase implements Plugin {

    private PluginLoader loader;

    private Server server;

    private boolean isEnabled = false;

    private boolean initialized = false;

    private PluginDescription description;

    private File dataFolder;
    private Config config;
    private File configFile;
    private File file;
    private PluginLogger logger;


    public void onLoad() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public final boolean isEnabled() {
        return isEnabled;
    }

    
    public final void setEnabled(boolean value) {
        if (isEnabled != value) {
            isEnabled = value;
            if (isEnabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    
    public final void setEnabled() {
        this.setEnabled(true);
    }

    public final boolean isDisabled() {
        return !isEnabled;
    }

    public final File getDataFolder() {
        return dataFolder;
    }

    public final PluginDescription getDescription() {
        return description;
    }

    
    public final void init(PluginLoader loader, Server server, PluginDescription description, File dataFolder, File file) {
        if (!initialized) {
            initialized = true;
            this.loader = loader;
            this.server = server;
            this.description = description;
            this.dataFolder = dataFolder;
            this.file = file;
            this.configFile = new File(this.dataFolder, "config.yml");
            this.logger = new PluginLogger(this);
        }
    }

    public PluginLogger getLogger() {
        return logger;
    }

    
    public final boolean isInitialized() {
        return initialized;
    }

    
    public PluginIdentifiableCommand getCommand(String name) {
        PluginIdentifiableCommand command = this.getServer().getPluginCommand(name);
        if (command == null || !command.getPlugin().equals(this)) {
            command = this.getServer().getPluginCommand(this.description.getName().toLowerCase() + ":" + name);
        }

        if (command != null && command.getPlugin().equals(this)) {
            return command;
        } else {
            return null;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Override
    public InputStream getResource(String filename) {
        return this.getClass().getClassLoader().getResourceAsStream(filename);
    }

    @Override
    public boolean saveResource(String filename) {
        return saveResource(filename, false);
    }

    @Override
    public boolean saveResource(String filename, boolean replace) {
        return saveResource(filename, filename, replace);
    }

    @Override
    public boolean saveResource(String filename, String outputName, boolean replace) {
        File out = new File(dataFolder, outputName);
        if (!out.exists() || replace) {
            try (InputStream resource = getResource(filename)) {
                if (resource != null) {
                    File outFolder = out.getParentFile();
                    if (!outFolder.exists()) {
                        outFolder.mkdirs();
                    }
                    Utils.writeFile(out, resource);

                    return true;
                }
            } catch (IOException e) {
                Server.getInstance().getLogger().logException(e);
            }
        }
        return false;
    }

    @Override
    public Config getConfig() {
        if (this.config == null) {
            this.reloadConfig();
        }
        return this.config;
    }

    @Override
    public void saveConfig() {
        if (!this.getConfig().save()) {
            this.getLogger().critical("Could not save config to " + this.configFile.toString());
        }
    }

    @Override
    public void saveDefaultConfig() {
        if (!this.configFile.exists()) {
            this.saveResource("config.yml", false);
        }
    }

    @Override
    public void reloadConfig() {
        this.config = new Config(this.configFile);
        InputStream configStream = this.getResource("config.yml");
        if (configStream != null) {
            DumperOptions dumperOptions = new DumperOptions();
            dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(dumperOptions);
            try {
                this.config.setDefault(yaml.loadAs(Utils.readFile(this.configFile), LinkedHashMap.class));
            } catch (IOException e) {
                Server.getInstance().getLogger().logException(e);
            }
        }
    }

    @Override
    public Server getServer() {
        return server;
    }

    @Override
    public String getName() {
        return this.description.getName();
    }

    
    public final String getFullName() {
        return this.description.getFullName();
    }

    
    protected File getFile() {
        return file;
    }

    @Override
    public PluginLoader getPluginLoader() {
        return this.loader;
    }
}
