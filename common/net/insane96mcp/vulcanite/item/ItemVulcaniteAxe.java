package net.insane96mcp.vulcanite.item;

import java.util.List;

import net.insane96mcp.vulcanite.Vulcanite;
import net.insane96mcp.vulcanite.lib.Names;
import net.insane96mcp.vulcanite.lib.Stats;
import net.insane96mcp.vulcanite.lib.Tooltips;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class ItemVulcaniteAxe extends ItemAxe{
	public ItemVulcaniteAxe(String name, ToolMaterial material, CreativeTabs tab) {
		super(material, 7f, -3.1f);
		setRegistryName(name);
		setCreativeTab(tab);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Vulcanite.RESOURCE_PREFIX + Names.VULCANITE_AXE;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		
	}
}
