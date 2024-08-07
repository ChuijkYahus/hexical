package miyucomics.hexical.casting.patterns

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import miyucomics.hexical.registry.HexicalAdvancements
import miyucomics.hexical.state.PersistentStateHandler
import net.minecraft.util.Identifier

class OpShader(private val shader: Identifier?) : SpellAction {
	override val argc = 0
	override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> = Triple(Spell(shader), MediaConstants.DUST_UNIT / 10, listOf())

	private data class Spell(val shader: Identifier?) : RenderedSpell {
		override fun cast(ctx: CastingContext) {
			PersistentStateHandler.setShader(ctx.caster, shader)
			HexicalAdvancements.SHADER.trigger(ctx.caster)
		}
	}
}