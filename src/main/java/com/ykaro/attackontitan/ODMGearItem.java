package com.ykaro.attackontitan;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.core.Holder;

public class ODMGearItem extends ArmorItem {

    public ODMGearItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    // Remova o @Override que estava aqui.
    // O erro acontece porque esse método não existe mais nessa classe nas versões novas.
}