package miyucomics.hexical.registry

import at.petrak.hexcasting.api.PatternRegistry
import at.petrak.hexcasting.api.spell.Action
import at.petrak.hexcasting.api.spell.math.HexDir
import at.petrak.hexcasting.api.spell.math.HexPattern
import miyucomics.hexical.Hexical
import miyucomics.hexical.casting.patterns.operators.eval.OpNephthys
import miyucomics.hexical.casting.patterns.operators.lamp.OpGetLampData
import miyucomics.hexical.casting.patterns.spells.OpChorusBlink
import miyucomics.hexical.casting.patterns.spells.OpConjureBlock
import miyucomics.hexical.casting.patterns.spells.OpPing
import miyucomics.hexical.casting.patterns.spells.OpProgramLamp
import net.minecraft.util.Identifier

object HexicalPatterns {
	private var PATTERNS: MutableList<Triple<HexPattern, Identifier, Action>> = ArrayList()
	private var PER_WORLD_PATTERNS: MutableList<Triple<HexPattern, Identifier, Action>> = ArrayList()

	var NEPHTHYS_GAMBIT = register(HexPattern.fromAngles("deaqqdq", HexDir.SOUTH_EAST), "nephthys", OpNephthys);

	var CONJURE_BOUNCY_BLOCK: HexPattern = register(HexPattern.fromAngles("dee", HexDir.NORTH_WEST), "conjure_bouncy_block", OpConjureBlock(HexicalBlocks.CONJURED_BOUNCY_BLOCK.defaultState))
	var CONJURE_SLIPPERY_BLOCK: HexPattern = register(HexPattern.fromAngles("deea", HexDir.NORTH_WEST), "conjure_slippery_block", OpConjureBlock(HexicalBlocks.CONJURED_SLIPPERY_BLOCK.defaultState))

	var CHORUS_BLINK: HexPattern = register(HexPattern.fromAngles("aawqqqq", HexDir.SOUTH_EAST), "chorus_blink", OpChorusBlink())
	var PROGRAM_LAMP: HexPattern = register(HexPattern.fromAngles("wwqqqqq", HexDir.EAST), "program_lamp", OpProgramLamp())
	var PING: HexPattern = register(HexPattern.fromAngles("eweeewedqdeqqqqqwaeeee", HexDir.NORTH_EAST), "ping", OpPing())

	var ROD_LOOK: HexPattern = register(HexPattern.fromAngles("qwddeda", HexDir.SOUTH_WEST), "get_lamp_start_position", OpGetLampData(0))
	var ROD_POS: HexPattern = register(HexPattern.fromAngles("qwddedq", HexDir.SOUTH_WEST), "get_lamp_start_rotation", OpGetLampData(1))
	var ROD_STAMP: HexPattern = register(HexPattern.fromAngles("qwddedw", HexDir.SOUTH_WEST), "get_lamp_use_time", OpGetLampData(2))

	@JvmStatic
	fun init() {
		try {
			for ((first, second, third) in PATTERNS)
				PatternRegistry.mapPattern(first, second, third)
			for ((first, second, third) in PER_WORLD_PATTERNS)
				PatternRegistry.mapPattern(first, second, third, true)
		} catch (e: PatternRegistry.RegisterPatternException) {
			e.printStackTrace()
		}
	}

	private fun register(pattern: HexPattern, name: String, action: Action): HexPattern {
		val triple = Triple(pattern, Hexical.id(name), action)
		PATTERNS.add(triple)
		return pattern
	}

	private fun registerPerWorld(pattern: HexPattern, name: String, action: Action): HexPattern {
		val triple = Triple(pattern, Hexical.id(name), action)
		PER_WORLD_PATTERNS.add(triple)
		return pattern
	}
}