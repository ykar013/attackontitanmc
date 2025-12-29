package com.ykaro.attackontitan;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PureTitanModel extends GeoModel<PureTitanEntity> {
    @Override
    public ResourceLocation getModelResource(PureTitanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AttackOnTitan.MODID, "geo/pure_titan.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PureTitanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AttackOnTitan.MODID, "textures/entity/pure_titan.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PureTitanEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AttackOnTitan.MODID, "animations/pure_titan.animation.json");
    }
}