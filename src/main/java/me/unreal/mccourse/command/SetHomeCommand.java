package me.unreal.mccourse.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.unreal.mccourse.util.IEntityDataSaver;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class SetHomeCommand {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("home")
                .then(CommandManager.literal("set").executes(SetHomeCommand::run)));
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        IEntityDataSaver player = ((IEntityDataSaver) context.getSource().getPlayer());
        BlockPos playerPos = context.getSource().getPlayer().getBlockPos();
        String positionString = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

        player.mC_Course_1_21_X$getPersistentData().putIntArray("mccourse.homepos",
                new int[] { playerPos.getX(), playerPos.getY(), playerPos.getZ() });

        context.getSource().sendFeedback(() -> Text.literal("Set Home at " + positionString), true);
        return 1;
    }
}
