package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = listOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("fixme", app.items[0].name)

    }

    @Test
    fun `updateQuality() decreases sellIn and quality correctly for regular items` (){
        val items = listOf(Item("Regular item", 12, 22))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(11, items[0].sellIn)
        assertEquals(20, items[0].quality)
    }

    @Test
    fun `updateQuality() increases quality correctly for Aged Brie` () {
        val items = listOf(Item("Aged Brie", 7, 12))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(items[0].quality, 13)
        assertEquals(items[0].sellIn, 6)
    }

    @Test
    fun `updateQuality() set quality to 0 for expired Backstage Passed` (){
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 7))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(0, items[0].quality)
    }

    @Test
    fun `updateQuality() increases quality correctly for Backstage Passes within 5 days to concert` (){
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 4, 12))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(3, items[0].sellIn)
        assertEquals(15, items[0].quality)
    }

    @Test
    fun `updateQuality() increases quality correctly for Backstage Passes within 10 days to concert` (){
        val items = listOf(Item("Backstage passes to a TAFKAL80ETC concert", 8, 8))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(7, items[0].sellIn)
        assertEquals(10, items[0].quality)
    }

    @Test
    fun `updateQuality() decreases quality twice as fast for Conjured items`(){
        val items = listOf(Item("Conjured item", 7, 8))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(6, items[0].sellIn)
        assertEquals(6, items[0].quality)
    }

    @Test
    fun `updateQuality() decreases quality twice as fast for passed of date items`(){
        val items = listOf(Item("Conjured item", -7, 8))
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(-8, items[0].sellIn)
        assertEquals(6, items[0].quality)
    }

    @Test
    fun `updateQuality() never decreases quality for Sulfuras` () {
        val items = listOf(Item("Sulfuras, Hand of Ragnaros", 0, 80), )
        //simulation for 1 day
        GildedRose(items).updateQuality()
        assertEquals(80, items[0].quality)
    }
}


