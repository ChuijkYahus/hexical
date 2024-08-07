package miyucomics.hexical.casting.patterns.getters

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getLivingEntityButNotArmorStand
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.NullIota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import miyucomics.hexical.casting.iota.getIdentifier
import net.minecraft.util.registry.Registry

class OpGetStatusEffectInstanceData(private val mode: Int) : ConstMediaAction {
	override val argc = 2
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val entity = args.getLivingEntityButNotArmorStand(0, argc)
		ctx.assertEntityInRange(entity)
		val effect = args.getIdentifier(1, argc)
		if (!Registry.STATUS_EFFECT.containsId(effect))
			throw MishapInvalidIota.of(args[1], 1, "status_effect")
		val instance = entity.getStatusEffect(Registry.STATUS_EFFECT.get(effect)) ?: return listOf(NullIota())
		return when (mode) {
			0 -> instance.amplifier.asActionResult
			1 -> (instance.duration.toDouble() / 20.0).asActionResult
			else -> throw IllegalStateException()
		}
	}
}