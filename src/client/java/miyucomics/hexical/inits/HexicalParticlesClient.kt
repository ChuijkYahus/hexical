package miyucomics.hexical.inits

import miyucomics.hexical.features.confetti.ConfettiParticle
import miyucomics.hexical.features.sparkle.SparkleParticle
import miyucomics.hexical.features.zap.ZapParticle
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry

object HexicalParticlesClient {
	fun clientInit() {
		ParticleFactoryRegistry.getInstance().register(HexicalParticles.CONFETTI_PARTICLE, ConfettiParticle::Factory)
		ParticleFactoryRegistry.getInstance().register(HexicalParticles.SPARKLE_PARTICLE, SparkleParticle::Factory)
		ParticleFactoryRegistry.getInstance().register(HexicalParticles.ZAP_PARTICLE, ZapParticle::Factory)
	}
}