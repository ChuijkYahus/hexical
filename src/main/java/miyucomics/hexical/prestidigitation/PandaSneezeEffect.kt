package miyucomics.hexical.prestidigitation

import miyucomics.hexical.interfaces.PrestidigitationEffect
import net.minecraft.entity.Entity
import net.minecraft.entity.passive.PandaEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.math.BlockPos

class PandaSneezeEffect : PrestidigitationEffect {
	override fun effectBlock(caster: ServerPlayerEntity, position: BlockPos) {}

	override fun effectEntity(caster: ServerPlayerEntity, entity: Entity) {
		if (entity is PandaEntity)
			entity.isSneezing = true
	}
}