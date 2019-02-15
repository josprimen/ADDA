# ADDA
Ligas de adda, etc.

Ejercicio de Algoritmos Genéticos y Programación Lineal

Problema 1 – Problema de los alimentos 
 
Un fabricante de alimentos para animales está produciendo una mezcla de alimento para ganado vacuno. 
Dicha mezcla contiene una serie de ingredientes activos y un relleno para proporcionar volumen. 
Cada ingrediente activo aporta una cantidad de nutrientes y tiene un coste asociado. 
El relleno no aporta nutrientes, y su coste se considera despreciable. 
 
Se desea determinar qué cantidad (cuántos gramos) de cada ingrediente activo es necesario incluir por kilo de alimento para minimizar costes, teniendo en cuenta que cada kilo de alimento debe contener una cantidad mínima de nutrientes concreta. Los gramos a incluir deben ser cantidades enteras. 
 
Como información del problema se conoce la lista de nutrientes (denominada nutrientes), donde cada nutriente tiene las propiedades (1) código, que es su posición en la lista nutrientes, y (2) cantidadMinimaPorKilo, que es la mínima cantidad de dicho nutriente que debe incluirse en cada kilo de comida (especificada en gramos).  
 
También se conoce la lista de ingredientes activos (denominada ingredientesActivos). Cada ingrediente consta de las siguientes propiedades: (1) código, que es su posición en la lista ingredientesActivos, (2) cantidadNutrientes, que es una lista de enteros de forma que en la posición i-ésima aparece la cantidad del nutriente i que aporta dicho ingrediente (especificada en gramos de nutriente por gramos de ingrediente activo), y (3) coste, que es el coste de dicho ingrediente por gramo.  
 
Por ejemplo, para un problema definido por los siguientes nutrientes: 
 
  código cantidadMinimaPorKilo (en gramos) 
  0          90 
  1          50 
  2          20 
  3           2 
 
Y por los siguientes ingredientes activos: 
 
código cantidadNutrientes (en gramos de nutriente por gramos de ingrediente activo) Coste (euros por gramo) 
                      0     1      2       3      
0                     0.1    0.08   0.04     0.01                                      0.04 
1                     0.2    0.15   0.02     0                                         0.06 
 
 la solución óptima consiste en incluir 368 gramos del ingrediente 0, y 266 gramos del ingrediente 1, con un coste de 30.68 euros.  
 
 SE PIDE(*): (1) Completar la ficha de descripción del problema. (2) Resolver el problema por PL o PLI, para ello: a. Indique razonadamente si es adecuado usar PL ó PLI . b. Completar la ficha de descripción de la solución mediante programación lineal. Justifique por qué ha incluido cada variable y cada restricción. c. Genere un archivo txt (o dos si le es más sencillo hacer la lectura posterior) con los datos del escenario de entrada de forma similar a como se ha realizado en las clases de prácticas para otros problemas. d. Desarrolle un proyecto que resuelva el problema especificado por la técnica indicada. Tenga en cuenta que debe dar una implementación general que genere la solución requerida para cualquier problema de entrada, y no sólo para el escenario concreto que se proporciona en este enunciado. e. Dicho proyecto debe incluir un test de prueba que genere la solución para el escenario previamente descrito en el enunciado. Debe entregar tanto el archivo en formato LPSolve generado, como la solución obtenida para dicho escenario.  (3) Resolver el problema mediante AG, para ello: a. ¿Qué tipo o tipos de cromosomas son los más adecuados para resolver el problema y por qué? b. Completar la ficha de descripción de la solución mediante AG. c. Desarrolle un proyecto que resuelva el problema especificado por la técnica indicada. d. Dicho proyecto debe incluir un test de prueba que genere la solución para el escenario previamente descrito en el enunciado. 
