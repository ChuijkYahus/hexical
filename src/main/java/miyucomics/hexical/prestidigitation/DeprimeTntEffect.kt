package miyucomics.hexical.prestidigitation

import miyucomics.hexical.interfaces.PrestidigitationEffect
import net.minecraft.block.Blocks
import net.minecraft.entity.Entity
import net.minecraft.entity.TntEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos

class DeprimeTntEffect : PrestidigitationEffect {
	override fun effectBlock(caster: ServerPlayerEntity, position: BlockPos) {}

	override fun effectEntity(caster: ServerPlayerEntity, entity: Entity) {
		if (entity is TntEntity) {
			if (entity.world.getBlockState(entity.blockPos).isOf(Blocks.WATER) || entity.world.getBlockState(entity.blockPos).isOf(Blocks.LAVA) || entity.world.getBlockState(entity.blockPos).isOf(Blocks.AIR) || entity.world.getBlockState(entity.blockPos).isOf(Blocks.CAVE_AIR) || entity.world.getBlockState(entity.blockPos).isOf(Blocks.VOID_AIR))
				entity.world.setBlockState(entity.blockPos, Blocks.TNT.defaultState)
			entity.discard()
		}
	}
}