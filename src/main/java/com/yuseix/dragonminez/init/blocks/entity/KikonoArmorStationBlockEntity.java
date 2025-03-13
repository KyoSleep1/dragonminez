package com.yuseix.dragonminez.init.blocks.entity;

import com.yuseix.dragonminez.init.MainBlockEntities;
import com.yuseix.dragonminez.init.menus.menutypes.KikonoArmorStationMenuType;
import com.yuseix.dragonminez.server.recipes.ArmorStationRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class KikonoArmorStationBlockEntity extends BlockEntity implements MenuProvider {
	private final ItemStackHandler itemHandler = new ItemStackHandler(12);

	protected void onContentsChanged(int slot) {
		setChanged();
		if (!level.isClientSide()) {
			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
		}
	}

	private static final int SLOT_1 = 0;
	private static final int SLOT_2 = 1;
	private static final int SLOT_3 = 2;
	private static final int SLOT_4 = 3;
	private static final int SLOT_5 = 4;
	private static final int SLOT_6 = 5;
	private static final int SLOT_7 = 6;
	private static final int SLOT_8 = 7;
	private static final int SLOT_9 = 8;
	private static final int PRESET = 9;
	private static final int ARMOR = 10;
	private static final int OUTPUT = 11;

	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	protected final ContainerData data;
	private int progress;
	private int maxProgress;

	public KikonoArmorStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(MainBlockEntities.KIKONO_ARMOR_STATION_BE.get(), pPos, pBlockState);
		this.data = new ContainerData() {
			@Override
			public int get(int pIndex) {
				return switch (pIndex) {
					case 0 -> KikonoArmorStationBlockEntity.this.progress;
					case 1 -> KikonoArmorStationBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int pIndex, int pValue) {
				switch (pIndex) {
					case 0 -> KikonoArmorStationBlockEntity.this.progress = pValue;
					case 1 -> KikonoArmorStationBlockEntity.this.maxProgress = pValue;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		};
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER) {
			return lazyItemHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}

	public void drops() {
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		}

		Containers.dropContents(this.level, this.worldPosition, inventory);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("block.dragonminez.kikono_armor_station.title");
	}

	@Override
	public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
		return new KikonoArmorStationMenuType(pContainerId, pPlayerInventory, this, this.data);
	}

	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.put("inventory", itemHandler.serializeNBT());
		pTag.putInt("kikono_armor_station.progress", progress);
		super.saveAdditional(pTag);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		itemHandler.deserializeNBT(pTag.getCompound("inventory"));
		progress = pTag.getInt("kikono_armor_station.progress");
	}

	public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
		Optional<ArmorStationRecipes> recipe = getCurrentRecipe();
		if (recipe.isPresent() && this.itemHandler.getStackInSlot(OUTPUT).isEmpty()) {
			if (this.maxProgress == 0) {
				this.maxProgress = recipe.get().getCraftingTime();  // Ajusta el tiempo de la receta
			}
			if (!(progress >= maxProgress)) { // Puse dos veces el ++ asi va mas rapido, me da paja cambiar el tiempo en cada craft xd
				progress++;
				progress++;
				setChanged(pLevel, pPos, pState);
			} else {
				craftItem();
				progress = 0;
				//System.out.println("Item crafteado");
			}
		} else {
			progress = 0;
		}
	}

	private void craftItem() {
		Optional<ArmorStationRecipes> recipe = getCurrentRecipe();
		ItemStack result = recipe.get().getResultItem(null);


		this.itemHandler.extractItem(SLOT_1, 1, false);
		this.itemHandler.extractItem(SLOT_2, 1, false);
		this.itemHandler.extractItem(SLOT_3, 1, false);
		this.itemHandler.extractItem(SLOT_4, 1, false);
		this.itemHandler.extractItem(SLOT_5, 1, false);
		this.itemHandler.extractItem(SLOT_6, 1, false);
		this.itemHandler.extractItem(SLOT_7, 1, false);
		this.itemHandler.extractItem(SLOT_8, 1, false);
		this.itemHandler.extractItem(SLOT_9, 1, false);
		this.itemHandler.extractItem(PRESET, 1, false);
		this.itemHandler.extractItem(ARMOR, 1, false);

		this.itemHandler.setStackInSlot(OUTPUT, new ItemStack(result.getItem(),
				this.itemHandler.getStackInSlot(OUTPUT).getCount() + result.getCount()));

		if (level != null) {
			level.playSound(null, this.worldPosition,
					SoundEvents.SMITHING_TABLE_USE,
					SoundSource.BLOCKS, 1.0f, 1.0f);
		}
	}

	private Optional<ArmorStationRecipes> getCurrentRecipe() {
		SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++) {
			inventory.setItem(i, this.itemHandler.getStackInSlot(i));
		}

		return this.level.getRecipeManager().getRecipeFor(ArmorStationRecipes.Type.INSTANCE, inventory, level);
	}

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag() {
		return saveWithoutMetadata();
	}
}
