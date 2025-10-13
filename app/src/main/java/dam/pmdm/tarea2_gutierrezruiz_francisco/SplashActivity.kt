package dam.pmdm.tarea2_gutierrezruiz_francisco

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY_TIME: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. ESTABLECE EL LAYOUT PRIMERO Y SIEMPRE
        setContentView(R.layout.activity_splash)

        // 3. CONFIGURA EL LISTENER DE INSETS POR SEPARADO
        //    Su única responsabilidad es ajustar el padding.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. EJECUTA EL HANDLER EN EL FLUJO PRINCIPAL DEL ONCREATE
        Handler(Looper.getMainLooper()).postDelayed({
            // Este bloque se ejecutará después del tiempo de espera

            // 5. CONTEXTO CORRECTO en el Intent
            val intent = Intent(this@SplashActivity, MainActivity::class.java)

            // Inicia MainActivity
            startActivity(intent)

            // Finaliza la SplashActivity para que no se pueda volver a ella
            finish()

        }, SPLASH_DELAY_TIME)
    }
}
