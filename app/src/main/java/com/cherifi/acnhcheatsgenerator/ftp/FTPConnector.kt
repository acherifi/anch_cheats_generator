package com.cherifi.acnhcheatsgenerator.ftp

import android.os.AsyncTask
import android.util.Log
import com.cherifi.acnhcheatsgenerator.GameVersion
import it.sauronsoftware.ftp4j.FTPClient
import java.io.*
import java.lang.Exception
import java.net.URL

object FTPConnector {

    var ip = "192.168.1.43"
    var username = "ftpd"
    var password = " 12345"
    var port = 5000

    const val CHEAT_FILE_PATH = "/atmosphere/contents/01006f8002326000/cheats/"

    init {
        DownloadFilesTask().execute().get()
    }

    class DownloadFilesTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String {
            val mFtpClient = FTPClient()
            try {
                mFtpClient.connect(ip, port)
            } catch (e: Exception){
                Log.e("FTP Error", "Error connecting to host", e)
            }
            mFtpClient.login(username, password)
            mFtpClient.type = FTPClient.TYPE_BINARY
            mFtpClient.changeDirectory(CHEAT_FILE_PATH)

            val stream = FileOutputStream("fichier")

            mFtpClient.download(GameVersion.LATEST.toString(), stream, 0, null)
            mFtpClient.disconnect(true)
            return ""
        }


    }

    fun getFile(){

    }


}