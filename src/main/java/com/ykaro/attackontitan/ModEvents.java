package com.ykaro.attackontitan;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

@EventBusSubscriber(modid = AttackOnTitan.MODID)
public class ModEvents {

    // --- SISTEMA DE QUEBRA (10 HITS) ---
    @SubscribeEvent
    public static void onAttackEntity(LivingDamageEvent.Post event) {
        // Verifica se quem atacou foi um jogador
        if (event.getSource().getEntity() instanceof Player player) {
            // Executa apenas no servidor para evitar bugs visuais
            if (player.level().isClientSide()) return;

            ItemStack maoPrincipal = player.getMainHandItem();

            // Se o item na mão for a nossa lâmina
            if (maoPrincipal.getItem() == ModItems.ULTRAHARD_STEEL_BLADE.get()) {

                // Pega o dano atual e soma +1 (simulando o desgaste)
                int danoAtual = maoPrincipal.getDamageValue();
                int danoMaximo = maoPrincipal.getMaxDamage();

                // Se chegou no limite (10), quebra e troca pelo cabo
                if (danoAtual >= danoMaximo - 1) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.ODM_HANDLE.get()));

                    // Toca um som de metal quebrando (opcional, fica legal)
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
                else {
                    // Se não quebrou, aumenta o desgaste manualmente
                    maoPrincipal.setDamageValue(danoAtual + 1);
                }
            }
        }
    }

    // --- SISTEMA DE RECARGA (REFILL) ---
    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack itemNaMao = event.getItemStack();

        // Se estiver segurando o cabo vazio (ODM HANDLE)
        if (itemNaMao.getItem() == ModItems.ODM_HANDLE.get()) {

            // Procura o refill no inventário
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemNoInventario = player.getInventory().getItem(i);

                // Se achou o "refillblades"
                if (itemNoInventario.getItem() == ModItems.REFILLBLADES.get()) {

                    if (!player.level().isClientSide()) {
                        // Remove 1 munição
                        itemNoInventario.shrink(1);

                        // Transforma o cabo em espada nova
                        player.setItemInHand(event.getHand(), new ItemStack(ModItems.ULTRAHARD_STEEL_BLADE.get()));

                        // Som de recarga (metal sendo encaixado)
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.ANVIL_USE, SoundSource.PLAYERS, 0.5F, 2.0F);
                    }

                    // Cancela a animação padrão e diz que funcionou
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }
}