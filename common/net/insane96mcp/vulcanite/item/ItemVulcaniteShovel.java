package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ItemVulcaniteShovel extends ItemSpade{
	public ItemVulcaniteShovel(String name, ToolMaterial material, CreativeTabs tab) {
		super(material);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + net.insane96mcp.vulcanite.lib.Names.VULCANITE_SHOVEL;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {

	}
}
