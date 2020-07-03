package org.itxtech.nemisys.plugin;

import org.itxtech.nemisys.event.Event;
import org.itxtech.nemisys.event.Listener;


public interface EventExecutor {

    void execute(Listener listener, Event event);
}
