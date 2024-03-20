package Data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import modele.Song
import java.io.IOException

class ReadSongs {
    fun loadJson(context: Context): List<Song>{
        var jsonString: String

        try{
            jsonString = context.assets.open("cd_catalog.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            Log.d("LoadJson","null!!!!")
            return emptyList()
        }
        //Log.d("LoadJson", jsonString)
        Log.d("LoadJson", "yes")

        val gson = Gson()
        val listeSongModele = object : TypeToken<List<Song>>(){}.type
        println(listeSongModele)
        var liste: List<Song> = gson.fromJson(jsonString, listeSongModele)
        //liste.forEachIndexed { idx, song -> Log.i("data", "> Item $idx:\n$song") }

        return liste
    }
}