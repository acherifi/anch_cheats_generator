package com.cherifi.acnhcheatsgenerator.data

object DataParser {

    private const val ITEM_CLEANUP_DELIMITER: String = "stringEUen/item\\STR_ItemName_00_Ftr.msbt"
    private const val RECIPE_CLEANUP_DELIMITER: String = "follows"

    fun removeHeaderLine(delimiter: String, rawData: String): String{
        return rawData.split(delimiter)[1]
    }

    fun cleanupElements(items: MutableList<Pair<String, String>>): MutableList<Pair<String, String>>{
        val cleanList = mutableListOf<Pair<String, String>>()
        for (item in items){
            cleanList.add(Pair(item.first.trim(), item.second.split('[')[0].trim()))

        }
        return cleanList
    }

    fun parseItems(rawData: String): MutableList<Pair<String, String>> {
        val itemId =
            removeHeaderLine(
                ITEM_CLEANUP_DELIMITER,
                rawData
            )
        val ids = itemId.split("]")

        val list = mutableListOf<Pair<String, String>>()
        for (item in ids){
            val split = item.split(",")
            if (split.size == 1){
                continue
            }
            list.add(Pair(split[0], split[1]))
        }
        return cleanupElements(
            list
        )
    }

    fun parseRecipes(rawData: String): MutableList<Pair<String, String>> {
        val recipesId =
            removeHeaderLine(
                RECIPE_CLEANUP_DELIMITER,
                rawData
            )
        val ids = recipesId.split("\t")

        val list = mutableListOf<Pair<String, String>>()
        var result = "([0-9A-Z]{3})\\s((\\w+-?\\s?)+)".toRegex().findAll(recipesId)
        result.forEach { matchResult ->
            val recipeName = matchResult.groups[2]?.value?.trim()!!
            val recipeId = matchResult.groups[1]?.value?.trim()!!
            list.add(Pair(recipeName, recipeId))
        }
        return list
    }


}