package net.illuc.kontraption;

import it.zerono.mods.zerocore.lib.block.ModBlock;
import it.zerono.mods.zerocore.lib.item.ModItem;
import net.illuc.kontraption.multiblocks.largeionring.IIonRingPartType;
import net.illuc.kontraption.multiblocks.largeionring.LargeIonRingMultiBlock;
import net.illuc.kontraption.multiblocks.largeionring.parts.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

public final class GlobalRegistry {
    public static void EventInit(final IEventBus bus) {
        Blocks.init(bus);
        Items.init(bus);
        TileEntities.init(bus);

    }
    @SuppressWarnings({"rawtypes", "unchecked"})

    public static final class Blocks {
        private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Kontraption.MODID);

        //region Block DRO
        private static <T extends LargeIonMultiblockPartBlockTemplate<LargeIonRingMultiBlock, IIonRingPartType>>
        RegistryObject<T> registerIonMultiBBlock(final String name,
                                                 final IonRingPartTypes partType) {
            return BLOCKS.register(name, () -> (T) (partType.createBlock()));
        }

        //endregion

        //REGISTRATION STUFF

        static void init(final IEventBus bus){
            BLOCKS.register(bus);
        }
        public static Collection<RegistryObject<Block>> getAll(){
            return BLOCKS.getEntries();
        }
        public static final RegistryObject<LargeIonMultiblockPartBlockTemplate> LARGE_ION_THRUSTER_CASING = registerIonMultiBBlock("large_ion_thruster_casing", IonRingPartTypes.Casing);
        public static final RegistryObject<LargeIonMultiblockPartBlockTemplate> LARGE_ION_THRUSTER_COIL = registerIonMultiBBlock("large_ion_thruster_coil", IonRingPartTypes.Coil);
        public static final RegistryObject<LargeIonMultiblockPartBlockTemplate> LARGE_ION_THRUSTER_VALVE = registerIonMultiBBlock("large_ion_thruster_valve", IonRingPartTypes.PowerPortRF);
        public static final RegistryObject<LargeIonMultiblockPartBlockTemplate> LARGE_ION_THRUSTER_CONTROLLER = registerIonMultiBBlock("large_ion_thruster_controller", IonRingPartTypes.Controller);


    }
    public static final class Items {
        private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Kontraption.MODID);
        //region Items DRO
        private static RegistryObject<ModItem> registerItemGeneric(final String name, final int maxStack) {
            return ITEMS.register(name,
                    () -> new ModItem(new Item.Properties().stacksTo(maxStack)));
        }

        private static RegistryObject<BlockItem> registerItemBlock(final String name,
                                                                   final Supplier<Supplier<ModBlock>> blockSupplier) {
            return ITEMS.register(name,
                    () -> blockSupplier.get().get().createBlockItem(new Item.Properties().stacksTo(64)));
        }
        //endregion

        //REGISTRATION STUFF
        static void init(final IEventBus bus){
            ITEMS.register(bus);
        }
        public static final RegistryObject<BlockItem> LARGE_ION_THRUSTER_CASING = registerItemBlock("large_ion_thruster_casing", () -> Blocks.LARGE_ION_THRUSTER_CASING::get);
        public static final RegistryObject<BlockItem> LARGE_ION_THRUSTER_COIL = registerItemBlock("large_ion_thruster_coil", () -> Blocks.LARGE_ION_THRUSTER_COIL::get);
        public static final RegistryObject<BlockItem> LARGE_ION_THRUSTER_VALVE = registerItemBlock("large_ion_thruster_valve", () -> Blocks.LARGE_ION_THRUSTER_VALVE::get);
        public static final RegistryObject<BlockItem> LARGE_ION_THRUSTER_CONTROLLER = registerItemBlock("large_ion_thruster_controller", () -> Blocks.LARGE_ION_THRUSTER_CONTROLLER::get);
    }

    public static final class TileEntities {
        private static final DeferredRegister<BlockEntityType<?>> TILEENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Kontraption.MODID);
        //PREDEFS
        //region BlockEnity DRO
        @SafeVarargs
        @SuppressWarnings("ConstantConditions")
        private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> registerBlockEntity(
                final String name,
                final BlockEntityType.BlockEntitySupplier<T> factory,
                final Supplier<Supplier<Block>>... validBlockSuppliers) {

            return TILEENTITIES.register(name, () -> {
                Block[] validBlocks = Arrays.stream(validBlockSuppliers)
                        .map(Supplier::get)
                        .map(Supplier::get)
                        .toArray(Block[]::new);

                return BlockEntityType.Builder.of(factory, validBlocks).build(null);
            });
        }
        //endregion
        //REGISTRATION STUFF
        static void init(IEventBus bus){
            TILEENTITIES.register(bus);
        }
        public static final RegistryObject<BlockEntityType<LargeIonRingCasingEntity>> LARGE_ION_THRUSTER_CASING =
                registerBlockEntity("large_ion_thruster_casing", LargeIonRingCasingEntity::new, () -> Blocks.LARGE_ION_THRUSTER_CASING::get);
        public static final RegistryObject<BlockEntityType<LargeIonRingCoilEntity>> LARGE_ION_THRUSTER_COIL =
                registerBlockEntity("large_ion_thruster_coil", LargeIonRingCoilEntity::new, () -> Blocks.LARGE_ION_THRUSTER_COIL::get);
        public static final RegistryObject<BlockEntityType<LargeIonRingPowerPortEntity>> LARGE_ION_THRUSTER_VALVE =
                registerBlockEntity("large_ion_thruster_valve", LargeIonRingPowerPortEntity::new, () -> Blocks.LARGE_ION_THRUSTER_VALVE::get);
        public static final RegistryObject<BlockEntityType<LargeIonRingController>> LARGE_ION_THRUSTER_CONTROLLER =
                registerBlockEntity("large_ion_thruster_controller", LargeIonRingController::new, () -> Blocks.LARGE_ION_THRUSTER_CONTROLLER::get);
    }

}
