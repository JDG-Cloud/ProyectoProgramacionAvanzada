package co.edu.uniquindio.CommuSafe.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo") // Prefijo común para todas las rutas del controlador
public class SaludoControlador {

    //metodo de ruta http
    @GetMapping //este metodo responde a una peticion del servidor o acceso a una ruta vacia
    public String saludar(){
        return "Hola, bienvenido a la página";
    }

    @GetMapping("/{nombre}") //asignacion a la variable del metodo
    public String saludarNombre(@PathVariable String nombre){
        return "Hola %s, bienvenido a la aplicación".formatted(nombre);

    }

}
