package miyucomics.hexical.casting.patterns.mage_blocks

import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapBadBlock
import miyucomics.hexical.blocks.MageBlock
import miyucomics.hexical.blocks.MageBlockEntity
import miyucomics.hexical.inits.HexicalAdvancements
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

class OpModifyMageBlock(private val property: String, arguments: Int = 0) : SpellAction {
	override val argc = arguments + 1
	override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val pos = args.getBlockPos(0, argc)
		ctx.assertVecInRange(pos)
		if (ctx.world.getBlockState(pos).block !is MageBlock)
			throw MishapBadBlock.of(pos, "mage_block")
		if (property == "energized")
			args.getPositiveIntUnder(1, 16, argc)
		if (property == "ephemeral")
			args.getPositiveInt(1, argc)
		return Triple(Spell(pos, property, args.subList(1, args.size).toList()), 0, listOf(ParticleSpray.cloud(Vec3d.ofCenter(pos), 1.0)))
	}

	private data class Spell(val pos: BlockPos, val property: String, val args: List<Iota>) : RenderedSpell {
		override fun cast(ctx: CastingContext) {
			(ctx.world.getBlockEntity(pos) as MageBlockEntity).setProperty(property, args, ctx.caster)
			HexicalAdvancements.DIY.trigger(ctx.caster)
		}
	}
}