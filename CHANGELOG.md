# Changelog

## 1.3.0
- added hexbursts - adds an iota to your stack when eaten
- added hextitos - casts a hex using the player stack when eaten
- added dye iota
    - added Chromatic Purification and Dye spell to get and set dyes of blocks and entities
    - added data-driven process to add color associations to blocks so you can add support for mods with simple datapacks
- added evocation - enlightened players can cast spells by holding a keybind for some time
- added internal iota storage to handheld lamp and patterns to interact with it
- added Similarity Distillation - get if two iota are of the same type
- added Congruence Distillation - get if two patterns are the same, regardless of orientation
- added Charge, Dodge, Retreat, and Evade Reflection patterns - pushes how long you've pressed the wasd keys
- added Conjure Compass spell - conjure a compass that always points towards a location
- added Conjure Firework spell - conjures a firework with a vast number of options and possibilities
- added Simulate Firework spell - conjures a firework based off the firework star in your offhand
- added Magic Missile spell - fire silver of amethyst that deals knockback and weak damage
- added Greater Blink spell - blink with a relative positional and rotational offset for much cheaper than GTP
- added Prestidigitation spell - dozens of small magical effect on blocks and entities
  - added data-driven process to add prestidigitation associations
  - exposed registry for prestidigitation effects, allowing you to make an addon for custom magical effects
- added Wristpocket spell - hide items magically
  - added Ingest spell - eat wristpocketed item
  - added Mage Hand spell - use your wristpocketed item to interact with blocks and entities
- added mishap to Chorus Blink if you don't have chorus fruit in your inventory
- added semi-permeable modifier for : only sprinting creatures have collision
- added semi-permeable mage block modifier: only sprinting creatures have collision
- changed Identify pattern to have ambit limitation
- dropped support for Forge in order to prioritize development speed and features
- fixed specks being slightly off-center
- fixed advancements occasionally breaking
- fixed Conjure Speck spell costing too little
- fixed Janus' Gambit to actually terminate the hex now
- fixed bug with archgenie lamps where it can cast for free if it wasn't in your main inventory
- fixed bug with conjured staff where inputs aren't always neatly intercepted and you can accidentally break blocks
- fixed Finale Reflection that always returned null
- overhauled project structure completely
- overhauled genie lamps completely and entirely
- removed casting sounds from conjured staves and replaced it with staff drawing sounds
- updated documentation
- updated telepathy code to share code with movement reflections

## 1.2.0
- added more achievements
- added lightning rod staff, with strong knockback and slow swing speed
- added Displace spell for circles to teleport entities for cheap
- added energized mage blocks modifier to emit Redstone power
- added living scrolls
- added proper speck text rendering
- added more meta-evals
    - added Dioscuri's Gambit
    - added Janus' Gambit
    - added Sisyphus' Gambit
- added z-axis rotation for specks
- added iota storage for conjured staves
- added Dioscuri Gambit II
- changed Conjure Speck to push the speck to the stack
- changed Nephthys' Gambit to no longer need a number, instead relying on tail length
- fixed accidental swapping of Sloth and Racer's Purification
- fixed being able to use Recharge Item to recharge lamps
- fixed conjured staves not casting properly
- fixed mage block breaking particles
- fixed Nephthys' Gambit not working on single patterns
- fixed speck pattern saving
- remove ambit requirement for altering specks
- updated documentation
- overhauled a lot of code
- overhauled world scrying
    - added enchantment patterns
    - added entity patterns
    - added food patterns
    - added identifier patterns
    - added item patterns
    - added status effect patterns
    - added world patterns

## 1.1.0
- added identifier iota
- added `zh_cn` translation
- added mishap to grimoire patterns if you are not holding a grimoire in your offhand
- changed creative inventory lamps to have media by default
- changed Conjure Staff to take in dust rather than media for battery
- fixed telepathy crashing on servers

## 1.0.0
- Initial release, let's go!