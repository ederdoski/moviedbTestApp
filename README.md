# MoviedbTest - Android App

# Introducción

Una aplicación para explorar películas y gestionar datos de usuario, con integración de API REST y Firebase.

MoviedbTest es una aplicación para Android que permite a los usuarios explorar películas a través de una API REST y gestionar información relacionada con perfiles de usuario, ubicaciones y medios multimedia a través de Firebase. El objetivo es proporcionar una experiencia intuitiva y amigable.

# Arquitectura
La aplicación sigue la arquitectura MVVM (Model-View-ViewModel) para separación de preocupaciones y escalabilidad.

**Notifications Compat:** para notificar a los usuarios cuando la locacion se actualiza.

**AppExecutors:** para manejar los procesos de actualizacion en el mapa en segundo plano.

**Corrutinas**: Uso de corrutinas para la gestión de tareas en segundo plano y concurrencia.

**MVVM**: Separa la lógica de negocios de la presentación para facilitar las pruebas y el mantenimiento.

**Jetpack Components**: Uso de Navigation, DataBinding, Lifecycle y otros componentes modernos de Android.

**Koin:** Como inyector de dependencias para agilizar tiempo dado que me siento mas comodo con ella sin embargo es una biblioteca con el mismo nivel que hilt.

**Git Flow:** Se utilizo una estructura basica de git flow podran encontrar el historial de los [branch aqui](https://github.com/ederdoski/moviedbTestApp/branches)  y el historial de [pull request aqui](https://github.com/ederdoski/moviedbTestApp/pulls?q=is%3Apr+is%3Aclosed) 

El proyecto tiene dos estructuras, una llamada [core](https://github.com/ederdoski/moviedbTestApp/tree/main/moviedb/app/src/main/java/com/edominguez/moviedb/core) que se encarga de manejar la arquitectura y los componentes globales del app y otra llamada [features](https://github.com/ederdoski/moviedbTestApp/tree/main/moviedb/app/src/main/java/com/edominguez/moviedb/features/home) donde conseguiras las funcionalidades requeridas, dividi el proyecto de esta manera 
para que puedan ver el potencial de crecimiento que tiene la arquitectura.

# Persistencia de Datos

**Firebase Storage:** Para almacenamiento de imágenes.

**Firebase Cloud Firestore:** Para almacenar datos de ubicación y perfil.

# Instalación y Ejecución:

Configura las credenciales de la API de TMDb y Maps.
Las API KEY seran enviadas por correo para no exponerlo a travez de github.

solo deberan agregar en el archivo **local.properties** las siguiente lineas

**Maps:** 
MAPS_API_KEY=*INSERTE_KEY_AQUI*

**TMDB:** 
TMDB_TOKEN=*INSERTE_KEY_AQUI*

* si tienen problemas corriendo el app, conseguiran un compilado en la siguiente ruta [app --> app-debug.apk](https://github.com/ederdoski/moviedbTestApp/app)

# Por Mejorar: 

* Creacion de unit test y implementation Test
* Funciones Sociales: Permitir interacción entre usuarios.
* Autenticación de Usuario: Añadir autenticación para perfiles personalizados.
* Persistencia de datos (en el pasado he trabajado con room y realm, sin embargo no pude dedicarle el tiempo necesario para incluirlo en el proyecto) 
* Migracion de imagenes e iconos al formato svg para ganar mas calidad y reducir peso ( trabaje con librerias gratuitas que me limitaban al formato png)

# Firebase 

Se crearon dos tablas una para manejar las posiciones en los mapas y otra para manejar los reviews del usuario 
![image](https://github.com/ederdoski/moviedbTestApp/assets/39970879/af32e399-b170-43c7-9e3b-34dc3a0a28da)


![image](https://github.com/ederdoski/moviedbTestApp/assets/39970879/6a7786c8-ebb7-4ff9-bafe-c9df30224ddc) 


# Capturas de Pantalla

![image](https://github.com/ederdoski/moviedbTestApp/assets/39970879/7340332c-8471-4061-bd9e-cae39bd32f68)

![image](https://github.com/ederdoski/moviedbTestApp/assets/39970879/71bdb9de-d928-4c28-8845-10d2f2cf843e)

# Licencia

Esta aplicación se distribuye bajo la licencia MIT




