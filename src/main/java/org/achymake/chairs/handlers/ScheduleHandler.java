package org.achymake.chairs.handlers;

import org.achymake.chairs.Chairs;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class ScheduleHandler {
    private Chairs getInstance() {
        return Chairs.getInstance();
    }
    private BukkitScheduler getScheduler() {
        return getInstance().getBukkitScheduler();
    }
    public BukkitTask runLater(Runnable runnable, long timer) {
        return getScheduler().runTaskLater(getInstance(), runnable, timer);
    }
    public void runAsynchronously(Runnable runnable) {
        getScheduler().runTaskAsynchronously(getInstance(), runnable);
    }
    public boolean isQueued(int taskID) {
        return getScheduler().isQueued(taskID);
    }
    public void cancel(int taskID) {
        if (isQueued(taskID)) {
            getScheduler().cancelTask(taskID);
        }
    }
    public void disable() {
        getScheduler().cancelTasks(getInstance());
    }
}