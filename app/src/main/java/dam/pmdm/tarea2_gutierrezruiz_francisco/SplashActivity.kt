package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val tiempoRetardo: Long = 2000 // Declara el tiempo de retardo en milisegundos

    // Metodo que se ejecuta al crear la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al metodo onCreate de la superclase
        super.onCreate(savedInstanceState)
        // Verifica si la version de Android es mayor o igual a la version de API 31
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            // Si es menor, manejamos la splash manualmente con un Handler.
            // Establece el layout de la actividad
            setContentView(R.layout.activity_splash)
            // Crea una instancia del Handler en el hilo principal y ejecuta una tarea retardada
            Handler(Looper.getMainLooper()).postDelayed({
                // Crea un Intent para iniciar la actividad principal
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                // Inicia la actividad principal
                startActivity(intent)
                // Finaliza la actividad actual
                finish()
            }, tiempoRetardo)
            // Si la version de Android es mayor o igual a la version de API 31, usamos la splash nativa
        } else {
            // Establece el layout de la actividad
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            // Inicia la actividad principal
            startActivity(intent)
            // Finaliza la actividad actual
            finish()
        }
    }
}
