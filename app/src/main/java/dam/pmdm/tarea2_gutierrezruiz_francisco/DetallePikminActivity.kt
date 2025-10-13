package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ActivityDetallePikminBinding
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ActivityMainBinding

// Actividad que muestra los detalles de un pikmin
class DetallePikminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetallePikminBinding
    // Sobrescribe el metodo onCreate de la actividad. Recibe un objeto Bundle con los datos del pikmin seleccionado
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la clase padre
        super.onCreate(savedInstanceState)
        // Habilita el modo de aristas redondeadas para la barra de estado
        enableEdgeToEdge()
        // Infla el diseño de la actividad utilizando el binding. En la variable binding se almacena el diseño inflado con todos sus elementos
        binding = ActivityDetallePikminBinding.inflate(layoutInflater)
        // Establece el diseño de la actividad. root es la vista raíz del diseño
        setContentView(binding.root)
        // Configura la barra de herramientas creada por mí (toolbar) porque el tema es NoActionBar
        setSupportActionBar(binding.toolbarLayout.toolbar)
        // Habilita el botón de retroceso en la barra de herramientas
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Establece el titulo de la actividad en la barra de herramientas
        supportActionBar?.title = getString(R.string.detalle_pikmin)
        // Configura el comportamiento de la barra de insets para que la vista principal tenga padding alrededor
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtiene los datos del pikmin seleccionado desde el intent
        val nombre = intent.getStringExtra("nombre")
        val familia = intent.getStringExtra("familia")
        val nombreCientifico = intent.getStringExtra("nombreCientifico")
        val esTerrestre = intent.getBooleanExtra("esTerrestre", false)
        val esAcuatico = intent.getBooleanExtra("esAcuatico", false)
        val esAereo = intent.getBooleanExtra("esAereo", false)
        val descripcion = intent.getStringExtra("descripcion")
        val caracteristica1 = intent.getStringExtra("caracteristica1")
        val caracteristica2 = intent.getStringExtra("caracteristica2")
        val caracteristica3 = intent.getStringExtra("caracteristica3")
        val imagen = intent.getIntExtra("imagen", 0)

        // Muestra un mensaje con el nombre del pikmin seleccionado
        val mensaje=getString(R.string.se_ha_seleccionado_un_pikmin, nombre)
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

        // Oculta las vistas que no tienen datos llamando a la función setVisibleIfNotEmpty con los parametros del valor de la variable y las vistas correspondientes
        // Como caracteristicaX puede ser null, el metodo acepta null
        setVisibleIfNotEmpty(
            caracteristica1,
            binding.labelCaracteristica1,
            binding.caracteristica1Detalle
        )

        setVisibleIfNotEmpty(
            caracteristica2,
        binding.labelCaracteristica2,
            binding.caracteristica2Detalle
        )

        setVisibleIfNotEmpty(
            caracteristica3,
    binding.labelCaracteristica3,
            binding.caracteristica3Detalle
        )

        // Muestra los datos en las vistas correspondientes
        binding.nombreDetalle.text = nombre
        binding.familiaDetalle.text = familia
        binding.nombreCientificoDetalle.text = nombreCientifico
        binding.checkBoxTerrestre.isChecked = esTerrestre
        binding.checkBoxAcuatico.isChecked = esAcuatico
        binding.checkBoxAereo.isChecked = esAereo
        binding.descripcionDetalle.text = descripcion
        binding.caracteristica1Detalle.text = caracteristica1
        binding.caracteristica2Detalle.text = caracteristica2
        binding.caracteristica3Detalle.text = caracteristica3
        binding.imagenDetalle.setImageResource(imagen)
    }

    // Función que oculta o muestra una vista si el texto está vacío. Recibe un texto y una lista de vistas a modificar.
    fun setVisibleIfNotEmpty(text: String?, vararg views: View) {
        // Si el texto está vacío, oculta todas las vistas. Si no, muestra todas las vistas.
        val visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
        views.forEach { it.visibility = visibility }
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