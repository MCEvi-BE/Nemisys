package org.itxtech.nemisys.plugin;

import org.itxtech.nemisys.Server;
import org.itxtech.nemisys.command.CommandExecutor;
import org.itxtech.nemisys.utils.Config;

import java.io.File;
import java.io.InputStream;


public interface Plugin extends CommandExecutor {
    
    void onLoad();

    
    void onEnable();

    
    boolean isEnabled();

    
    void onDisable();

    
    boolean isDisabled();

    
    File getDataFolder();

    
    PluginDescription getDescription();

    
    InputStream getResource(String filename);

    
    boolean saveResource(String filename);

    

    boolean saveResource(String filename, boolean replace);

    boolean saveResource(String filename, String outputName, boolean replace);

    
    Config getConfig();

    
    void saveConfig();

    
    void saveDefaultConfig();

    
    void reloadConfig();

    
    Server getServer();

    
    String getName();

    
    PluginLogger getLogger();

    
    PluginLoader getPluginLoader();

}
