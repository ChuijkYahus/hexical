package miyucomics.hexical.registry

import miyucomics.hexical.state.EvokeState
import miyucomics.hexical.state.KeybindData
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents

object HexicalEvents {
	@JvmStatic
	fun init() {
		ServerTickEvents.END_SERVER_TICK.register {
			for (player in EvokeState.active.keys)
				if (EvokeState.active[player]!!)
					EvokeState.duration[player] = EvokeState.duration[player]!! + 1
			for (player in KeybindData.duration.keys) {
				val binds = KeybindData.active[player]!!
				for (key in binds.keys)
					if (KeybindData.active[player]!!.getOrDefault(key, false))
						KeybindData.duration[player]!![key] = KeybindData.duration[player]!![key]!! + 1
			}
		}
	}
}