package me.unreal.mccourse.screen;

import me.unreal.mccourse.MCCourseMod;
import me.unreal.mccourse.screen.custom.CrystallizerScreenHandler;
import me.unreal.mccourse.screen.custom.PedestalScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<PedestalScreenHandler> PEDESTAL_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "pedestal_screen_handler"),
                    new ExtendedScreenHandlerType<>(PedestalScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<CrystallizerScreenHandler> CRYSTALLIZER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MCCourseMod.MOD_ID, "crystalizer_screen_handler"),
                    new ExtendedScreenHandlerType<>(CrystallizerScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        MCCourseMod.LOGGER.info("Registering Screen Handlers for " + MCCourseMod.MOD_ID);
    }
}
