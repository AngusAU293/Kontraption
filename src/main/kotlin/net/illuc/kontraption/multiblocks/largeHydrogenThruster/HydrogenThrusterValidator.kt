package net.illuc.kontraption.multiblocks.largeHydrogenThruster

import it.unimi.dsi.fastutil.longs.Long2ObjectMap
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
import mekanism.common.content.blocktype.BlockType
import mekanism.common.lib.math.voxel.VoxelCuboid
import mekanism.common.lib.multiblock.CuboidStructureValidator
import mekanism.common.lib.multiblock.FormationProtocol
import mekanism.common.lib.multiblock.FormationProtocol.CasingType
import mekanism.common.lib.multiblock.FormationProtocol.FormationResult
import mekanism.common.tile.TileEntityPressureDisperser
import mekanism.common.util.WorldUtils
import net.illuc.kontraption.KontraptionBlockTypes
import net.illuc.kontraption.KontraptionLang
import net.illuc.kontraption.blockEntities.TileEntityHydrogenThrusterExhaust
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.chunk.ChunkAccess
import net.minecraft.world.phys.Vec3


class HydrogenThrusterValidator : CuboidStructureValidator<HydrogenThrusterMultiblockData>(VoxelCuboid(3, 3, 3), VoxelCuboid(17, 18, 17)) {





    override fun getCasingType(state: BlockState?): FormationProtocol.CasingType {
        val block: Block = state!!.block
        if (BlockType.`is`(block, KontraptionBlockTypes.HYDROGEN_THRUSTER_CASING)) {
            return CasingType.FRAME
        } else if (BlockType.`is`(block, KontraptionBlockTypes.HYDROGEN_THRUSTER_VALVE)) {
            return CasingType.VALVE
        } else if (BlockType.`is`(block, KontraptionBlockTypes.HYDROGEN_THRUSTER_EXHAUST)) {
            return CasingType.OTHER
        }
        return CasingType.INVALID

    }

    override fun postcheck(structure: HydrogenThrusterMultiblockData?, chunkMap: Long2ObjectMap<ChunkAccess?>?): FormationResult? {
        if (structure!!.length() % 2 !== 1 || structure!!.width() % 2 !== 1) {
            return FormationResult.fail(KontraptionLang.DESCRIPTION_ION_THRUSTER)
        }
        val centerX = structure!!.minPos.x + (structure!!.length() - 1) / 2
        val centerY = structure!!.minPos.x + (structure!!.height() - 1) / 2
        val centerZ = structure!!.minPos.z + (structure!!.width() - 1) / 2

        val exhausts: Set<BlockPos> = ObjectOpenHashSet()


        //Get Direction
        var centerExhaust :TileEntityHydrogenThrusterExhaust


        //:sob:
        val mutablePos = MutableBlockPos()
        mutablePos.set(centerX, structure.maxPos.y, centerZ);
        var tile = WorldUtils.getTileEntity(TileEntityHydrogenThrusterExhaust::class.java, world, chunkMap!!, mutablePos)
        if (tile == null){
            println("boowomp :(")
            return FormationResult.fail(KontraptionLang.DESCRIPTION_ION_THRUSTER, mutablePos);

        }
        println("yahhooo")
        return FormationResult.SUCCESS
    }
}