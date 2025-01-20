package miyucomics.hexical.casting.patterns.lamp

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.NullIota
import miyucomics.hexical.casting.env.TurretLampCastEnv
import miyucomics.hexical.casting.mishaps.NeedsArchGenieLamp
import miyucomics.hexical.items.hasActiveArchLamp
import miyucomics.hexical.state.ArchLampData
import miyucomics.hexical.state.PersistentStateHandler
import net.minecraft.server.network.ServerPlayerEntity

class OpGetArchLampData(private val process: (CastingEnvironment, ArchLampData) -> List<Iota>) : ConstMediaAction {
	override val argc = 0
	override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
		if (env is TurretLampCastEnv)
			return process(env, PersistentStateHandler.getArchLampData(env.lamp))

		val caster = env.castingEntity
		if (caster !is ServerPlayerEntity)
			return listOf(NullIota())
		if (!hasActiveArchLamp(caster))
			throw NeedsArchGenieLamp()
		return process(env, PersistentStateHandler.getArchLampData(caster))
	}
}