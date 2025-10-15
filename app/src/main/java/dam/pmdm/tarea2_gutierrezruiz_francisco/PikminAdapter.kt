package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea2_gutierrezruiz_francisco.databinding.ItemLayoutBinding

// Adapter personalizado para el RecyclerView. Recibe una lista de objetos Pikmin como parámetro y hereda de RecyclerView.Adapter
class PikminAdapter(
    private val elementos: List<Pikmin>, // lista de objetos Pikmin
    private val onItemClick: (Pikmin) -> Unit // función lambda de callback. Cuando se hace clic en un item, se llama a esta función enviando el objeto Pikmin seleccionado
) : RecyclerView.Adapter<PikminAdapter.PikminViewHolder>() {

    // ViewHolder que contiene los elementos de cada item. Aquí se almacenan las referencias a las vistas (imagen y texto de cada pikmin) dentro del layout
    // En lugar de crear un fichero aparte para el ViewHolder se crea aquí dentro.
    class PikminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Obtiene el contexto de la vista
        val context=itemView.context
        // Crea un objeto ItemLayoutBinding a partir de la vista. Esto permite acceder a las vistas dentro del layout
        private val binding = ItemLayoutBinding.bind(itemView)
        // La función bind se utiliza para establecer los datos de un objeto Pikmin con los elementos de la vista.
        fun bind(pikmin: Pikmin) {
            binding.imagen.setImageResource(pikmin.imagen)
            binding.nombre.text = context.getString(pikmin.nombre)
        }
    }

    // Sobrescribe el metodo heredado para crear un nuevo objeto ViewHolder inflando el layout del item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PikminViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PikminViewHolder(view)
    }

    // Sobrescribe el metodo heredado para vincular los datos de un objeto Pikmin a un ViewHolder
    override fun onBindViewHolder(holder: PikminViewHolder, position: Int) {
        // Obtiene el objeto Pikmin en la posición actual
        val pikmin = elementos[position]
        // Se llama a la función bind del ViewHolder para establecer los datos del pikmin
        holder.bind(pikmin)
        // Cuando se hace clic en el item, se llama a la función lambda onItemClick enviando el objeto Pikmin seleccionado
        holder.itemView.setOnClickListener { onItemClick(pikmin) }
    }

    // Sobrescribe el metodo heredado para devolver la cantidad de elementos en la lista
    override fun getItemCount(): Int = elementos.size
}
