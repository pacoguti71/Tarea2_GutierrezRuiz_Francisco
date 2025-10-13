package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Declaración de la variable binding de tipo ActivityMainBinding. Se inicializa después
    private lateinit var adapter: PikminAdapter // Declaración de la variable adapter de tipo PikminAdapter. Se inicializa después
    private val elementos =
        PIKMINS // Declaración de la variable elementos que es una lista de objetos Pikmin

    // Metodo onCreate de la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la clase padre
        super.onCreate(savedInstanceState)
        // Habilita el modo de aristas redondeadas para la barra de estado
        enableEdgeToEdge()
        // Infla el diseño de la actividad utilizando el binding. En la variable binding se almacena el diseño inflado con todos sus elementos
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Establece el diseño de la actividad. root es la vista raíz del diseño
        setContentView(binding.root)
        // Configura la barra de herramientas creada por mí (toolbar)
        setSupportActionBar(binding.toolbar)
        // Configura el comportamiento de la barra de insets para que la vista principal tenga padding alrededor
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Asigna un layout al reciclerView para gestionar el diseño en cuadrícula
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

        // Muestra un Snackbar con un mensaje de bienvenida
        Snackbar.make(
            binding.main, getString(R.string.bienvenido_al_mundo_pikmin), Snackbar.LENGTH_LONG
        ).show()

        // Crea un objeto adaptador con los datos de la lista de objetos Pikmin y lo asigna al adaptador del RecyclerView.
        // pikminSeleccionado es el objeto Pikmin seleccionado que se pasa a la actividad DetallePikminActivity.
        adapter = PikminAdapter(elementos) { pikminSeleccionado ->
            // Crea un intent para iniciar la actividad DetallePikminActivity. En ese intent se pasa el objeto Pikmin seleccionado
            val intent = Intent(this, DetallePikminActivity::class.java)
            // Pasa los datos del objeto Pikmin seleccionado a la actividad DetallePikminActivity. Estos datos se recuperarán en la actividad DetallePikminActivity
            // Se podría pasar el objeto completo serializando en data class, pero prefiero enviar los datos por separado para mantener el ejemplo simple
            intent.putExtra("nombre", pikminSeleccionado.nombre)
            intent.putExtra("familia", pikminSeleccionado.familia)
            intent.putExtra("nombreCientifico", pikminSeleccionado.nombreCientifico)
            intent.putExtra("esTerrestre", pikminSeleccionado.esTerrestre)
            intent.putExtra("esAcuatico", pikminSeleccionado.esAcuatico)
            intent.putExtra("esAereo", pikminSeleccionado.esAereo)
            intent.putExtra("descripcion", pikminSeleccionado.descripcion)
            intent.putExtra("caracteristica1", pikminSeleccionado.caracteristica1)
            intent.putExtra("caracteristica2", pikminSeleccionado.caracteristica2)
            intent.putExtra("caracteristica3", pikminSeleccionado.caracteristica3)
            intent.putExtra("imagen", pikminSeleccionado.imagen)
            // Inicia la actividad DetallePikminActivity
            startActivity(intent)
        }
        // Asigna el adaptador del RecyclerView para que lo use
        binding.recyclerView.adapter = adapter
    }

    // Sobreescribe el metodo onCreateOptionsMenu de la actividad para mostrar el menu
    // El menú se añade dinámicamente en tiempo de ejecución, por lo que no forma parte del layout estático de la actividad.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // El menuInflater toma el fichero XML y crea el menú a partir de él
        menuInflater.inflate(R.menu.menu, menu)
        return true // Devuelve 'true' para indicar que el menú debe mostrarse
    }

    // Sobreescribe el metodo onOptionsItemSelected para gestionar los items del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Usamos una estructura 'when' para comprobar qué item se ha pulsado
        return when (item.itemId) {
            R.id.action_acercade -> {
                // Diálogo que se muestra al pulsar "Acerca de"
                AlertDialog.Builder(this).setTitle(getString(R.string.acerca_de))
                    .setMessage(getString(R.string.desarrollado_por))
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        // Acción al pulsar "Ok"
                        dialog.dismiss()
                    }.show() // Muestra el diálogo
                true // Devuelve 'true' para indicar que has gestionado el clic
            }

            R.id.action_ajustes -> {
                val intent = Intent(this, AjustesActivity::class.java)
                startActivity(intent)
                true // Devuelve 'true' para indicar que has gestionado el clic
            }


            else -> super.onOptionsItemSelected(item) // Si no es un item que conozcas, deja que el sistema lo gestione. Sobre todo la flecha de retroceso
        }
    }

}