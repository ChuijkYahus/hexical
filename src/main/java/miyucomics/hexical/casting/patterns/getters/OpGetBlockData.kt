package miyucomics.hexical.casting.patterns.getters

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.DoubleIota
import at.petrak.hexcasting.api.spell.iota.Iota

class OpGetBlockData(private val mode: Int) : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val position = args.getBlockPos(0, argc)
		ctx.assertVecInRange(position)
		val block = ctx.world.getBlockState(position).block
		return listOf(
			when (mode) {
				0 -> DoubleIota(block.hardness.toDouble())
				1 -> DoubleIota(block.blastResistance.toDouble())
				else -> throw IllegalStateException()
			}
		)
	}
}