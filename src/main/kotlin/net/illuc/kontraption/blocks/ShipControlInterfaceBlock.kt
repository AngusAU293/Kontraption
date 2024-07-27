package net.illuc.kontraption.blocks

import mekanism.common.block.prefab.BlockTile
import mekanism.common.content.blocktype.BlockTypeTile
import net.illuc.kontraption.blockEntities.TileEntityShipControlInterface
import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.BlockHitResult

class ShipControlInterfaceBlock(type: BlockTypeTile<TileEntityShipControlInterface?>?) : BlockTile<TileEntityShipControlInterface?, BlockTypeTile<TileEntityShipControlInterface?>?>(
    type,
    BlockBehaviour.Properties.of(),
) {
    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        blockHitResult: BlockHitResult,
    ): InteractionResult {
        if (level.isClientSide) return InteractionResult.SUCCESS
        val blockEntity = level.getBlockEntity(pos) as TileEntityShipControlInterface

        return if (blockEntity.sit(player)) {
            InteractionResult.CONSUME
        } else {
            InteractionResult.PASS
        }
    }

    override fun onPlace(
        state: BlockState,
        world: Level,
        pos: BlockPos,
        oldState: BlockState,
        isMoving: Boolean,
    ) {
        val be = world.getBlockEntity(pos) as TileEntityShipControlInterface
        be.enable()
        super.onPlace(state, world, pos, oldState, isMoving)
    }

    override fun <T : BlockEntity?> getTicker(
        level: Level,
        state: BlockState,
        type: BlockEntityType<T>,
    ): BlockEntityTicker<T> =
        BlockEntityTicker { level, pos, state, blockEntity ->
            if (level.isClientSide) return@BlockEntityTicker
            if (blockEntity is TileEntityShipControlInterface) {
                blockEntity.tick()
            }
        }
}
