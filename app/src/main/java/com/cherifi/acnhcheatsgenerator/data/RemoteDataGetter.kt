package com.cherifi.acnhcheatsgenerator.data

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.URL

object RemoteDataGetter {

    val ITEM_ID_FILE_URL: URL = URL(  "https://pastebin.com/raw/B8N5KKsh")
    val RECIPE_ITEM_ID_URL: URL = URL("https://pastebin.com/raw/w8CVN7Zr")

    lateinit var items: MutableList<Pair<String, String>>
    lateinit var recipes: MutableList<Pair<String, String>>

    init {
        val content = DownloadFilesTask()
            .execute(
                ITEM_ID_FILE_URL,
                RECIPE_ITEM_ID_URL
            ).get()
        if (content.first != "" && content.second != "") {
            items = content.first?.let { DataParser.parseItems(it) }!!
            recipes = content.second?.let { DataParser.parseRecipes(it) }!!
        }

    }



    class DownloadFilesTask : AsyncTask<URL, Int, Pair<String?, String?>>() {

        override fun doInBackground(vararg params: URL?): Pair<String?, String?> {
            try {
                return Pair(params[0]?.readText(), params[1]?.readText())
            } catch (e: IOException){
                Log.d("NTWK ERROR", "Unable to download file", e)
            }
            return Pair("", "")

        }
    }
}

