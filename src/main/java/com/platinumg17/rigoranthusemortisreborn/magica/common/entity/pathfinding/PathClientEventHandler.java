package com.platinumg17.rigoranthusemortisreborn.magica.common.entity.pathfinding;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.platinumg17.rigoranthusemortisreborn.magica.common.entity.pathfinding.pathjobs.AbstractPathJob.DEBUG_DRAW;

/**
 * Used to handle client events.
 */
@OnlyIn(Dist.CLIENT)
public class PathClientEventHandler {
    /**
     * Used to catch the renderWorldLastEvent in order to draw the debug nodes for pathfinding.
     *
     * @param event the catched event.
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void renderWorldLastEvent(final RenderWorldLastEvent event) {
        if (DEBUG_DRAW) {
            Pathfinding.debugDraw(event.getPartialTicks(), event.getMatrixStack());
        }
    }
}