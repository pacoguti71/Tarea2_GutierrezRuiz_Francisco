package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ItemLayoutBinding

/**
 * Adaptador personalizado para un [RecyclerView] diseñado para mostrar una lista de objetos [Pikmin].
 *
 * Este adaptador se encarga de crear las vistas ([PikminViewHolder]) a partir del layout
 * [R.layout.item_layout] y de vincular los datos de cada [Pikmin] a su respectiva vista.
 * Además, gestiona el evento de clic en cada elemento de la lista.
 *
 * @property elementos Lista de objetos [Pikmin] que se mostrarán en el RecyclerView.
 * @property onItemClick Función lambda que se ejecuta cuando se hace clic en un elemento. Recibe el [Pikmin] seleccionado como parámetro.
 */
class PikminAdapter(
    private val elementos: List<Pikmin>, // lista de objetos Pikmin
    private val onItemClick: (Pikmin) -> Unit // función lambda de callback. Cuando se hace clic en un item, se llama a esta función enviando el objeto Pikmin seleccionado
) : RecyclerView.Adapter<PikminAdapter.PikminViewHolder>() {

    /**
     * ViewHolder que contiene y gestiona las vistas de un único elemento ([Pikmin]) dentro del [RecyclerView].
     *
     * Almacena las referencias a las vistas (imagen y texto) y proporciona la función [bind]
     * para rellenar esas vistas con los datos de un objeto [Pikmin].
     *
     * @param itemView La vista raíz del layout del elemento individual (R.layout.item_layout).
     */
    class PikminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Obtiene el contexto de la vista
        val context = itemView.context
        // Crea un objeto ItemLayoutBinding a partir de la vista. Esto permite acceder a las vistas dentro del layout
        private val binding = ItemLayoutBinding.bind(itemView)

        /**
         * Enlaza los datos de un objeto [Pikmin] a las vistas del ViewHolder.
         *
         * Establece la imagen y el nombre del Pikmin, utilizando [context.getString] para
         * resolver el Resource ID del nombre.
         *
         * @param pikmin El objeto [Pikmin] con los datos a mostrar.
         */
        fun bind(pikmin: Pikmin) {
            binding.imagen.setImageResource(pikmin.imagen)
            // Se usa context.getString(pikmin.nombre) porque pikmin.nombre es un Resource ID (Int)
            binding.nombre.text = context.getString(pikmin.nombre)
        }
    }

    /**
     * Sobrescribe el metodo heredado para crear un nuevo objeto [PikminViewHolder].
     *
     * Este método se llama cuando el [RecyclerView] necesita un nuevo [ViewHolder] para representar
     * un elemento. Infla el layout [R.layout.item_layout].
     *
     * @param parent El [ViewGroup] en el que se colocará la nueva [View] después de que se adjunte al adaptador.
     * @param viewType El tipo de vista de la nueva [View].
     * @return Una nueva instancia de [PikminViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PikminViewHolder {
        // Infla el layout del item a partir de su Resource ID
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PikminViewHolder(view)
    }

    /**
     * Sobrescribe el metodo heredado para vincular los datos de un objeto [Pikmin] a un [PikminViewHolder].
     *
     * Este método es llamado por el [RecyclerView] para mostrar los datos en la posición especificada.
     * También adjunta un `OnClickListener` al elemento de la vista.
     *
     * @param holder El [PikminViewHolder] que debe actualizarse para representar el contenido del elemento.
     * @param position La posición del elemento dentro del conjunto de datos del adaptador.
     */
    override fun onBindViewHolder(holder: PikminViewHolder, position: Int) {
        // Obtiene el objeto Pikmin en la posición actual
        val pikmin = elementos[position]
        // Se llama a la función bind del ViewHolder para establecer los datos del pikmin
        holder.bind(pikmin)
        // Establece el OnClickListener en la vista del elemento.
        // Cuando se hace clic, se ejecuta la función lambda onItemClick, pasando el Pikmin seleccionado.
        holder.itemView.setOnClickListener { onItemClick(pikmin) }
    }

    /**
     * Sobrescribe el metodo heredado para devolver la cantidad total de elementos en el conjunto de datos.
     *
     * @return El tamaño de la lista de [Pikmin] ([elementos.size]).
     */
    override fun getItemCount(): Int = elementos.size
}