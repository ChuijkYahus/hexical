package miyucomics.hexical.casting.patterns

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getVec3
import at.petrak.hexcasting.api.spell.iota.Iota
import miyucomics.hexical.entities.MagicMissileEntity
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d

class OpMagicMissile : SpellAction {
	override val argc = 2
	override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val headOffset = args.getVec3(0, argc)
		val straightAxis = ctx.caster.rotationVector

		val upPitch = (-ctx.caster.pitch + 90) * (Math.PI.toFloat() / 180)
		val yaw = -ctx.caster.headYaw * (Math.PI.toFloat() / 180)
		val h = MathHelper.cos(yaw).toDouble()
		val j = MathHelper.cos(upPitch).toDouble()
		val upAxis = Vec3d(
			MathHelper.sin(yaw).toDouble() * j,
			MathHelper.sin(upPitch).toDouble(),
			h * j
		)

		val sideAxis = straightAxis.crossProduct(upAxis).normalize()
		val worldCoords = ctx.caster.eyePos
			.add(sideAxis.multiply(headOffset.x))
			.add(upAxis.multiply(headOffset.y))
			.add(straightAxis.multiply(headOffset.z))
		ctx.assertVecInRange(worldCoords)

		val velocity = args.getVec3(1, argc)
		return Triple(Spell(worldCoords, velocity), MediaConstants.DUST_UNIT, listOf())
	}

	private data class Spell(val position: Vec3d, val velocity: Vec3d) : RenderedSpell {
		override fun cast(ctx: CastingContext) {
			val missile = MagicMissileEntity(ctx.world)
			missile.setPos(position.x, position.y, position.z)
			missile.owner = ctx.caster
			ctx.world.spawnEntity(missile)
			missile.tick()
			missile.setVelocity(velocity.x, velocity.y, velocity.z)
		}
	}
}