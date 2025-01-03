package miyucomics.hexical.casting.patterns.wristpocket

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import miyucomics.hexical.casting.mishaps.InedibleWristpocketMishap
import miyucomics.hexical.state.PersistentStateHandler
import net.minecraft.item.ItemStack
import net.minecraft.item.Items

class OpIngest : SpellAction {
	override val argc = 0
	override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val stack = PersistentStateHandler.getWristpocketStack(ctx.caster)
		if (stack.isOf(Items.POTION) || stack.isOf(Items.HONEY_BOTTLE) || stack.isOf(Items.MILK_BUCKET) || stack.item.isFood)
			return Triple(Spell(stack), MediaConstants.DUST_UNIT, listOf())
		throw InedibleWristpocketMishap()
	}

	private data class Spell(val stack: ItemStack) : RenderedSpell {
		override fun cast(ctx: CastingContext) {
			val originalItem = ctx.caster.getStackInHand(ctx.castingHand)
			ctx.caster.setStackInHand(ctx.castingHand, stack)
			val newStack = stack.finishUsing(ctx.world, ctx.caster)
			PersistentStateHandler.setWristpocketStack(ctx.caster, newStack)
			ctx.caster.setStackInHand(ctx.castingHand, originalItem)
		}
	}
}