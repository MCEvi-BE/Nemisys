package org.itxtech.nemisys.event.plugin;

import org.itxtech.nemisys.plugin.Plugin;


public class PluginEnableEvent extends PluginEvent {

    public PluginEnableEvent(Plugin plugin) {
        super(plugin);
    }
}
