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

public class UltraHardSteelBladeItem extends SwordItem {

    // 1. DEFINIÇÃO DO MATERIAL (Ajustado para 1.21.1)
    private static final Tier TITAN_TIER = new Tier() {
        @Override
        public int getUses() { return 10; }

        @Override
        public float getSpeed() { return 8.0f; }

        @Override
        public float getAttackDamageBonus() { return 46.0f; }

        // O 'getLevel' não existe mais. Usamos a Tag abaixo para definir nível Diamante.
        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.INCORRECT_FOR_DIAMOND_TOOL;
        }

        @Override
        public int getEnchantmentValue() { return 10; }

        @Override
        public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    };

    // 2. CONSTRUTOR
    public UltraHardSteelBladeItem() {
        super(TITAN_TIER, new Item.Properties().durability(10));
    }

    // 3. LÓGICA DE ATAQUE E QUEBRA (Método atualizado da 1.21.1)
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Gasta durabilidade (1 ponto por hit)
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);

        // Se a durabilidade acabar (chegar em 10 de dano ou stack ficar vazio)
        if (stack.getDamageValue() >= stack.getMaxDamage() || stack.isEmpty()) {
            if (attacker instanceof Player player) {
                // Troca a lâmina pelo cabo (ODM_HANDLE)
                player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.ODM_HANDLE.get()));
            }
        }
        return true;
    }
}