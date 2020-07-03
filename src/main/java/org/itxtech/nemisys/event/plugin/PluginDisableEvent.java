package org.itxtech.nemisys.event.plugin;

import org.itxtech.nemisys.plugin.Plugin;


public class PluginDisableEvent extends PluginEvent {

    public PluginDisableEvent(Plugin plugin) {
        super(plugin);
    }
}
