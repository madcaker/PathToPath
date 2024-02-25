package dev.madcaker.pathtopath.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ShovelItem.class)
public class ShovelMixin {
	@Inject(
		at = @At("HEAD"),
		method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;",
		cancellable = true,
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void useOnBlock(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {
		World world = ctx.getWorld();
		BlockPos pos = ctx.getBlockPos();
		BlockState state = world.getBlockState(pos);
		if (!state.isOf(Blocks.DIRT_PATH)) return;

		PlayerEntity player = ctx.getPlayer();
		if (player != null) {
			if (player.isSneaking()) {
				return;
			}
		}
		cir.setReturnValue(ActionResult.PASS);
	}
}
