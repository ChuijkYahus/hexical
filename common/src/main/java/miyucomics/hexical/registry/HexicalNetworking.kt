package miyucomics.hexical.registry

import at.petrak.hexcasting.api.spell.iota.BooleanIota
import at.petrak.hexcasting.api.spell.iota.Iota
import dev.architectury.networking.NetworkManager
import miyucomics.hexical.Hexical
import miyucomics.hexical.items.ConjuredStaffItem

object HexicalNetworking {
	@JvmStatic
	fun init() {
		NetworkManager.registerReceiver(NetworkManager.Side.C2S, Hexical.CAST_CONJURED_STAFF_PACKET) { buf, context ->
			val player = context.player
			val itemInHand = player.mainHandStack
			if (itemInHand.item is ConjuredStaffItem) {
				val constructedStack: MutableList<Iota> = ArrayList()
				val staffRank = buf.readInt()
				for (i in 0 until staffRank)
					constructedStack.add(BooleanIota(buf.readBoolean()))
				(itemInHand.item as ConjuredStaffItem).cast(context.player.world, player, itemInHand, constructedStack)
			}
		}
	}
}