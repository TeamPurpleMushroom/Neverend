package net.purplemushroom.neverend.content.items.gear;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.purplemushroom.neverend.content.items.INESpecialAbilityItem;
import net.purplemushroom.neverend.content.items.NEItemAbility;

public class NESword extends SwordItem implements INESpecialAbilityItem {
    private final NEItemAbility ability;
    public NESword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties, NEItemAbility ability) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.ability = ability;
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        ability.onDroppedTick(stack, entity);
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    public NEItemAbility getAbility() {
        return ability;
    }
}
