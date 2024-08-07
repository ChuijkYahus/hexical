package miyucomics.hexical.casting.patterns.identifier

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.EntityIota
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import miyucomics.hexical.casting.iota.asActionResult
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.decoration.ItemFrameEntity
import net.minecraft.util.registry.Registry

class OpRecognize : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
		return when (val arg = args[0]) {
			is EntityIota -> {
				when (arg.entity) {
					is ItemEntity -> Registry.ITEM.getId((arg.entity as ItemEntity).stack.item).asActionResult()
					is ItemFrameEntity -> Registry.ITEM.getId((arg.entity as ItemFrameEntity).heldItemStack.item).asActionResult()
					else -> throw MishapInvalidIota.of(arg, 0, "recognizable")
				}
			}
			else -> throw MishapInvalidIota.of(arg, 0, "recognizable")
		}
	}
}