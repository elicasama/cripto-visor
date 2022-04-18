# CriptoVisor

El proyecto consiste en crear un visor que muestre cotizaciones de las 100 criptomonedas más usadas.

El resultado final será algo así:

![image](https://user-images.githubusercontent.com/1631752/111599437-06eaa680-87af-11eb-820d-d312e2b07103.png)

## Desafíos

- Listar las cotizaciones recuperadas desde una API
- Dejar el listado andando y que se vea como en la foto
- Agregar una spinner (en Android, `ProgressBar`) para indicar que la aplicación está cargando. Recordar que las `View` tienen un método de extensión `isVisible` para mostrar u ocultarlas.
- Por defecto, la API nos entrega las monedas ordenadas por capitalización de mercado. Agregarle comportamiento al botón para que las reordene alfabéticamente. Para esto, se pueden guardar una instancia del `adapter` e invocar al método `populate(...)` con la lista ordenada como único argumento.
- Agregar un `ToggleButton` que solo muestre las monedas estables (cuya variación de precio sea menor al 1%)