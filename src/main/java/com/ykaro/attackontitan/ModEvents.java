package com.ykaro.attackontitan;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;

@EventBusSubscriber(modid = AttackOnTitan.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack itemNaMao = event.getItemStack();

        // Só roda no servidor (para realmente trocar o item)
        if (player.level().isClientSide()) return;

        // Se estiver segurando o CABO
        if (itemNaMao.getItem() == ModItems.ODM_HANDLE.get()) {

            // Procura o REFILL no inventário
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemNoInventario = player.getInventory().getItem(i);

                if (itemNoInventario.getItem() == ModItems.REFILLBLADES.get()) {

                    // 1. Remove 1 munição
                    itemNoInventario.shrink(1);

                    // 2. Coloca a ESPADA NOVA na mão (Durabilidade 10/10)
                    player.setItemInHand(event.getHand(), new ItemStack(ModItems.ULTRAHARD_STEEL_BLADE.get()));

                    // 3. Som de metal
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ANVIL_USE, SoundSource.PLAYERS, 0.5F, 2.0F);

                    // 4. Para o evento
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }
}