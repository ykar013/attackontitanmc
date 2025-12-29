package com.ykaro.attackontitan;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, AttackOnTitan.MODID);

    // Registro da entidade do Titã
    public static final DeferredHolder<EntityType<?>, EntityType<PureTitanEntity>> PURE_TITAN =
            ENTITIES.register("pure_titan",
                    () -> EntityType.Builder.of(PureTitanEntity::new, MobCategory.MONSTER)
                            .sized(1.2f, 10.5f) // Tamanho do Titã (Largura, Altura)
                            .build("pure_titan"));
}