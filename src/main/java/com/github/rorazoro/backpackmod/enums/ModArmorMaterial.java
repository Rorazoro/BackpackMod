package com.github.rorazoro.backpackmod.enums;

import java.util.function.Supplier;

import com.github.rorazoro.backpackmod.util.ModConstants;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum ModArmorMaterial implements IArmorMaterial {

    BACKPACK(ModConstants.MODID + ":backpack", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, () -> {
      return Ingredient.fromItems(Items.LEATHER);
    });
  
    private static final int[] MAX_DAMAGE_ARRAY = new int[] { 16, 16, 16, 16 };
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyValue<Ingredient> repairMaterial;
  
    private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn,
        int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
      this.name = nameIn;
      this.maxDamageFactor = maxDamageFactorIn;
      this.damageReductionAmountArray = damageReductionAmountArrayIn;
      this.enchantability = enchantabilityIn;
      this.soundEvent = soundEventIn;
      this.toughness = toughnessIn;
      this.repairMaterial = new LazyValue<Ingredient>(repairMaterialIn);
    }
  
    @Override
    public int getDurability(EquipmentSlotType slotType) {
      return MAX_DAMAGE_ARRAY[slotType.getIndex()] * this.maxDamageFactor;
    }
  
    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotType) {
      return this.damageReductionAmountArray[slotType.getIndex()];
    }
  
    @Override
    public int getEnchantability() {
      return this.enchantability;
    }
  
    @Override
    public SoundEvent getSoundEvent() {
      return this.soundEvent;
    }
  
    @Override
    public Ingredient getRepairMaterial() {
      return this.repairMaterial.getValue();
    }
  
    @Override
    public String getName() {
      return this.name;
    }
  
    @Override
    public float getToughness() {
      return this.toughness;
    }
  }