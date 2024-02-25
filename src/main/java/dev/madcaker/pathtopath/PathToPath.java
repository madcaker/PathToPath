package dev.madcaker.pathtopath;

import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.content.registry.api.BlockContentRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathToPath implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("pathtopath");

	@Override
	public void onInitialize(ModContainer mod) {
		TillableBlockRegistry.register(Blocks.FARMLAND, ctx -> {
			PlayerEntity player = ctx.getPlayer();
			if (player == null) return false;
			if (!player.isSneaking()) return false;
			return HoeItem.canTillFarmland(ctx);
		}, Blocks.DIRT.getDefaultState());
		BlockContentRegistries.FLATTENABLE.put(Blocks.DIRT_PATH, Blocks.DIRT.getDefaultState());
	}
}
