package com.cherifi.acnhcheatsgenerator.data

data class Cheat(val id: String, val item: String ) {

    override fun toString(): String {
        return """[Slot 1 - $item]
            ${CheatsConstants.ITEM_CHEAT}${id.padStart(4, '0')}
            """.trimIndent()
    }


}