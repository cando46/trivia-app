package com.mentalsegment.triviaapp;

import android.provider.BaseColumns;

public class ShopItemContract {
    public ShopItemContract() {
    }

    public static final class ShopItemEntry implements BaseColumns{
        public static final String TABLE_NAME="itemList";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_PRICE="price";

    }
}
