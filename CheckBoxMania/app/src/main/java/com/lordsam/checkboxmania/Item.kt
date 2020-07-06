package com.lordsam.checkboxmania

import android.os.Parcel
import android.os.Parcelable


class Item :Parcelable{
    var itemName = ""
    var itemPrize = 0
    var itemSize = 0
    var totalAmount = 0

    constructor(parcel: Parcel) {
        itemName = parcel.readString().toString()
        itemPrize = parcel.readInt()
        itemSize = parcel.readInt()
        totalAmount = parcel.readInt()
    }

    constructor(itemName :String, itemPrize :Int, itemSize :Int, totalAmount :Int){
        this.itemName = itemName
        this.itemPrize = itemPrize
        this.itemSize = itemSize
        this.totalAmount = totalAmount
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        if (p0 != null) {
            p0.writeString(itemName)
            p0.writeInt(itemPrize)
            p0.writeInt(itemSize)
            p0.writeInt(totalAmount)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}