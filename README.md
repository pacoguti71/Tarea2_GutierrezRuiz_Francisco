# 🌱 Aplicación Pikmin - Android

Aplicación Android que muestra una **lista de Pikmin** y permite consultar los **detalles** de cada uno.  
Incluye una **toolbar** con las opciones **"Acerca de"** y **"Ajustes"**, con un diseño limpio y responsivo.

---

## 📸 Capturas de pantalla

| Lista de Pikmin | Detalle del Pikmin | Menú de opciones |
|------------------|--------------------|------------------|
| ![Lista](screenshots/lista_pikmin.png) | ![Detalle](screenshots/detalle_pikmin.png) | ![Menú](screenshots/menu_toolbar.png) |

> Guarda las imágenes en una carpeta `/screenshots` dentro del repositorio para que se muestren correctamente.

---

## 📱 Características principales

- **Lista de Pikmin:** muestra un `RecyclerView` con nombre, tipo e imagen de cada Pikmin.  
- **Pantalla de detalles:** al pulsar un Pikmin, se abre una `Activity` con su descripción e imagen ampliada.  
- **Toolbar con menú:**  
  - **Acerca de:** muestra información del autor o de la app.  
  - **Ajustes:** permite modificar configuraciones básicas del usuario.  
- **Diseño adaptativo:** interfaz optimizada para distintos tamaños de pantalla.

---

## 🧩 Tecnologías utilizadas

- **Lenguaje:** Kotlin  
- **Entorno:** Android Studio  
- **Componentes:**  
  - `RecyclerView`  
  - `View Binding`  
  - `Intent` y `Bundle`  
  - `Toolbar` con menú XML  
  - `ConstraintLayout`  
  - Material Design Components  

---

## 📂 Estructura del proyecto

app/
├── java/
│ └── com.example.pikminapp/
│ ├── MainActivity.kt # Muestra la lista principal de Pikmin
│ ├── DetalleActivity.kt # Muestra los detalles del Pikmin seleccionado
│ ├── PikminAdapter.kt # Adaptador del RecyclerView
│ ├── Pikmin.kt # Clase de datos Pikmin
│ ├── DataSource.kt # Fuente estática de datos
│ ├── AcercaDeActivity.kt # Pantalla "Acerca de"
│ └── AjustesActivity.kt # Pantalla de configuración
│
├── res/
│ ├── layout/
│ │ ├── activity_main.xml
│ │ ├── activity_detalle.xml
│ │ ├── activity_acerca_de.xml
│ │ ├── activity_ajustes.xml
│ │ └── item_pikmin.xml
│ ├── menu/
│ │ └── toolbar_menu.xml
│ ├── drawable/ # Imágenes e iconos de Pikmin
│ ├── values/ # strings.xml, colors.xml, styles.xml
│ └── mipmap/ # Iconos de la app
│
├── screenshots/
│ ├── lista_pikmin.png
│ ├── detalle_pikmin.png
│ └── menu_toolbar.png
│
└── AndroidManifest.xml


---

## 🚀 Ejecución del proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/PikminApp.git
2. Abre el proyecto con Android Studio.
3. Espera la sincronización de Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo físico con Android 8.0 o superior.

⚙️ Menú de la Toolbar

Archivo: res/menu/toolbar_menu.xml

<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/action_acerca"
        android:title="Acerca de"
        android:icon="@drawable/ic_info"
        android:showAsAction="ifRoom" />
    <item
        android:id="@+id/action_ajustes"
        android:title="Ajustes"
        android:icon="@drawable/ic_settings"
        android:showAsAction="ifRoom" />
</menu>


En MainActivity.kt se maneja la selección:

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
        R.id.action_acerca -> {
            startActivity(Intent(this, AcercaDeActivity::class.java))
            true
        }
        R.id.action_ajustes -> {
            startActivity(Intent(this, AjustesActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}

🧠 Conceptos reforzados

Este proyecto refuerza el uso de:
 - Arquitectura de una app Android con varias Activities.
- Implementación de listas dinámicas mediante RecyclerView y adaptadores personalizados.
- Comunicación entre Activities mediante Intent y Bundle.
- Creación y manejo de menús con Toolbar.
- Diseño de interfaz con XML y principios de Material Design.

🧑‍💻 Autor

Francisco Gutiérrez Ruiz
📚 Estudiante de Informática | 🚕 Taxista | 💻 Creador de Código para Todos

🪴 Licencia

Este proyecto se distribuye bajo la licencia MIT.
Consulta el archivo LICENSE
 para más información.
