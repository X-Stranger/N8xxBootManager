package by.linux.n8xx.bootmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for content for user interfaces.
 * <p>
 */
public class ItemList {

    /**
     * An array of UI list items.
     */
    public static List<Item> ITEMS = new ArrayList<Item>();

    /**
     * A map of sample UI list items, by ID.
     */
    public static Map<String, Item> ITEM_MAP = new HashMap<String, Item>();

    static {
        addItem(new Item("boot", ItemListActivity.getAppContext().getString(R.string.normal_boot)));
        addItem(new Item("recovery", ItemListActivity.getAppContext().getString(R.string.recovery_boot)));
        addItem(new Item("settings", ItemListActivity.getAppContext().getString(R.string.settings)));
    }

    private static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Item {
        public String id;
        public String content;

        public Item(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
