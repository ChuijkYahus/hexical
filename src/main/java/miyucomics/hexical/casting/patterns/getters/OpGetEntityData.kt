package miyucomics.hexical.casting.patterns.getters

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.iota.BooleanIota
import at.petrak.hexcasting.api.spell.iota.DoubleIota
import at.petrak.hexcasting.api.spell.iota.Iota

class OpGetEntityData(private val mode: Int) : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val entity = args.getEntity(0, argc)
		return listOf(
			when (mode) {
				0 -> BooleanIota(entity.isOnFire)
				1 -> DoubleIota(entity.fireTicks.toDouble() / 20)
				2 -> BooleanIota(entity.isWet)
				else -> throw IllegalStateException()
			}
		)
	}
}