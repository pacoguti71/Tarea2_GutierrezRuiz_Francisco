package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Declaración de la variable binding de tipo ActivityMainBinding. Se inicializa después
    private lateinit var adapter: PikminAdapter // Declaración de la variable adapter de tipo PikminAdapter. Se inicializa después
    private val elementos: List<Pikmin> =
        crearListaPikmin() // Declaración de la variable elementos de tipo List<Pikmin>. Se inicializa llamando a la función crearListaPikmin()

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
        // Configura la barra de herramientas creada por mí (toolbar) porque el tema es NoActionBar
        setSupportActionBar(binding.toolbarLayout.toolbar)
        // Configura el comportamiento de la barra de insets para que la vista principal tenga padding alrededor
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Crea un objeto PreferencesHelper para gestionar las preferencias del usuario
        val preferencesHelper = PreferencesHelper(this)
        // Recupera el estado de la preferencia de modo oscuro
        val esModoOscuro = preferencesHelper.esModoOscuro()
        // Comprueba si el modo oscuro está activado
        if (esModoOscuro) {
            // Si estaba guardado como oscuro, aplica el modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            // Si no, aplica el modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Asigna un layout al reciclerView para gestionar el diseño en cuadrícula
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

        // Muestra un Snackbar con un mensaje de bienvenida
        Snackbar.make(
            binding.main,
            getString(R.string.bienvenido_al_mundo_pikmin),
            Snackbar.LENGTH_LONG
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
            // Si se ha pulsado el item de Acerca de...
            R.id.action_acercade -> {
                AlertDialog.Builder(this)
                    .setIcon(R.drawable.icono)
                    .setTitle(getString(R.string.acerca_de))
                    .setMessage(getString(R.string.desarrollado_por))
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show() // Muestra el diálogo
                true // Devuelve 'true' para indicar que has gestionado el clic
            }
            // Si se ha pulsado el item Ajustes
            R.id.action_ajustes -> {
                val intent = Intent(this, AjustesActivity::class.java)
                startActivity(intent)
                true // Devuelve 'true' para indicar que has gestionado el clic
            }

            else -> super.onOptionsItemSelected(item) // Si no es un item que conozcas, deja que el sistema lo gestione. Sobre todo la flecha de retroceso
        }
    }

    private fun crearListaPikmin(): List<Pikmin> {
        // Lista de pikmins
        return listOf(
            Pikmin(
                nombre = R.string.red_pikmin,
                familia = R.string.fam_red_pikmin,
                nombreCientifico = R.string.cn_red_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_red_pikmin,
                caracteristica1 = R.string.char1_red_pikmin,
                caracteristica2 = R.string.char2_red_pikmin,
                caracteristica3 = R.string.char3_red_pikmin,
                imagen = R.drawable.red_pikmin

            ),
            Pikmin(
                nombre = R.string.yellow_pikmin,
                familia = R.string.fam_yellow_pikmin,
                nombreCientifico = R.string.cn_yellow_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_yellow_pikmin,
                caracteristica1 = R.string.char1_yellow_pikmin,
                caracteristica2 = R.string.char2_yellow_pikmin,
                caracteristica3 = R.string.char3_yellow_pikmin,
                imagen = R.drawable.yellow_pikmin
            ),
            Pikmin(
                nombre = R.string.blue_pikmin,
                familia = R.string.fam_blue_pikmin,
                nombreCientifico = R.string.cn_blue_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_blue_pikmin,
                caracteristica1 = R.string.char1_blue_pikmin,
                caracteristica2 = R.string.char2_blue_pikmin,
                caracteristica3 = R.string.char3_blue_pikmin,
                imagen = R.drawable.blue_pikmin
            ),
            Pikmin(
                nombre = R.string.white_pikmin,
                familia = R.string.fam_white_pikmin,
                nombreCientifico = R.string.cn_white_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_white_pikmin,
                caracteristica1 = R.string.char1_white_pikmin,
                caracteristica2 = R.string.char2_white_pikmin,
                caracteristica3 = R.string.char3_white_pikmin,
                imagen = R.drawable.white_pikmin
            ),
            Pikmin(
                nombre = R.string.purple_pikmin,
                familia = R.string.fam_purple_pikmin,
                nombreCientifico = R.string.cn_purple_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_purple_pikmin,
                caracteristica1 = R.string.char1_purple_pikmin,
                caracteristica2 = R.string.char2_purple_pikmin,
                caracteristica3 = R.string.char3_purple_pikmin,
                imagen = R.drawable.purple_pikmin
            ),
            Pikmin(
                nombre = R.string.rock_pikmin,
                familia = R.string.fam_rock_pikmin,
                nombreCientifico = R.string.cn_rock_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_rock_pikmin,
                caracteristica1 = R.string.char1_rock_pikmin,
                caracteristica2 = R.string.char2_rock_pikmin,
                caracteristica3 = R.string.char3_rock_pikmin,
                imagen = R.drawable.rock_pikmin
            ),
            Pikmin(
                nombre = R.string.winged_pikmin,
                familia = R.string.fam_winged_pikmin,
                nombreCientifico = R.string.cn_winged_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_winged_pikmin,
                caracteristica1 = R.string.char1_winged_pikmin,
                caracteristica2 = R.string.char2_winged_pikmin,
                caracteristica3 = R.string.char3_winged_pikmin,
                imagen = R.drawable.winged_pikmin
            ),
            Pikmin(
                nombre = R.string.ice_pikmin,
                familia = R.string.fam_ice_pikmin,
                nombreCientifico = R.string.cn_ice_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_ice_pikmin,
                caracteristica1 = R.string.char1_ice_pikmin,
                caracteristica2 = R.string.char2_ice_pikmin,
                caracteristica3 = R.string.char3_ice_pikmin,
                imagen = R.drawable.ice_pikmin
            ),
            Pikmin(
                nombre = R.string.glow_pikmin,
                familia = R.string.fam_glow_pikmin,
                nombreCientifico = R.string.cn_glow_pikmin,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_glow_pikmin,
                caracteristica1 = R.string.char1_glow_pikmin,
                caracteristica2 = R.string.char2_glow_pikmin,
                caracteristica3 = R.string.char3_glow_pikmin,
                imagen = R.drawable.glow_pikmin
            ),
            Pikmin(
                nombre = R.string.bulborb,
                familia = R.string.fam_bulborb,
                nombreCientifico = R.string.cn_bulborb,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_bulborb,
                caracteristica1 = R.string.char1_bulborb,
                caracteristica2 = R.string.char2_bulborb,
                caracteristica3 = R.string.char3_bulborb,
                imagen = R.drawable.bulborb
            ),
            Pikmin(
                nombre = R.string.joustmite,
                familia = R.string.fam_joustmite,
                nombreCientifico = R.string.cn_joustmite,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_joustmite,
                caracteristica1 = R.string.char1_joustmite,
                caracteristica2 = R.string.char2_joustmite,
                caracteristica3 = R.string.char3_joustmite,
                imagen = R.drawable.joustmite
            ),
            Pikmin(
                nombre = R.string.skitter_leaf,
                familia = R.string.fam_skitter_leaf,
                nombreCientifico = R.string.cn_skitter_leaf,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_skitter_leaf,
                caracteristica1 = R.string.char1_skitter_leaf,
                caracteristica2 = R.string.char2_skitter_leaf,
                caracteristica3 = R.string.char3_skitter_leaf,
                imagen = R.drawable.skitter_leaf
            ),
            Pikmin(
                nombre = R.string.skutterchuck,
                familia = R.string.fam_skutterchuck,
                nombreCientifico = R.string.cn_skutterchuck,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_skutterchuck,
                caracteristica1 = R.string.char1_skutterchuck,
                caracteristica2 = R.string.char2_skutterchuck,
                caracteristica3 = R.string.char3_skutterchuck,
                imagen = R.drawable.skutterchuck
            ),
            Pikmin(
                nombre = R.string.pyroclasmic_slooch,
                familia = R.string.fam_pyroclasmic_slooch,
                nombreCientifico = R.string.cn_pyroclasmic_slooch,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_pyroclasmic_slooch,
                caracteristica1 = R.string.char1_pyroclasmic_slooch,
                caracteristica2 = R.string.char2_pyroclasmic_slooch,
                caracteristica3 = R.string.char3_pyroclasmic_slooch,
                imagen = R.drawable.pyroclasmic_slooch
            ),
            Pikmin(
                nombre = R.string.bearded_amprat,
                familia = R.string.fam_bearded_amprat,
                nombreCientifico = R.string.cn_bearded_amprat,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_bearded_amprat,
                caracteristica1 = R.string.char1_bearded_amprat,
                caracteristica2 = R.string.char2_bearded_amprat,
                caracteristica3 = R.string.char3_bearded_amprat,
                imagen = R.drawable.bearded_amprat
            ),
            Pikmin(
                nombre = R.string.empress_bulblax,
                familia = R.string.fam_empress_bulblax,
                nombreCientifico = R.string.cn_empress_bulblax,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_empress_bulblax,
                caracteristica1 = R.string.char1_empress_bulblax,
                caracteristica2 = R.string.char2_empress_bulblax,
                caracteristica3 = R.string.char3_empress_bulblax,
                imagen = R.drawable.empress_bulblax
            ),
            Pikmin(
                nombre = R.string.waterwraith,
                familia = R.string.fam_waterwraith,
                nombreCientifico = R.string.cn_waterwraith,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_waterwraith,
                caracteristica1 = R.string.char1_waterwraith,
                caracteristica2 = R.string.char2_waterwraith,
                caracteristica3 = R.string.char3_waterwraith,
                imagen = R.drawable.waterwraith
            ),
            Pikmin(
                nombre = R.string.lumiknoll,
                familia = R.string.fam_lumiknoll,
                nombreCientifico = R.string.cn_lumiknoll,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_lumiknoll,
                caracteristica1 = R.string.char1_lumiknoll,
                caracteristica2 = R.string.char2_lumiknoll,
                caracteristica3 = R.string.char3_lumiknoll,
                imagen = R.drawable.lumiknoll
            ),
            Pikmin(
                nombre = R.string.peckish_aristocrab,
                familia = R.string.fam_peckish_aristocrab,
                nombreCientifico = R.string.cn_peckish_aristocrab,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_peckish_aristocrab,
                caracteristica1 = R.string.char1_peckish_aristocrab,
                caracteristica2 = R.string.char2_peckish_aristocrab,
                caracteristica3 = R.string.char3_peckish_aristocrab,
                imagen = R.drawable.peckish_aristocrab
            ),
            Pikmin(
                nombre = R.string.puckering_blinnow,
                familia = R.string.fam_puckering_blinnow,
                nombreCientifico = R.string.cn_puckering_blinnow,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_puckering_blinnow,
                caracteristica1 = R.string.char1_puckering_blinnow,
                caracteristica2 = R.string.char2_puckering_blinnow,
                caracteristica3 = R.string.char3_puckering_blinnow,
                imagen = R.drawable.puckering_blinnow
            ),
            Pikmin(
                nombre = R.string.skeeterskate,
                familia = R.string.fam_skeeterskate,
                nombreCientifico = R.string.cn_skeeterskate,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_skeeterskate,
                caracteristica1 = R.string.char1_skeeterskate,
                caracteristica2 = R.string.char2_skeeterskate,
                caracteristica3 = R.string.char3_skeeterskate,
                imagen = R.drawable.skeeterskate
            ),
            Pikmin(
                nombre = R.string.toady_bloyster,
                familia = R.string.fam_toady_bloyster,
                nombreCientifico = R.string.cn_toady_bloyster,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_toady_bloyster,
                caracteristica1 = R.string.char1_toady_bloyster,
                caracteristica2 = R.string.char2_toady_bloyster,
                caracteristica3 = R.string.char3_toady_bloyster,
                imagen = R.drawable.toady_bloyster
            ),
            Pikmin(
                nombre = R.string.waddlepus,
                familia = R.string.fam_waddlepus,
                nombreCientifico = R.string.cn_waddlepus,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_waddlepus,
                caracteristica1 = R.string.char1_waddlepus,
                caracteristica2 = R.string.char2_waddlepus,
                caracteristica3 = R.string.char3_waddlepus,
                imagen = R.drawable.waddlepus
            ),
            Pikmin(
                nombre = R.string.puffy_blowhog,
                familia = R.string.fam_puffy_blowhog,
                nombreCientifico = R.string.cn_puffy_blowhog,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_puffy_blowhog,
                caracteristica1 = R.string.char1_puffy_blowhog,
                caracteristica2 = R.string.char2_puffy_blowhog,
                caracteristica3 = R.string.char3_puffy_blowhog,
                imagen = R.drawable.puffy_blowhog
            ),
            Pikmin(
                nombre = R.string.swooping_snitchbug,
                familia = R.string.fam_swooping_snitchbug,
                nombreCientifico = R.string.cn_swooping_snitchbug,
                esTerrestre = true,
                esAcuatico = false,
                esAereo = false,
                descripcion = R.string.desc_swooping_snitchbug,
                caracteristica1 = R.string.char1_swooping_snitchbug,
                caracteristica2 = R.string.char2_swooping_snitchbug,
                caracteristica3 = R.string.char3_swooping_snitchbug,
                imagen = R.drawable.swooping_snitchbug
            )
        )
    }


}