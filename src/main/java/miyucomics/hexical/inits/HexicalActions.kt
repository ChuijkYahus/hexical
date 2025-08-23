package miyucomics.hexical.inits

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.iota.IotaType
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.utils.vecFromNBT
import at.petrak.hexcasting.common.casting.actions.selectors.OpGetEntitiesBy
import at.petrak.hexcasting.common.lib.hex.HexActions
import miyucomics.hexical.HexicalMain
import miyucomics.hexical.features.akashic.OpClearAkashicShelf
import miyucomics.hexical.features.akashic.OpKeyAkashicShelf
import miyucomics.hexical.features.akashic.OpReadAkashicShelf
import miyucomics.hexical.features.akashic.OpWriteAkashicShelf
import miyucomics.hexical.features.animated_scrolls.OpAlterScroll
import miyucomics.hexical.features.animated_scrolls.OpColorScroll
import miyucomics.hexical.features.autographs.OpAutograph
import miyucomics.hexical.features.autographs.OpHasAutograph
import miyucomics.hexical.features.autographs.OpUnautograph
import miyucomics.hexical.features.block_mimicry.OpCook
import miyucomics.hexical.features.block_mimicry.OpDispense
import miyucomics.hexical.features.block_mimicry.OpStonecut
import miyucomics.hexical.features.breaking.OpBreakFortune
import miyucomics.hexical.features.breaking.OpBreakSilk
import miyucomics.hexical.features.charms.*
import miyucomics.hexical.features.circle.OpAbsorbArm
import miyucomics.hexical.features.circle.OpCreateDust
import miyucomics.hexical.features.circle.OpDisplace
import miyucomics.hexical.features.confection.OpConjureGummy
import miyucomics.hexical.features.confection.OpConjureHexburst
import miyucomics.hexical.features.confection.OpConjureHextito
import miyucomics.hexical.features.confetti.OpConfetti
import miyucomics.hexical.features.conjure.OpConjureEntity
import miyucomics.hexical.features.conjure.OpConjureFlower
import miyucomics.hexical.features.conjure.OpConjureLight
import miyucomics.hexical.features.conjure.OpConjureSpike
import miyucomics.hexical.features.dyes.OpDye
import miyucomics.hexical.features.dyes.OpGetDye
import miyucomics.hexical.features.dyes.OpTranslateDye
import miyucomics.hexical.features.evocation.OpGetEvocation
import miyucomics.hexical.features.evocation.OpSetEvocation
import miyucomics.hexical.features.grimoires.OpGrimoireErase
import miyucomics.hexical.features.grimoires.OpGrimoireIndex
import miyucomics.hexical.features.grimoires.OpGrimoireWrite
import miyucomics.hexical.features.grok.OpGrokPull
import miyucomics.hexical.features.grok.OpGrokPush
import miyucomics.hexical.features.hopper.OpHopper
import miyucomics.hexical.features.hopper.OpIndexHopper
import miyucomics.hexical.features.hotbar.OpGetHotbar
import miyucomics.hexical.features.hotbar.OpSetHotbar
import miyucomics.hexical.features.jailbreak.OpJailbreakDevice
import miyucomics.hexical.features.lamps.*
import miyucomics.hexical.features.lesser_sentinels.OpLesserSentinelGet
import miyucomics.hexical.features.lesser_sentinels.OpLesserSentinelSet
import miyucomics.hexical.features.lore.OpItemLore
import miyucomics.hexical.features.lore.OpItemName
import miyucomics.hexical.features.mage_blocks.OpConfigureMageBlock
import miyucomics.hexical.features.mage_blocks.OpConjureMageBlock
import miyucomics.hexical.features.mage_blocks.OpResetMageBlock
import miyucomics.hexical.features.mage_blocks.modifiers.BouncyModifier
import miyucomics.hexical.features.mage_blocks.modifiers.LifespanModifier
import miyucomics.hexical.features.mage_blocks.modifiers.RedstoneModifier
import miyucomics.hexical.features.mage_blocks.modifiers.VolatileModifier
import miyucomics.hexical.features.magic_missile.OpMagicMissile
import miyucomics.hexical.features.misc_actions.*
import miyucomics.hexical.features.pattern_manipulation.*
import miyucomics.hexical.features.periwinkle.OpCompelSniffer
import miyucomics.hexical.features.pigments.OpSamplePigment
import miyucomics.hexical.features.pigments.OpTakeOnPigment
import miyucomics.hexical.features.pigments.OpToPigment
import miyucomics.hexical.features.prestidigitation.OpPrestidigitation
import miyucomics.hexical.features.pyrotechnics.OpConjureFirework
import miyucomics.hexical.features.pyrotechnics.OpSimulateFirework
import miyucomics.hexical.features.rotate.OpRotateBlock
import miyucomics.hexical.features.rotate.OpRotateEntity
import miyucomics.hexical.features.shaders.OpShader
import miyucomics.hexical.features.sparkle.OpSparkle
import miyucomics.hexical.features.specklikes.OpKillSpecklike
import miyucomics.hexical.features.specklikes.OpSpecklikeProperty
import miyucomics.hexical.features.specklikes.Specklike
import miyucomics.hexical.features.specklikes.mesh.OpConjureMesh
import miyucomics.hexical.features.specklikes.mesh.OpReadMesh
import miyucomics.hexical.features.specklikes.mesh.OpWeaveMesh
import miyucomics.hexical.features.specklikes.speck.OpConjureSpeck
import miyucomics.hexical.features.specklikes.speck.OpIotaSpeck
import miyucomics.hexical.features.telepathy.*
import miyucomics.hexical.features.wristpocket.*
import net.minecraft.entity.EntityType
import net.minecraft.entity.projectile.FireballEntity
import net.minecraft.entity.projectile.LlamaSpitEntity
import net.minecraft.entity.projectile.thrown.EggEntity
import net.minecraft.entity.projectile.thrown.SnowballEntity
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvents

object HexicalActions {
	fun init() {
		register("normalize_scroll", "wqwawqwqawawa", HexDir.SOUTH_WEST, OpAlterScroll { it.setState(0) })
		register("age_scroll", "wqwawqwqawwwdwdwwwa", HexDir.SOUTH_WEST, OpAlterScroll { it.setState(1) })
		register("vanish_scroll", "wqwawqwqaqqa", HexDir.SOUTH_WEST, OpAlterScroll { it.setState(2) })
		register("color_scroll", "wqwawqwqawawaedd", HexDir.SOUTH_WEST, OpColorScroll)
		register("glow_scroll", "wqwawqwqawawaewdwdw", HexDir.SOUTH_WEST, OpAlterScroll { it.toggleGlow() })

		register("write_grimoire", "aqwqaeaqa", HexDir.WEST, OpGrimoireWrite)
		register("erase_grimoire", "aqwqaqded", HexDir.WEST, OpGrimoireErase)
		register("index_grimoire", "aqaeaqwqa", HexDir.SOUTH_EAST, OpGrimoireIndex)

		register("periwinkle", "wwwaqdadaadadqqqeaeq", HexDir.EAST, OpCompelSniffer)

		register("wish", "eweweweweweewedeaqqqd", HexDir.NORTH_WEST, OpWish)
		register("recharge_lamp", "qaqwawqwqqwqwqwqwqwqq", HexDir.EAST, OpRechargeLamp)
		register("promote_lamp", "qweedeqeedeqdqdwewewwewewwewe", HexDir.WEST, OpPromoteLamp)
		register("get_hand_lamp_position", "qwddedqdd", HexDir.SOUTH_WEST, OpGetHandLampData { _, nbt -> vecFromNBT(nbt.getCompound("position")).asActionResult })
		register("get_hand_lamp_rotation", "qwddedadw", HexDir.SOUTH_WEST, OpGetHandLampData { _, nbt -> vecFromNBT(nbt.getCompound("rotation")).asActionResult })
		register("get_hand_lamp_velocity", "qwddedqew", HexDir.SOUTH_WEST, OpGetHandLampData { _, nbt -> vecFromNBT(nbt.getCompound("velocity")).asActionResult })
		register("get_hand_lamp_use_time", "qwddedqwddwa", HexDir.SOUTH_WEST, OpGetHandLampData { env, nbt -> (env.world.time - (nbt.getDouble("start_time") + 1.0)).asActionResult })
		register("get_hand_lamp_media", "qwddedaeeeee", HexDir.SOUTH_WEST, OpGetHandLampData { env, _ -> ((env.castingEntity!!.activeItem.item as HandLampItem).getMedia(env.castingEntity!!.activeItem).toDouble() / MediaConstants.DUST_UNIT).asActionResult })
		register("get_hand_lamp_storage", "qwddedqwaqqqqq", HexDir.SOUTH_WEST, OpGetHandLampData { env, nbt -> listOf(IotaType.deserialize(nbt.getCompound("storage"), env.world)) })
		register("set_hand_lamp_storage", "qwddedqedeeeee", HexDir.SOUTH_WEST, OpSetHandLampStorage)
		register("get_arch_lamp_position", "qaqwddedqdd", HexDir.NORTH_EAST, OpGetArchLampData { _, data -> data.position.asActionResult })
		register("get_arch_lamp_rotation", "qaqwddedadw", HexDir.NORTH_EAST, OpGetArchLampData { _, data -> data.rotation.asActionResult })
		register("get_arch_lamp_velocity", "qaqwddedqew", HexDir.NORTH_EAST, OpGetArchLampData { _, data -> data.velocity.asActionResult })
		register("get_arch_lamp_use_time", "qaqwddedqwddwa", HexDir.NORTH_EAST, OpGetArchLampData { ctx, data -> (ctx.world.time - (data.time + 1)).asActionResult })
		register("get_arch_lamp_storage", "qaqwddedqwaqqqqq", HexDir.NORTH_EAST, OpGetArchLampData { ctx, data -> listOf(IotaType.deserialize(data.storage, ctx.world)) })
		register("set_arch_lamp_storage", "qaqwddedqedeeeee", HexDir.NORTH_EAST, OpSetArchLampStorage)
		register("get_arch_lamp_media", "qaqwddedaeeeee", HexDir.NORTH_EAST, OpGetArchLampMedia)
		register("has_arch_lamp", "qaqwddedqeed", HexDir.NORTH_EAST, OpIsUsingArchLamp)
		register("lamp_finale", "aaddaddad", HexDir.EAST, OpGetFinale)

		register("rotate_block", "edeeeeeweewadeeed", HexDir.EAST, OpRotateBlock)
		register("rotate_entity", "qqqdaqqqa", HexDir.EAST, OpRotateEntity)

		register("shuffle_pattern", "aqqqdae", HexDir.NORTH_EAST, OpShufflePattern)
		register("congruent", "aaqd", HexDir.EAST, OpCongruentPattern)
		register("similar", "aedd", HexDir.EAST, OpSimilarPattern)
		register("serialize_pattern", "wqaedeqd", HexDir.EAST, OpSerializePattern)
		register("deserialize_pattern", "wqqqaqwd", HexDir.EAST, OpDeserializePattern)
		register("draw_pattern", "eadqqqa", HexDir.NORTH_EAST, OpDrawPattern)

		register("grok_pull", "aqawwqaw", HexDir.EAST, OpGrokPull)
		register("grok_push", "ewdewwde", HexDir.EAST, OpGrokPush)

		register("get_telepathy", "wqqadaw", HexDir.EAST, OpGetKeybind("key.hexical.telepathy"))
		register("send_telepathy", "qqqqwaqa", HexDir.EAST, OpSendTelepathy)
		register("shout_telepathy", "daqqqqwa", HexDir.EAST, OpShoutTelepathy)
		register("pling", "eqqqada", HexDir.NORTH_EAST, OpHallucinateSound(Registries.SOUND_EVENT.getEntry(SoundEvents.ENTITY_PLAYER_LEVELUP)))
		register("click", "eqqadaq", HexDir.NORTH_EAST, OpHallucinateSound(Registries.SOUND_EVENT.getEntry(SoundEvents.UI_BUTTON_CLICK.value())))
		register("left_click", "qadee", HexDir.NORTH_EAST, OpGetKeybind("key.attack"))
		register("right_click", "edaqq", HexDir.NORTH_WEST, OpGetKeybind("key.use"))
		register("moving_up", "aqaddq", HexDir.SOUTH_EAST, OpGetKeybind("key.forward"))
		register("moving_down", "dedwdq", HexDir.SOUTH_WEST, OpGetKeybind("key.back"))
		register("moving_left", "edead", HexDir.SOUTH_EAST, OpGetKeybind("key.left"))
		register("moving_right", "qaqda", HexDir.SOUTH_WEST, OpGetKeybind("key.right"))
		register("jumping", "qaqdaqqa", HexDir.SOUTH_WEST, OpGetKeybind("key.jump"))
		register("sneaking", "wede", HexDir.NORTH_WEST, OpGetKeybind("key.sneak"))
		register("scroll", "qadeeee", HexDir.NORTH_EAST, OpGetScroll)

		register("key_shelf", "qaqqadaq", HexDir.EAST, OpKeyAkashicShelf)
		register("read_shelf", "qaqqqada", HexDir.EAST, OpReadAkashicShelf)
		register("write_shelf", "edeeedad", HexDir.SOUTH_WEST, OpWriteAkashicShelf)
		register("clear_shelf", "edeedade", HexDir.SOUTH_WEST, OpClearAkashicShelf)

		register("conjure_mage_block", "dee", HexDir.NORTH_WEST, OpConjureMageBlock)
		register("reset_mage_block", "deeaw", HexDir.NORTH_WEST, OpResetMageBlock)
		register("modify_block_bouncy", "deeqa", HexDir.NORTH_WEST, OpConfigureMageBlock(BouncyModifier.TYPE))
		register("modify_block_ephemeral", "deewwaawd", HexDir.NORTH_WEST, OpConfigureMageBlock(LifespanModifier.TYPE))
		register("modify_block_energized", "deewad", HexDir.NORTH_WEST, OpConfigureMageBlock(RedstoneModifier.TYPE))
		register("modify_block_volatile", "deewedeeeee", HexDir.NORTH_WEST, OpConfigureMageBlock(VolatileModifier.TYPE))

		register("autograph", "eeeeeww", HexDir.WEST, OpAutograph)
		register("unautograph", "wwqqqqq", HexDir.NORTH_EAST, OpUnautograph)
		register("has_autograph", "wwqqqqqaw", HexDir.NORTH_EAST, OpHasAutograph)

		register("get_dye", "weedwa", HexDir.NORTH_EAST, OpGetDye)
		register("dye", "dwaqqw", HexDir.NORTH_WEST, OpDye)
		register("translate_dye", "wdwwaawwewdwwewwdwwe", HexDir.EAST, OpTranslateDye)

		register("magic_missile", "qaqww", HexDir.WEST, OpMagicMissile)

		register("to_pigment", "aqwedeweeeewweeew", HexDir.NORTH_WEST, OpToPigment)
		register("sample_pigment", "edewqaqqqqqwqqq", HexDir.SOUTH_EAST, OpSamplePigment)
		register("take_on_pigment", "weeeweeqeeeewqaqweeee", HexDir.EAST, OpTakeOnPigment)

		register("prestidigitation", "wedewedew", HexDir.NORTH_EAST, OpPrestidigitation)

		register("wristpocket", "aaqqa", HexDir.WEST, OpWristpocket)
		register("wristpocket_item", "aaqqada", HexDir.WEST, OpGetWristpocket)
		register("sleight", "aaqqadeeeq", HexDir.WEST, OpSleight)
		register("mage_hand", "aaqqaeea", HexDir.WEST, OpMageHand)
		register("mage_mouth", "aaqqadaa", HexDir.WEST, OpMageMouth)

		register("conjure_speck", "ade", HexDir.SOUTH_WEST, OpConjureSpeck)
		register("iota_speck", "adeeaqa", HexDir.SOUTH_WEST, OpIotaSpeck)
		register("kill_specklike", "adeaqde", HexDir.SOUTH_WEST, OpKillSpecklike)
		register("move_specklike", "adeqaa", HexDir.SOUTH_WEST, OpSpecklikeProperty(0))
		register("rotate_specklike", "adeaw", HexDir.SOUTH_WEST, OpSpecklikeProperty(1))
		register("roll_specklike", "adeqqqqq", HexDir.SOUTH_WEST, OpSpecklikeProperty(2))
		register("size_specklike", "adeeqed", HexDir.SOUTH_WEST, OpSpecklikeProperty(3))
		register("thickness_specklike", "adeeqw", HexDir.SOUTH_WEST, OpSpecklikeProperty(4))
		register("lifetime_specklike", "adeqqaawdd", HexDir.SOUTH_WEST, OpSpecklikeProperty(5))
		register("pigment_specklike", "adeqqaq", HexDir.SOUTH_WEST, OpSpecklikeProperty(6))
		register("zone_specklike", "qqqqqwdeddwqde", HexDir.SOUTH_EAST, OpGetEntitiesBy({ entity -> entity is Specklike }, false))

		register("egg", "qqqwaqaaqeeewdedde", HexDir.SOUTH_EAST, OpConjureEntity(MediaConstants.DUST_UNIT * 2) { world, position, caster ->
			val egg = EggEntity(world, position.x, position.y, position.z)
			egg.owner = caster
			return@OpConjureEntity egg
		})
		register("llama_spit", "dwqaqw", HexDir.EAST, OpConjureEntity(MediaConstants.DUST_UNIT / 4) { world, position, caster ->
			val spit = LlamaSpitEntity(EntityType.LLAMA_SPIT, world)
			spit.setPosition(position)
			spit.owner = caster
			return@OpConjureEntity spit
		})
		register("snowball", "ddeeeeewd", HexDir.NORTH_EAST, OpConjureEntity(MediaConstants.DUST_UNIT / 2) { world, position, caster ->
			val snowball = SnowballEntity(world, position.x, position.y, position.z)
			snowball.owner = caster
			return@OpConjureEntity snowball
		})
		register("ghast_fireball", "wqqqqqwaeaeaeaeae", HexDir.SOUTH_EAST, OpConjureEntity(MediaConstants.DUST_UNIT * 3) { world, position, caster ->
			val fireball = FireballEntity(world, caster, 0.0, 0.0, 0.0, 1)
			fireball.setPosition(position)
			return@OpConjureEntity fireball
		})

		register("confetti", "awddeqaedd", HexDir.EAST, OpConfetti)
		register("vibration", "wwawawwd", HexDir.EAST, OpVibrate)
		register("sparkle", "dqa", HexDir.NORTH_EAST, OpSparkle)
		register("jailbreak", "wwaqqqqqeqdedwqeaeqwdedwqeaeq", HexDir.EAST, OpJailbreakDevice)
		register("flower", "weqqqqqwaeaeaeaeaea", HexDir.NORTH_EAST, OpConjureFlower)
		register("light", "aeaeaeaeaeawqqqqq", HexDir.SOUTH_EAST, OpConjureLight)
		register("gasp", "aweeeeewaweeeee", HexDir.NORTH_WEST, OpGasp)
		register("parrot", "wweedadw", HexDir.NORTH_EAST, OpImitateParrot)

		register("break_fortune", "qaqqqqqdeeeqeee", HexDir.EAST, OpBreakFortune)
		register("break_silk", "aqaeaqdeeweweedq", HexDir.EAST, OpBreakSilk)

		register("conjure_gummy", "eeewdw", HexDir.SOUTH_WEST, OpConjureGummy)
		register("conjure_hexburst", "aadaadqaq", HexDir.EAST, OpConjureHexburst)
		register("conjure_hextito", "qaqdqaqdwawaw", HexDir.EAST, OpConjureHextito)

		register("spike", "qdqdqdqdww", HexDir.NORTH_EAST, OpConjureSpike)

		register("dispense", "wqwawqwddaeeead", HexDir.SOUTH_WEST, OpDispense)
		register("smelt", "qwqqadadadewewewe", HexDir.SOUTH_EAST, OpCook(RecipeType.SMELTING, "target.smelting"))
		register("roast", "aqqwwqqawdadedad", HexDir.NORTH_WEST, OpCook(RecipeType.CAMPFIRE_COOKING, "target.roasting"))
		register("smoke", "qwqqadadadewdqqdwe", HexDir.SOUTH_EAST, OpCook(RecipeType.SMOKING, "target.smoking"))
		register("blast", "qwqqadadadewweewwe", HexDir.SOUTH_EAST, OpCook(RecipeType.BLASTING, "target.blasting"))
		register("stonecut", "qqqqqwaeaeaeaeaeadawa", HexDir.EAST, OpStonecut)

		register("displace", "qaqqqqeedaqqqa", HexDir.NORTH_EAST, OpDisplace)
		register("absorb_arm", "aaqqadaqwqa", HexDir.WEST, OpAbsorbArm)
		register("create_dust", "eaqwedqdqddqqwae", HexDir.SOUTH_WEST, OpCreateDust)

		register("get_evocation", "wwdeeeeeqeaqawwewewwaqawwewew", HexDir.EAST, OpGetEvocation)
		register("set_evocation", "wwaqqqqqeqdedwwqwqwwdedwwqwqw", HexDir.EAST, OpSetEvocation)
		register("is_evoking", "wwaqqqqqeeaqawwewewwaqawwewew", HexDir.EAST, OpGetKeybind("key.hexical.evoke"))

		register("conjure_firework", "dedwaqwwawwqa", HexDir.SOUTH_WEST, OpConjureFirework)
		register("simulate_firework", "dedwaqwqqwqa", HexDir.SOUTH_WEST, OpSimulateFirework)

		register("get_hotbar", "qwawqwa", HexDir.EAST, OpGetHotbar)
		register("set_hotbar", "dwewdwe", HexDir.WEST, OpSetHotbar)

		register("set_lesser_sentinels", "aeaae", HexDir.EAST, OpLesserSentinelSet)
		register("get_lesser_sentinels", "dqddq", HexDir.WEST, OpLesserSentinelGet)

		register("item_name", "qwawqwaadwa", HexDir.SOUTH_EAST, OpItemName)
		register("item_lore", "dwewdweedwa", HexDir.NORTH_WEST, OpItemLore)

		register("shader_clear", "eeeeeqaqeeeee", HexDir.WEST, OpShader(null))
		register("shader_owl", "edewawede", HexDir.WEST, OpShader(HexicalMain.id("shaders/post/night_vision.json")))
		register("shader_lines", "eedwwawwdee", HexDir.WEST, OpShader(HexicalMain.id("shaders/post/outlines_only.json")))
		register("shader_tv", "wewdwewwawwewdwew", HexDir.WEST, OpShader(HexicalMain.id("shaders/post/television.json")))
		register("shader_media", "eewdweqaqewdwee", HexDir.WEST, OpShader(HexicalMain.id("shaders/post/media.json")))
		register("shader_spider", "qaqdedaedqqdedaqaedeqd", HexDir.NORTH_EAST, OpShader(HexicalMain.id("shaders/post/spider.json")))
		// color shift - edqdeqaqedqde

		register("hopper", "qwawqwaeqqq", HexDir.SOUTH_EAST, OpHopper)
		register("index_hopper", "qqqeawqwawq", HexDir.SOUTH_WEST, OpIndexHopper)

		register("horrible", "wedqawqeewdeaqeewdeaqqedqawqqedqawqeedqawqqewdeaqeedqawqeewdeaqqewdeaqeewdeaqeedqawqqedqawqqewdeaqeedqawqeewdeaqqewdeaqeewdeaqeedqawqqedqawqqewdeaqqedqawqeewdeaqeewdeaqqedqawqqedqawqeedqawqqewdeaqqedqawqeewdeaqeewdeaqqedqawqqedqawqeedqawqqewdeaqeedqawqeewdeaqeewdeaqqedqawqqedqawqeedqawqqewdeaqqedqawqeewdeaqqewdeaqeewdeaqeedqawqqedqawqqewdeaqe", HexDir.EAST, OpHorrible)

		register("charm", "edeeeeeqaaqeeeadweeqeeqdqeeqeeqde", HexDir.SOUTH_EAST, OpCharmItem)
		register("write_charmed", "waqqqqqedeqdqdqdqdqe", HexDir.NORTH_EAST, OpWriteCharmed)
		register("read_charmed", "waqqqqqeaqeaeaeaeaeq", HexDir.NORTH_EAST, OpReadCharmed)
		register("write_charmed_proxy", "edewqaqqdeeeee", HexDir.SOUTH_EAST, OpProxyWriteCharmed)
		register("read_charmed_proxy", "qaqwedeeaqqqqq", HexDir.NORTH_EAST, OpProxyReadCharmed)
		register("discharm", "qaqwddaaeawaea", HexDir.NORTH_EAST, OpDischarmItem)

		register("greater_blink", "wqawawaqwqwqawawaqw", HexDir.SOUTH_WEST, OpGreaterBlink)

		register("conjure_mesh", "qaqqqqqwqqqdeeweweeaeewewee", HexDir.EAST, OpConjureMesh)
		register("weave_mesh", "qaqqqqqwqqqdeewewee", HexDir.EAST, OpWeaveMesh)
		register("read_mesh", "edeeeeeweeeaqqwqwqq", HexDir.SOUTH_WEST, OpReadMesh)
	}

	private fun register(name: String, signature: String, startDir: HexDir, action: Action) =
		Registry.register(HexActions.REGISTRY, HexicalMain.id(name), ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), action))
}
