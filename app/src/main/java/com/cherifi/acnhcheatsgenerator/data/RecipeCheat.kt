package com.cherifi.acnhcheatsgenerator.data

data class RecipeCheat(override val id: String, override val item: String ): ICheat {

    val RECIPE_CHEAT = "04100000 AC3B90C0 000016A2\n04100000 AC3B90C4 00000"

    override fun toString(): String {
        return """[Slot 1 - $item]
${RECIPE_CHEAT}${id.padStart(4, '0')}
            """.trimIndent()
    }


}