Proyecto Programación de Semana Santa.

El proyecto consiste en un pequeño programa que pueda servir para generar presupuestos en una clínica dental (de manera muy basica, de momento).

Consiste en 2 paquetes con 4 clases:
    El primer paquete es el llamado Dentadura, consiste de 3 clases:
        La clase Dentadura, que recibe como parámetro un ID y un estado y nos genera un pequeño mapa de una boca según el sistema de numeración FDI
        que utilizan los odontólogos para orientarse dentro de una madíbula. Lo que nos devuelve esta clase, es un mapa dónde, por defecto, los dientes
        tienen el estado "B" bien o que no necesitan intervención y en la posición de los dientes pasados como parámetro, nos imprime el estado del 
        diente en cuestión según esta e[X]traído, [E]mpastado o si ha sido necesaria una [P]rótesis.

        La clase Diente se encarga de asignar correctamente los parámetros pasados y nos hace de intermediario entre las clases privadas de Dentadura y 
        la clase Main.

        La clase Main que es la que recibe los constructores con sus parámetros para finalmente imprimirnos el resultado.

    El segundo Paquete llamado Presupuestos contiene sólo la clase GestionDentaddura, que recibiendo como parámetro la dentadura que hemos modificado,
    obtiene el estado de todos los dientes que han sido intervenidos para generar un rpesupuesto e imprimirnos un desglose con el precio por unidad
    intervenida y el precio final del presupuesto.
