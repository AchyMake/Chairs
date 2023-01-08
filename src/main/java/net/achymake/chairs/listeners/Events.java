package net.achymake.chairs.listeners;

import net.achymake.chairs.Chairs;
import net.achymake.chairs.listeners.dismount.DismountChair;
import net.achymake.chairs.listeners.interact.ClickCarpets;
import net.achymake.chairs.listeners.interact.ClickSlabs;
import net.achymake.chairs.listeners.interact.ClickStairs;

public class Events {
    public static void start(Chairs plugin){
        new DismountChair(plugin);
        new ClickCarpets(plugin);
        new ClickSlabs(plugin);
        new ClickStairs(plugin);
    }
}