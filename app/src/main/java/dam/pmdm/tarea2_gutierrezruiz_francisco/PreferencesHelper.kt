package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.content.Context
import android.content.SharedPreferences
// --- CONSTANTES PRIVADAS A NIVEL DE FICHERO ---
// Se definen fuera de la clase y son privadas, por lo que solo son visibles dentro de este fichero.
private const val PREFERENCES_FILE_NAME = "app_settings"
private const val KEY_DARK_MODE = "dark_mode"
private const val KEY_LANGUAGE = "language"

class PreferencesHelper(context: Context) {
    // Crea una instancia de SharedPreferences. Context.MODE_PRIVATE asegura que solo esta aplicación pueda acceder a este fichero.
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)

    // Función para leer si está guardado el modo oscuro.
    fun esModoOscuro(): Boolean {
        // Leemos el valor booleano. Si no se encuentra la clave, devuelve 'false' como valor por defecto.
        return sharedPreferences.getBoolean(KEY_DARK_MODE, false)
    }

    // Función para leer el idioma guardado. No se usa
    fun getIdioma(): String {
        // Lee el valor String. Si no se encuentra, devuelve "es" por defecto. El operador elvis (?:) asegura que si se devuelve un nulo, lo convertimos a "es".
        return sharedPreferences.getString(KEY_LANGUAGE, "es") ?: "es"
    }

    // Función para guardar el modo oscuro.
    fun saveModoOscuro(esModoOscuro: Boolean) {
        // Obtiene un editor para poder modificar las preferencias.
        sharedPreferences.edit().apply {
            // Guarda el valor booleano con su clave correspondiente.
            putBoolean(KEY_DARK_MODE, esModoOscuro)
            // Guarda los cambios
            apply()
        }
    }

    // Función para guardar el idioma.
    fun saveIdioma(idioma: String) {
        // Obtiene un editor para poder modificar las preferencias.
        sharedPreferences.edit().apply {
            // Guarda el valor String con su clave correspondiente.
            putString(KEY_LANGUAGE, idioma)
            // Guarda los cambios
            apply()
        }
    }

}
