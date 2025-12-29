package com.ykaro.attackontitan;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Holder;
import org.jetbrains.annotations.Nullable;

public class ODMGearItem extends ArmorItem {

    public ODMGearItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        // Corrigido para retornar ResourceLocation e usar o seu caminho de textura
        return ResourceLocation.fromNamespaceAndPath("attackontitan", "textures/models/armor/odm_gear_layer.png");
    }
}