package miyucomics.hexical.casting.operators.dye

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.iota.EntityIota
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.NullIota
import at.petrak.hexcasting.api.spell.iota.Vec3Iota
import miyucomics.hexical.data.DyeData
import miyucomics.hexical.iota.DyeIota
import net.minecraft.block.Block
import net.minecraft.block.SignBlock
import net.minecraft.block.entity.SignBlockEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.DyeItem
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.math.BlockPos

class OpGetDye : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		return listOf(when (args[0]) {
			is EntityIota -> {
				val entity = args.getEntity(0, argc)
				ctx.assertEntityInRange(entity)
				processEntity(entity)
			}
			is Vec3Iota -> {
				val position = args.getBlockPos(0, argc)
				ctx.assertVecInRange(position)
				processVec3d(position, ctx.world)
			}
			else -> NullIota()
		})
	}

	private fun processEntity(entity: Entity): Iota {
		return when (entity) {
			is ItemEntity -> {
				when (val item = entity.stack.item) {
					is BlockItem -> getDyeFromBlock(item.block)
					is DyeItem -> DyeIota(item.color)
					else -> NullIota()
				}
			}
			is SheepEntity -> DyeIota(entity.color)
			else -> NullIota()
		}
	}
	private fun processVec3d(position: BlockPos, world: ServerWorld): Iota {
		val state = world.getBlockState(position)
		if (state.block is SignBlock)
			return DyeIota((world.getBlockEntity(position) as SignBlockEntity).textColor)
		return getDyeFromBlock(world.getBlockState(position).block)
	}
	private fun getDyeFromBlock(block: Block): Iota {
		val dye = DyeData.getDye(block) ?: return NullIota()
		return DyeIota(dye)
	}
}