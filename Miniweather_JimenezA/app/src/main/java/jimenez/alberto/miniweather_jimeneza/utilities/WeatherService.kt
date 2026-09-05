package jimenez.alberto.miniweather_jimeneza.utilities

import android.content.Context
import jimenez.alberto.miniweather_jimeneza.R
import jimenez.alberto.miniweather_jimeneza.domain.Weather

class WeatherService(val context: Context) {
    val weatherStates = arrayOf(
        context.getString(R.string.snowy),
        context.getString(R.string.windy),
        context.getString(R.string.stormy),
        context.getString(R.string.rainy),
        context.getString(R.string.cloudy),
        context.getString(R.string.sunny)
    )

    fun getCities():Array<String>{
        return arrayOf("Ciudad de México","Londres","Paris","Guadalajara","Ciudad Obregon")
    }

     fun generateWeather(): Weather {
        val temp = (-15..50).random()

        var weatherIndex = -1
        when(temp){
            in -15..0 -> weatherIndex = 0
            in 1..18 -> weatherIndex = (1..4).random()
            in 19..25 -> weatherIndex = (4..5).random()
            else -> weatherIndex = 5
        }
        return Weather(temp,weatherStates[weatherIndex])
    }
}
