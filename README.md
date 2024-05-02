# moviedbTestApp

Introduction

Reto tecnico creado para presentar mis capacidades al equipo de openPay, el proyecto sigue la mayoria de lineamientos propuestos en el reto.

Uso:

El API KEY sera enviado por correo para no exponerlo a travez de github, solo deberan agregar en el archivo 
local.properties la siguiente linea : 

MAPS_API_KEY=*INSERTE_KEY_AQUI*

si tienen problemas corriendo el app, conseguiran un compilado en la siguiente ruta  [moviedbTestApp]([https://github.com/ederdoski/moviedbTestApp/app](https://github.com/ederdoski/moviedbTestApp/tree/main/app))

Observaciones

Se utilizo una estructura basica de git flow para llevar a cabo el proyecto podran encontrar los detalles aqui

El proyecto tiene dos estructuras, una llamada core que se encarga de manejar la arquitectura y los componentes globales del app y otra llamada features donde conseguiras las funcionalidades requeridas, dividi el proyecto de esta manera 
para que puedan ver el potencial de crecimiento que tiene la arquitectura.

si bien el proyecto esta basado en MVVM y realmente cuenta con un actividad tal como se pidio, divide cada uno de los modulos en paquetes independientes para que sea escalable

Use la inyeccion de dependencias con Koin para agilizar tiempo dado que me siento mas comodo con ella sin embargo es una biblioteca con el mismo nivel que hilt 

Por Mejorar: 

Persistencia de datos (en el pasado he trabajado con room y realm, sin embargo no pude dedicarle el tiempo necesario para incluirlo en el proyecto)
Migracion de imagenes e iconos al formato svg para ganar mas calidad y reducir peso ( trabaje con librerias gratuitas que me limitaban al formato png)
Creacion de unit test y implementation Test
