Proyecto Programación Clinica Dental.

La estructura nos deja con 5 paquetes para gestionar los diferentes aspectos de nuestro programa,
Por una parte tenemos el paquete de clientes, en el que la intención es crear objetos del tipo cliente para crear nuestros objetos de tipo cliente y asi guardar los datos y poder asignarles una dentadura más adelante.

Por otro lado, está el paquete DBCustomer, que nos hace la conexión a la base de datos y se encarga de las diferentes tareas de creacion y modificación de registros en la base de datos. 


En tercer lugar, nos queda el paquete dentadura, parte vital de nuestro programa y alrededor de la cual gira todo. Ésta clase nos construye la dentadura de cada cliente en funcion de las intervenciones realizadas. Para no hacerlo demasiado extenso, se han incluído 3 tipos de intervención, a saber, Extracciones, Protesis y Empastes. La clase tiene un constructor por defecto que nos crea una dentadura adulta con todos los dientes diferenciados según la nomenclatura FDI (Iniciales de Federación Dental Internacional) la cual diferencia los dientes según la zona de la boca donde se encuentran (11 - 18 para los dientes situados en la zona superior derecha, 21-28 para los situados arriba a la izquierda, 4
1-48 para los de la zona inferior derecha y finalmente 31-38 para la zona inferior izquierda). ![numeros_de_los_dientes](https://github.com/Agauna13/ProyectoDentistaProgramacion/assets/151865658/69539f2e-9372-477c-8dba-20bc48b2edcc).

La clase Dentadura toma como parámetro el id del diente según la nomenclatura antes explicada y el estado que queremos asignarle, luego recorre el array bidimensional donde hemos guardado la dentadura creada por defecto y asigna el estado al diente pertinente. El método mostar() nos muestra la dentadura que hemos creado por consola de la siguiente manera: 

18 17 16 15 14 13 12 11 21 22 23 24 25 26 27 28 
 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 
 B  B  B  B  B  B  B     B  B  B  B  B  B  B  B 
 B  B  B  B  B  B  B  B  B  B  B  B  B  B  B  B 
 |  |  |  |  |  |  |  |  |  |  |  |  |  |  |  | 
48 47 46 45 44 43 42 41 31 32 33 34 35 36 37 38 

Podemos observar que el diente numero 11 ha sido extraído y el resto de la dentadura conserva el estado [B] porque los presuponemos en buen estado.


En cuarto lugar, he creado una clase llamada DBcustomer que controla la conexión entre el programa y la base de datos. La clase consta de diversos métodos segun los datos que queramos introducir/extraer de la base de datos.


Por último, en la clase presupuesto, nos encargamos de buscar dentro de la dentadura pasada como parametro los diferentes dientes que han sido intervenidos y les asignamos un precio el cual se va sumando y se va acumulando en nuestra variable String cuerpo, el cual será el grupo de conceptos que imprimiremos al cliente para que sepa qué le estamos cobrando en tal caso. Mediante nuestra clase conectora, extraemos los datos personales de nuestros clientes para incluírlos en la parte superior de nuestro presupuesto y finalmente dependiendo del coste total del mismo, llamamos a la clase polimórica alarma para que nos imprima/avise de si un cliente tiene un presupuesto demasiado elevado o si, valorando el tiempo transcurrido desde que se hizo la intervención hasta el momento de hacer el presupuesto, imprimir la alarma de cliente deudor o moroso si han pasado más de 30 días.

Desde la clase main, mediante varios menús podemos navegar las diferentes opciones que nos ofrece el programa para la creación de nuevos clientes o dentaduras. la creación de presupuestos se hace automáticamente una vez creamos una dentadura para facilitar el trabajo a los empleados.

y por último, la clase que nos creará los presupuestos en funcion de las intervenciones hechas a cada dentadura.
