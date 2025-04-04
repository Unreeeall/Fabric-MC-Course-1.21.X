package me.unreal.mccourse.event;

import me.unreal.mccourse.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCopyHandler implements ServerPlayerEvents.CopyFrom {
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        ((IEntityDataSaver) newPlayer).getPersistentData().putIntArray("mccourse.homepos",
                ((IEntityDataSaver) oldPlayer).getPersistentData().getIntArray("mccourse.homepos"));
    }
}