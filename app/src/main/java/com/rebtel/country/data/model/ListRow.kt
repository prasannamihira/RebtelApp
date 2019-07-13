package com.rebtel.country.data.model

/**
 * Country list row item
 *
 * @param isHeader // check the item is in header
 * @param char // char index bind to scrollbar
 * @param item // country item
 */
data class ListRow(var isHeader: Boolean, val char: Char, val item: CountryResponseDataModel)