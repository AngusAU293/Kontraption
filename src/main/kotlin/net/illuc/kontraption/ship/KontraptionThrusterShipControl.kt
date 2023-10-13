package net.illuc.kontraption.ship

import com.fasterxml.jackson.annotation.JsonIgnore
import net.illuc.kontraption.util.toBlockPos
import net.illuc.kontraption.util.toDouble
import net.illuc.kontraption.util.toJOML
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.player.Player
import org.joml.Vector3d
import org.joml.Vector3i
import org.valkyrienskies.core.api.ships.PhysShip
import org.valkyrienskies.core.api.ships.ServerShip
import org.valkyrienskies.core.api.ships.getAttachment
import org.valkyrienskies.core.api.ships.saveAttachment
import org.valkyrienskies.core.api.ships.ShipForcesInducer
import org.valkyrienskies.core.impl.game.ships.PhysShipImpl
import org.valkyrienskies.mod.api.SeatedControllingPlayer
import java.util.concurrent.CopyOnWriteArrayList


class KontraptionThrusterShipControl : ShipForcesInducer {

    //thank vs tournament for inspiration :heart:
    private val thrusters = CopyOnWriteArrayList<Triple<Vector3i, Vector3d, Double>>()

    override fun applyForces(physShip: PhysShip) {
        physShip as PhysShipImpl
        thrusters.forEach {
            val (pos, force, tier) = it

            val tForce = physShip.transform.shipToWorld.transformDirection(force, Vector3d())
            val tPos = pos.toDouble().add(0.5, 0.5, 0.5).sub(physShip.transform.positionInShip)

            if (force.isFinite) {
                physShip.applyInvariantForceToPos(tForce.mul(10000 * tier), tPos)
            }
        }
    }

    fun addThruster(pos: BlockPos, tier: Double, force: Vector3d) {
        thrusters.add(Triple(pos.toJOML(), force, tier))
    }

    fun controlAll(forceDirection: Vector3d, power: Double) {
        println(thrusters)
        thrusters.forEach {
            println("g" + it.second + " vs " + forceDirection)
            if (it.second == forceDirection){
                println("yoinky")
                val (pos, force, tier) = it
                removeThruster(pos.toBlockPos(), tier, force)
                
                addThruster(pos.toBlockPos(), power, force)
            }

        }
    }


    fun removeThruster(pos: BlockPos, tier: Double, force: Vector3d) {
        thrusters.remove(Triple(pos.toJOML(), force, tier))
    }

    fun stopThruster(pos: BlockPos) {
        thrusters.removeAll { it.first == pos.toJOML() }
    }

    companion object {
        fun getOrCreate(ship: ServerShip): KontraptionThrusterShipControl {
            return ship.getAttachment<KontraptionThrusterShipControl>()
                    ?: KontraptionThrusterShipControl().also { ship.saveAttachment(it) }
        }
    }

}