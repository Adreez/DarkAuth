package sk.adr3ez.darkauth.shared.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(final Material itemType) {
        item = new ItemStack(itemType);
    }
    public ItemBuilder(final ItemStack itemStack) {
        item = itemStack;
    }
    public ItemBuilder() {
        item = new ItemStack(Material.AIR);
    }

    public ItemBuilder type(final Material material) {
        item.setType(material);
        return this;
    }

    public ItemBuilder setName(final String name) {
        Objects.requireNonNull(item.getItemMeta()).setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(final ArrayList<String> lore) {
        Objects.requireNonNull(item.getItemMeta()).setLore(lore);
        return this;
    }

    public ItemBuilder customModelData(final int modelData) {
        Objects.requireNonNull(item.getItemMeta()).setCustomModelData(modelData);
        return this;
    }

    public ItemBuilder setItemMeta(final ItemMeta itemMeta) {
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack make() {
        return item;
    }

}
