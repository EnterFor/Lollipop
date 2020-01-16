package owmii.lib.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeItem;

public interface IItemBase extends IForgeItem {
    @OnlyIn(Dist.CLIENT)
    default void renderByItem(ItemStack stack) {
    }

    default void oneTimeInfo(PlayerEntity player, ItemStack stack, ITextComponent component) {
        CompoundNBT p = player.getPersistentData();
        int i = player.inventory.currentItem;
        int j = p.getInt("ChatInfo");
        if (i != j && p.contains("ChatInfo")) {
            p.remove("ChatInfo");
        }
        if (!stack.equals(player.inventory.mainInventory.get(i), true))
            return;
        if (i != j) {
            player.sendStatusMessage(component, true);
            p.putInt("ChatInfo", i);
        }
    }
}
