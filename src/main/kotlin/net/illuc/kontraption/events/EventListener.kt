package net.illuc.kontraption.events

import net.illuc.kontraption.blockEntities.TileEntityKey
import net.illuc.kontraption.ship.KontraptionKeyBlockControl
import net.illuc.kontraption.util.toBlockPos
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.SubscribeEvent

object EventListener {
    @SubscribeEvent
    fun onKeyEvent(event: KeyBindEvent) {
        val level = event.player.level()
        val klist = KontraptionKeyBlockControl.getOrCreate(event.ship).getKeystones()
        klist.forEach { (position, keybind, be) ->
            val blockEntity = level.getBlockEntity(position.toBlockPos())
            if (blockEntity is TileEntityKey) {
                blockEntity.fire(event)
            }
        }
    }

    fun register() {
        MinecraftForge.EVENT_BUS.register(this)
    }
}
