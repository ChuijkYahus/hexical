package miyucomics.hexical.casting.patterns.identifier

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.Vec3Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapInvalidIota
import miyucomics.hexical.casting.iota.asActionResult
import net.minecraft.registry.Registries
import net.minecraft.util.math.BlockPos

class OpIdentify : ConstMediaAction {
	override val argc = 1
	override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
		return when (val arg = args[0]) {
			is EntityIota -> Registries.ENTITY_TYPE.getId(args.getEntity(0, argc).type).asActionResult()
			is Vec3Iota -> {
				env.assertVecInRange(arg.vec3)
				Registries.BLOCK.getId(env.world.getBlockState(BlockPos(arg.vec3)).block).asActionResult()
			}
			else -> throw MishapInvalidIota.of(arg, 0, "identifiable")
		}
	}
}