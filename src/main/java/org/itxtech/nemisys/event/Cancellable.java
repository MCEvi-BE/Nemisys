package org.itxtech.nemisys.event;


public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean forceCancel);

    void setCancelled();
}
