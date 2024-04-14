package miyucomics.hexical.casting.operators.getters

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.iota.*
import at.petrak.hexcasting.api.spell.mishaps.MishapBadItem
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import miyucomics.hexical.iota.IdentifierIota
import miyucomics.hexical.iota.getItemStack
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.FoodComponent
import net.minecraft.util.registry.Registry

class OpGetFoodData(private val mode: Int) : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		val stack = args.getItemStack(0, argc)
		if (!stack.isFood)
			throw MishapInvalidIota.of(args[0], 0, "food")
		return listOf(
			when (mode) {
				0 -> DoubleIota(stack.item.foodComponent!!.hunger.toDouble())
				1 -> DoubleIota(stack.item.foodComponent!!.saturationModifier.toDouble())
				2 -> BooleanIota(stack.item.foodComponent!!.isMeat)
				3 -> BooleanIota(stack.item.foodComponent!!.isSnack)
				else -> NullIota()
			}
		)
	}
}