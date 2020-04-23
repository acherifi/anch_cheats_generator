package com.cherifi.acnhcheatsgenerator.data

data class ItemCheat(override val id: String, override val item: String ): ICheat {

    val ITEM_CHEAT = "04100000 AC3B90C0 0000"

    override fun toString(): String {
        return """[Slot 1 - $item]
${ITEM_CHEAT}${id.padStart(4, '0')}
            """.trimIndent()
    }


}