package com.ykaro.attackontitan;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, AttackOnTitan.MODID);

    // ODM GEAR
    public static final Supplier<Item> ODM_GEAR = ITEMS.register("odm_gear",
            () -> new ODMGearItem(ArmorMaterials.IRON, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));

    // CABO VAZIO (HANDLE)
    public static final Supplier<Item> ODM_HANDLE = ITEMS.register("odm_handle",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // REFILL (MUNIÇÃO)
    public static final Supplier<Item> REFILLBLADES = ITEMS.register("refillblades",
            () -> new Item(new Item.Properties().stacksTo(16)));

    // LÂMINA COMPLETA
    public static final Supplier<Item> ULTRAHARD_STEEL_BLADE = ITEMS.register("ultrahard_steel_blade",
            () -> new SwordItem(Tiers.DIAMOND, new Item.Properties()
                    .durability(10) // Define durabilidade máxima como 10
                    .attributes(SwordItem.createAttributes(Tiers.DIAMOND, 46.0f, -2.4f))));
    // 46 + 4 base do player = 50 de dano total
}