package miyucomics.hexical.casting.patterns

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapOthersName
import at.petrak.hexcasting.api.utils.putCompound
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import miyucomics.hexical.registry.HexicalItems
import net.minecraft.entity.ItemEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3d

class OpConjureHexburst : SpellAction {
	override val argc = 2
	override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val position = args.getVec3(0, argc)
		ctx.assertVecInRange(position)
		val iota = args[1]
		val trueName = MishapOthersName.getTrueNameFromDatum(args[1], ctx.caster)
		if (trueName != null)
			throw MishapOthersName(trueName)
		return Triple(Spell(position, iota), MediaConstants.DUST_UNIT, listOf(ParticleSpray.burst(position, 1.0)))
	}

	private data class Spell(val position: Vec3d, val iota: Iota) : RenderedSpell {
		override fun cast(ctx: CastingContext) {
			val stack = ItemStack(HexicalItems.HEXBURST_ITEM, 1)
			stack.orCreateNbt.putCompound("iota", HexIotaTypes.serialize(iota))
			ctx.world.spawnEntity(ItemEntity(ctx.world, position.x, position.y, position.z, stack))
		}
	}
}