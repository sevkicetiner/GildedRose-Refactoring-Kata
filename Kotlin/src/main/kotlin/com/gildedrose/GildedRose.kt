package com.gildedrose

import kotlin.math.max

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        items.forEach { item->
            when(item.name){
                "Aged Brie"-> updateAgedBrideQuality(item)
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePassQuality(item)
                "Sulfuras, Hand of Ragnaros" -> {} // quality is not need to update in this situation
                else -> updateRegularItemQuality(item)
            }
            updateSellIn(item)
        }
    }

    private fun updateRegularItemQuality(item: Item) {
        //there are 2 rule about 'twice as fast as normal'
        if(item.name.startsWith("Conjured") || item.sellIn <= 0){
            decreaseQuality(item, 2)
        } else {
            decreaseQuality(item)
        }
    }

    private fun updateBackstagePassQuality(item: Item) {
        // there are 4 different situation for Backstage passes
        when{
            item.sellIn <= 0 -> item.quality = 0
            item.sellIn <= 5 -> increaseQuality(item, 3)
            item.sellIn <= 10 -> increaseQuality(item,2)
            else -> increaseQuality(item)
        }

    }

    private fun updateSellIn(item: Item) {
        item.sellIn--
    }

    private fun updateAgedBrideQuality(item: Item) {
        increaseQuality(item)
    }

    private fun increaseQuality(item:Item, amount:Int = 1){
        // quality can't be bigger than 50
        item.quality = minOf(item.quality + amount, 50)
    }

    private fun decreaseQuality(item:Item, amount: Int = 1){
        //quality can't be lower then 0
        item.quality = max(0,item.quality - amount)
    }
}

