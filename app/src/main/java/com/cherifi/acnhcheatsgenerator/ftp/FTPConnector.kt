package com.cherifi.acnhcheatsgenerator.ftp

import android.os.AsyncTask
import android.util.Log
import com.cherifi.acnhcheatsgenerator.GameVersion
import org.apache.commons.net.ftp.FTPClient
import java.io.ByteArrayOutputStream

object FTPConnector {

    var ip = "192.168.1.43"
    var username = "ftpd"
    var password = " 12345"
    var port = 5000

    const val CHEAT_FILE_PATH = "/atmosphere/contents/01006f8002326000/cheats/"

    init {
        DownloadFilesTask().execute()
    }

    class DownloadFilesTask : AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg params: Void?): String {
            val client: FTPClient = FTPClient()
            try {
                client.connect(ip, 5000)
                Log.d("FTP", "Connected to the switch.")
            } catch(e: Exception){
                Log.d("FTP", "Unable to connect to switch.", e)
            }
            client.enterLocalPassiveMode()
            client.login(username, password)
            client.changeWorkingDirectory(CHEAT_FILE_PATH)
            val files = client.listFiles()

            var outStream = ByteArrayOutputStream()
            val retrieved = client.retrieveFile(GameVersion.LATEST.gameVersion+ ".txt", outStream)
            if (retrieved){
                Log.d("FTP", "File retrieved!")
            }
            outStream.close()
            client.disconnect()
            return ""

        }
        /*override fun doInBackground(vararg params: Void?): String {
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
        }*/


    }

    fun getFile(){

    }


}