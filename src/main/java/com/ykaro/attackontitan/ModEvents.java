package com.ykaro.attackontitan;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

@EventBusSubscriber(modid = AttackOnTitan.MODID)
public class ModEvents {

    // CONTAGEM DE ATAQUES: Gasta durabilidade apenas ao causar dano
    @SubscribeEvent
    public static void onAttack(LivingDamageEvent.Post event) {
        // Verifica se quem atacou foi um Player
        if (event.getSource().getEntity() instanceof Player player) {
            ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);

            // Se o item for a lâmina
            if (mainHand.getItem() == ModItems.ULTRAHARD_STEEL_BLADE.get()) {
                int currentDamage = mainHand.getDamageValue();
                int maxDamage = mainHand.getMaxDamage();

                // Se ainda não chegou no 10º ataque, aumenta o dano do item
                if (currentDamage < maxDamage - 1) {
                    mainHand.setDamageValue(currentDamage + 1);
                } else {
                    // No 10º ataque, substitui pelo cabo
                    player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.ODM_HANDLE.get()));
                }
            }
        }
    }

    // RECARGA: Botão direito com RefillBlades para voltar a ter a lâmina
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack itemStack = event.getItemStack();

        if (itemStack.getItem() == ModItems.ODM_HANDLE.get()) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack invStack = player.getInventory().getItem(i);

                // Se achar o item de munição
                if (invStack.getItem() == ModItems.REFILLBLADES.get()) {
                    invStack.shrink(1); // Consome 1 refill
                    player.setItemInHand(event.getHand(), new ItemStack(ModItems.ULTRAHARD_STEEL_BLADE.get()));
                    event.setCanceled(true);
                    break;
                }
            }
        }
    }
}