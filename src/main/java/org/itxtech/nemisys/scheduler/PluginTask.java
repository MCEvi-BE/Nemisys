package org.itxtech.nemisys.scheduler;


import org.itxtech.nemisys.plugin.Plugin;


public abstract class PluginTask<T extends Plugin> extends Task {

    protected T owner;

    
    public PluginTask(T owner) {
        this.owner = owner;
    }

    
    public final T getOwner() {
        return this.owner;
    }

}
