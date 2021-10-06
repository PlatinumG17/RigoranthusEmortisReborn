package com.platinumg17.rigoranthusemortisreborn.core.registry;

import com.platinumg17.rigoranthusemortisreborn.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RigoranthusItemGroup {
	
	public static final ItemGroup RIGORANTHUS_EMORTIS_GROUP = new ItemGroup("rigoranthusemortistab")
	{
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.PACT_OF_SERVITUDE.get());
		}
	};
}
