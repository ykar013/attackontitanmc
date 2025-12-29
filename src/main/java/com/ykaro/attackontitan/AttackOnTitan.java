package com.ykaro.attackontitan;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(AttackOnTitan.MODID)
public class AttackOnTitan {
    public static final String MODID = "attackontitan";

    public AttackOnTitan(IEventBus modEventBus) {
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);

        modEventBus.addListener(this::registerAttributes);
        modEventBus.addListener(this::clientSetup);
    }

    private void registerAttributes(EntityAttributeCreationEvent event) {
        // O erro sumiu porque PureTitanEntity.createAttributes() agora existe e é público
        event.put(ModEntities.PURE_TITAN.get(), PureTitanEntity.createAttributes().build());
    }

    private void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.PURE_TITAN.get(), PureTitanRenderer::new);
    }
}