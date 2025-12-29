package com.ykaro.attackontitan;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AttackOnTitan.MODID);

    // ODM GEAR
    public static final Supplier<Item> ODM_GEAR = ITEMS.register("odm_gear",
            () -> new ODMGearItem(ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));

    // CABO (HANDLE)
    public static final Supplier<Item> ODM_HANDLE = ITEMS.register("odm_handle",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // REFILL (MUNIÇÃO)
    public static final Supplier<Item> REFILLBLADES = ITEMS.register("refillblades",
            () -> new Item(new Item.Properties().stacksTo(16)));

    // AQUI ESTÁ A MUDANÇA: Usamos "UltraHardSteelBladeItem" em vez de "SwordItem"
    public static final Supplier<Item> ULTRAHARD_STEEL_BLADE = ITEMS.register("ultrahard_steel_blade",
            () -> new UltraHardSteelBladeItem());
}