package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ActivityAjustesBinding
import java.util.Locale

class AjustesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAjustesBinding // // Declaración de la variable binding de tipo ActivityAjustesBinding. Se inicializa después

    // Metodo onCreate de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la clase padre
        super.onCreate(savedInstanceState)
        // Habilita el modo de borde para la actividad
        enableEdgeToEdge()
        // Infla el layout de la actividad utilizando el binding
        binding = ActivityAjustesBinding.inflate(layoutInflater)
        // Establece el layout inflado como el contenido de la actividad
        setContentView(binding.root)
        // Configura el comportamiento de la barra de insets para que la vista principal tenga padding alrededor
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Configura el modo oscuro
        configurarModoOscuro()
        // Configura el idioma
        configurarIdioma()
    }


    // Metodo para configurar el modo oscuro
    private fun configurarModoOscuro() {
        // Obtiene el modo oscuro actual
        val esModoOscuroActual =
            (resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) == android.content.res.Configuration.UI_MODE_NIGHT_YES
        // Configura el switch según el modo que está activo. Pondrá el switch en true si el modo oscuro está activo
        binding.switchTema.isChecked = esModoOscuroActual
        // Configura el listener del switch para cambiar el modo según el estado del switch
        binding.switchTema.setOnCheckedChangeListener { _, isChecked ->
            // Cambia el modo según el estado del switch
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    // Metodo para configurar el idioma
    private fun configurarIdioma() {
        // Obtiene el idioma actual
        val idiomaActual = Locale.getDefault().language
        // Configura el switch según el idioma que está activo. Pondrá el switch en true si el idioma es español
        binding.switchIdioma.isChecked = idiomaActual == "es"
        // Configura el listener del switch para cambiar el idioma según el estado del switch
        binding.switchIdioma.setOnCheckedChangeListener { _, isChecked ->
            // Cambia el idioma según el estado del switch
            if (isChecked) {
                setIdioma("es")
            } else {
                setIdioma("en")
            }
        }
    }

    // Metodo para cambiar el idioma. Le pasa el idioma que se quiere cambiar
    private fun setIdioma(idioma: String) {
        // Crea un nuevo Locale con el idioma que se quiere cambiar
        val locale = Locale.Builder()
            .setLanguage(idioma)
            .build()
        // Crea una nueva lista de locales con el nuevo Locale
        val localeList = LocaleListCompat.create(locale)
        // Establece la nueva lista de locales como la lista actual
        AppCompatDelegate.setApplicationLocales(localeList)
        // Recrea la actividad para que se aplique el cambio de idioma
        recreate()
    }

}

