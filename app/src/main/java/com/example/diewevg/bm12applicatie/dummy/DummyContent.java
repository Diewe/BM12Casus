package com.example.diewevg.bm12applicatie.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 7;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.cursus, item);
    }

    private static DummyItem createDummyItem(int position) {
        String cursus = new String();
        String leeractiviteit = new String();
        if (position <= 4) {
         cursus = "BM12";
        } else {
            cursus = "BM13";
        }

        if (position <= 2) {
            leeractiviteit = "Werkcollege";
        } else if (position == 3 || position == 5) {
            leeractiviteit = "Hoorcollege";
        } else {
            leeractiviteit = "Zelfstudie CiS";
        }

        return new DummyItem(cursus, leeractiviteit, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String cursus;
        public final String leeractiviteit;
        public final String details;

        public DummyItem(String cursus, String leeractiviteit, String details) {
            this.cursus = cursus;
            this.leeractiviteit = leeractiviteit;
            this.details = details;
        }

        @Override
        public String toString() {
            return leeractiviteit;
        }
    }
}
