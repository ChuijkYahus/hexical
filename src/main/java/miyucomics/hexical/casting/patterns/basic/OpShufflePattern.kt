package miyucomics.hexical.casting.patterns.basic

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getInt
import at.petrak.hexcasting.api.spell.getPattern
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.PatternIota
import at.petrak.hexcasting.api.spell.math.EulerPathFinder

class OpShufflePattern : ConstMediaAction {
	override val argc = 2
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val pattern = args.getPattern(0, argc)
		val seed = args.getInt(1, argc)
		return listOf(PatternIota(EulerPathFinder.findAltDrawing(pattern, seed.toLong())))
	}
}