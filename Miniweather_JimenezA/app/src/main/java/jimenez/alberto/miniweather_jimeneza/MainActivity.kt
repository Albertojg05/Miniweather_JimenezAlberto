package jimenez.alberto.miniweather_jimeneza

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import java.util.Calendar
import androidx.core.view.WindowInsetsCompat
import jimenez.alberto.miniweather_jimeneza.utilities.WeatherService
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvGretting = findViewById<TextView>(R.id.tvGreeting)
        val tvCity = findViewById<TextView>(R.id.tvCity)
        val tvWeather = findViewById<TextView>(R.id.tvWeather)
        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val ivWeather = findViewById<ImageView>(R.id.ivWeather)

        val citySelected = intent.getStringExtra("city")
        val time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        tvCity.text = citySelected

        tvGretting.text = when (time) {
            in 5..11 -> getString(R.string.good_morning)
            in 12..19 -> getString(R.string.good_afternoon)
            else -> getString(R.string.good_evening)
        }

        val weatherService = WeatherService(this)

        val weather = weatherService.getWeather(citySelected ?: "")

        tvTemperature.text = "${weather.temperature}°C"
        tvWeather.text = weather.weather

        val imageResource = when (weather.weather) {
            getString(R.string.snowy) -> R.drawable.ic_snowy
            getString(R.string.windy) -> R.drawable.ic_windy
            getString(R.string.stormy) -> R.drawable.ic_stormy
            getString(R.string.rainy) -> R.drawable.ic_rainy
            getString(R.string.cloudy) -> R.drawable.ic_cloudy
            getString(R.string.sunny) -> R.drawable.ic_sunny
            else -> R.drawable.ic_cloudy
        }

        ivWeather.setImageResource(imageResource)
    }
}