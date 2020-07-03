package org.itxtech.nemisys.scheduler;

import org.itxtech.nemisys.Server;


public abstract class Task implements Runnable {
    private TaskHandler taskHandler = null;

    public final TaskHandler getHandler() {
        return this.taskHandler;
    }

    public final void setHandler(TaskHandler taskHandler) {
        if (this.taskHandler == null || taskHandler == null) {
            this.taskHandler = taskHandler;
        }
    }

    public final int getTaskId() {
        return this.taskHandler != null ? this.taskHandler.getTaskId() : -1;
    }

    
    public abstract void onRun(int currentTick);

    @Override
    public final void run() {

    }

    public void onCancel() {

    }

    public void cancel() {
        try {
            this.getHandler().cancel();
        } catch (RuntimeException ex) {
            Server.getInstance().getLogger().critical("Exception while invoking onCancel", ex);
        }
    }

}
