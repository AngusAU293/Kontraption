package net.illuc.kontraption;

import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import net.illuc.kontraption.blockEntities.*;

public class KontraptionTileEntityTypes {
    private KontraptionTileEntityTypes() {
    }

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(Kontraption.MODID);

    public static final TileEntityTypeRegistryObject<TileEntityIonThruster> ION_THRUSTER = TILE_ENTITY_TYPES.register(KontraptionBlocks.ION_THRUSTER, TileEntityIonThruster::new);
    public static final TileEntityTypeRegistryObject<TileEntityShipControlInterface> SHIP_CONTROL_INTERFACE = TILE_ENTITY_TYPES.register(KontraptionBlocks.SHIP_CONTROL_INTERFACE, TileEntityShipControlInterface::new);
    public static final TileEntityTypeRegistryObject<TileEntityGyro> GYRO = TILE_ENTITY_TYPES.register(KontraptionBlocks.GYRO, TileEntityGyro::new);
    public static final TileEntityTypeRegistryObject<TileEntityWheel> WHEEL = TILE_ENTITY_TYPES.register(KontraptionBlocks.WHEEL, TileEntityWheel::new);
    public static final TileEntityTypeRegistryObject<TileEntityLiquidFuelThrusterCasing> LIQUID_FUEL_THRUSTER_CASING = TILE_ENTITY_TYPES.register(KontraptionBlocks.LIQUID_FUEL_THRUSTER_CASING, TileEntityLiquidFuelThrusterCasing::new);
    public static final TileEntityTypeRegistryObject<TileEntityLiquidFuelThrusterValve> LIQUID_FUEL_THRUSTER_VALVE = TILE_ENTITY_TYPES.register(KontraptionBlocks.LIQUID_FUEL_THRUSTER_VALVE, TileEntityLiquidFuelThrusterValve::new);
    public static final TileEntityTypeRegistryObject<TileEntityLiquidFuelThrusterExhaust> LIQUID_FUEL_THRUSTER_EXHAUST = TILE_ENTITY_TYPES.register(KontraptionBlocks.LIQUID_FUEL_THRUSTER_EXHAUST, TileEntityLiquidFuelThrusterExhaust::new);

    public static final TileEntityTypeRegistryObject<TileEntityCannon> CANNON = TILE_ENTITY_TYPES.register(KontraptionBlocks.CANNON, TileEntityCannon::new);

    public static final TileEntityTypeRegistryObject<TileEntityServo> SERVO = TILE_ENTITY_TYPES.register(KontraptionBlocks.SERVO, TileEntityServo::new);

    public static final TileEntityTypeRegistryObject<TileEntityRailgunCasing> RAILGUN_CASING = TILE_ENTITY_TYPES.register(KontraptionBlocks.RAILGUN_CASING, TileEntityRailgunCasing::new);
    public static final TileEntityTypeRegistryObject<TileEntityRailgunCoil> RAILGUN_COIL = TILE_ENTITY_TYPES.register(KontraptionBlocks.RAILGUN_COIL, TileEntityRailgunCoil::new);
    public static final TileEntityTypeRegistryObject<TileEntityRailgunController> RAILGUN_CONTROLLER = TILE_ENTITY_TYPES.register(KontraptionBlocks.RAILGUN_CONTROLLER, TileEntityRailgunController::new);
    public static final TileEntityTypeRegistryObject<TileEntityRailgunPort> RAILGUN_PORT = TILE_ENTITY_TYPES.register(KontraptionBlocks.RAILGUN_PORT, TileEntityRailgunPort::new);
    public static final TileEntityTypeRegistryObject<TileEntityConnector> CONNECTOR = TILE_ENTITY_TYPES.register(KontraptionBlocks.CONNECTOR, TileEntityConnector::new);

}
