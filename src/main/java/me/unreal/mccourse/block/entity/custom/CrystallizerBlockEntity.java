package me.unreal.mccourse.block.entity.custom;

import me.unreal.mccourse.block.entity.IImplementedInventory;
import me.unreal.mccourse.block.entity.ModBlockEntities;
import me.unreal.mccourse.item.ModItems;
import me.unreal.mccourse.screen.custom.CrystallizerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CrystallizerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, IImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public static final int FLUID_ITEM_SLOT = 0;
    public static final int INPUT_ITEM_SLOT = 1;
    public static final int OUTPUT_ITEM_SLOT = 2;
    public static final int ENERGY_ITEM_SLOT = 3;


    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;


    public CrystallizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRYSTALLIZER_BE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> CrystallizerBlockEntity.this.progress;
                    case 1 -> CrystallizerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: CrystallizerBlockEntity.this.progress = value;
                    case 1: CrystallizerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.mccourse.crystallizer");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrystallizerScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("crystallizer.progress", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("crystallizer.progress");
        super.readNbt(nbt, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(hasRecipe() && canInsertIntoOutputSlot()) {
            increaseCraftingProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();

            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        this.removeStack(INPUT_ITEM_SLOT, 1);
        this.setStack(OUTPUT_ITEM_SLOT, new ItemStack(ModItems.FLUORITE, this.getStack(OUTPUT_ITEM_SLOT).getCount() + 1));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean canInsertIntoOutputSlot() {
        return this.getStack(OUTPUT_ITEM_SLOT).isEmpty() || this.getStack(OUTPUT_ITEM_SLOT).getCount() < this.getStack(OUTPUT_ITEM_SLOT).getMaxCount();
    }

    private boolean hasRecipe() {
        ItemStack input = new ItemStack(ModItems.RAW_FLUORITE);
        ItemStack output = new ItemStack(ModItems.FLUORITE, 6);
        
        return this.getStack(INPUT_ITEM_SLOT).getItem() == input.getItem() && canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return this.getStack(OUTPUT_ITEM_SLOT).isEmpty() || this.getStack(OUTPUT_ITEM_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.getStack(OUTPUT_ITEM_SLOT).getMaxCount() >= this.getStack(OUTPUT_ITEM_SLOT).getCount() + count;
    }


    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
