package com.rebtel.country.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Country list row item
 * check the item is in header
 * @param isHeader
 * @param char
 * @param item
 */
data class ListRow(var isHeader: Boolean, val char: Char, val item: CountryResponseDataModel)