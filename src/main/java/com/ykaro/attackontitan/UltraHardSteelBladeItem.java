package com.ykaro.attackontitan;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class UltraHardSteelBladeItem extends SwordItem {

    private static final Tier TITAN_TIER = new Tier() {
        @Override public int getUses() { return 20; }
        @Override public float getSpeed() { return 8.0f; }
        @Override public float getAttackDamageBonus() { return 49.0f; } // Deixamos aqui por segurança
        @Override public TagKey<Block> getIncorrectBlocksForDrops() { return BlockTags.INCORRECT_FOR_DIAMOND_TOOL; }
        @Override public int getEnchantmentValue() { return 10; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    };

    public UltraHardSteelBladeItem() {
        // Criamos as propriedades forçando o dano de 50 e velocidade de ataque
        super(TITAN_TIER, new Item.Properties()
                .durability(20)
                .attributes(createAttributes()));
    }

    // MÉTODO QUE FORÇA O DANO DE 50 NA 1.21.1
    private static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_damage"),
                        49.0, // 49 bônus + 1 base do player = 50 total
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                        ResourceLocation.withDefaultNamespace("base_attack_speed"),
                        -2.4, // Velocidade padrão de espada
                        AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);

        // Troca pelo cabo antes de sumir
        if (stack.getDamageValue() >= (stack.getMaxDamage() - 1)) {
            if (attacker instanceof Player player) {
                player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.ODM_HANDLE.get()));
            }
        }
        return true;
    }
}