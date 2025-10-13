package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ActivityAjustesBinding
import java.util.Locale

class AjustesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAjustesBinding // Declaración de la variable binding de tipo ActivityAjustesBinding. Se inicializa después
    private lateinit var preferencesHelper: PreferencesHelper // Declaración de la variable preferencesHelper de tipo PreferencesHelper. Se inicializa después

    // Metodo onCreate de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la clase padre
        super.onCreate(savedInstanceState)
        // Habilita el modo de borde para la actividad
        enableEdgeToEdge()

        // Infla el layout de la actividad utilizando el binding
        binding = ActivityAjustesBinding.inflate(layoutInflater)
        // Establece el diseño de la actividad. root es la vista raíz del diseño
        setContentView(binding.root)

        // Configura la barra de herramientas creada por mí (toolbar) porque el tema es NoActionBar
        setSupportActionBar(binding.toolbarLayout.toolbar)
        // Habilita el botón de retroceso en la barra de herramientas
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Establece el titulo de la actividad en la barra de herramientas
        supportActionBar?.title = getString(R.string.ajustes)

        // Configura el comportamiento de la barra de insets para que la vista principal tenga padding alrededor
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa la variable preferencesHelper con una instancia de PreferencesHelper. Aquí se guardarán las preferencias del usuario
        preferencesHelper = PreferencesHelper(this)

        // Configura el modo oscuro
        configurarModoOscuro()
        // Configura el idioma
        configurarIdioma()
    }

    // Metodo para configurar el modo oscuro
    private fun configurarModoOscuro() {
        // Obtiene el modo oscuro actual
        val esModoOscuroGuardado = preferencesHelper.esModoOscuro()
        // Configura el switch según el modo que está activo. Pondrá el switch en true si el modo oscuro está activo
        binding.switchTema.isChecked = esModoOscuroGuardado
        // Configura el listener del switch para cambiar el modo según el estado del switch
        binding.switchTema.setOnCheckedChangeListener { _, isChecked ->
            // Cambia el modo según el estado del switch
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            // Guarda el modo en las preferencias
            preferencesHelper.saveModoOscuro(isChecked)
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
            // Llama a la función para cambiar el idioma según el estado del switch
            if (isChecked) {
                setIdioma("es")
            } else {
                setIdioma("en")
            }
        }
    }

    // Función para cambiar el idioma. Le pasa el idioma que se quiere cambiar
    private fun setIdioma(idioma: String) {
        // Crea un nuevo Locale con el idioma que se quiere cambiar
        val locale = Locale.Builder()
            .setLanguage(idioma)
            .build()
        // Crea una nueva lista de locales con el nuevo Locale
        val listaIdiomas = LocaleListCompat.create(locale)
        // Establece la nueva lista de locales como la lista actual
        AppCompatDelegate.setApplicationLocales(listaIdiomas)
        // Guarda el idioma en las preferencias
        preferencesHelper.saveIdioma(idioma)
        // Recrea la actividad para que se aplique el cambio de idioma
        recreate()
    }

    // Sobrescribe el metodo onOptionsItemSelected de la actividad. Recibe un objeto MenuItem con la opción seleccionada
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Si se selecciona el botón de retroceso, llama al metodo onBackPressed de la actividad
            android.R.id.home -> {
                // Cierra la actividad actual y vuelve a la actividad anterior
                onBackPressedDispatcher.onBackPressed()
                // Devuelve true para indicar que se ha manejado el evento
                true
            }
            // Si se selecciona cualquier otra opción, llama al metodo onOptionsItemSelected de la clase padre
            else -> super.onOptionsItemSelected(item)
        }
    }
}