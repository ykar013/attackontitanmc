package com.ykaro.attackontitan;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PureTitanEntity extends Monster implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public PureTitanEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    // Método que resolve o erro vermelho no AttackOnTitan.java
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            // Só aceita dano da ULTRAHARD_STEEL_BLADE
            if (player.getMainHandItem().getItem() != ModItems.ULTRAHARD_STEEL_BLADE.get() && !player.isCreative()) {
                return false;
            }

            // Lógica da Nuca (Altura e Costas)
            boolean naNuca = player.getY() > this.getY() + (this.getBbHeight() * 0.75);
            Vec3 look = this.getViewVector(1.0F);
            Vec3 playerPos = player.position().subtract(this.position()).normalize();
            boolean atras = look.dot(playerPos) < -0.2;

            if (naNuca && atras) {
                return super.hurt(source, amount);
            }
        }
        return false;
    }

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
}