package jimenez.alberto.miniweather_jimeneza

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import jimenez.alberto.miniweather_jimeneza.domain.weather
import jimenez.alberto.miniweather_jimeneza.utilities.WeatherService

class CityActivity : AppCompatActivity() {

    var citySelected: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_city)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val service: WeatherService = WeatherService(this)
        val nextButton: Button = findViewById<Button>(R.id.btn_save_city)
        val citySelector: Spinner = findViewById<Spinner>(R.id.city_selector)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spiner_item, service.getCities())

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        citySelector.adapter = adapter

        citySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                TODO("Not yet implemented")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        nextButton.setOnClickListener {
            val intent = Intet(this, MainActivity::class)putExtra("city",citySelected)
        }
        startActivities(intent)
    }
}