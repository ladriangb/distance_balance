# Prueba práctica

El equipo de data science de Alkanza se encuentra analizando datos regionales para recomendar portafolios de inversión de una mejor manera. Para eso se encuentra con la necesidad de medir el índice de "desbalance en distancia" entre centros médicos desde un punto geográfico con un cierto radio. Alkanza necesita una herramienta que pueda calcular el **mínimo índice de "desbalance en distancia"** ​ siendo este definido como:

* a1 , a2 , ... , an son las distancias (números enteros) de cada uno de los centros médicos en un radio dado, ​ a un punto de referencia​
* El conjunto de centros médicos ​ se particiona​ en los conjuntos de índices D y B , donde D = { i 1 , i 2 , ... , i k } son los centros médicos "desbalanceados" y  B = { i 1 , i 2 , i 3 , ..., i n }∖D son los "balanceados"
* La medida de "desbalance en distancia" es:

```
f (D) =   ∑      ∑     | ai − aj |
         i∈D    j∈B
```

Nota: A ∖B es la diferencia entre los conjuntos A y B


>-----------------------------------------------------------

No me quedo claro cual es el criterio para decir que un centro hospitalario esta o no balanceado, 
tambien intente buscar la informacion los centros hospitalarios en el radio seleccionado x el usuario usando las herramientas de 
googleAPI pero estas no estan disponible en la capa gratuita sino solo 1 peticion al dia y la gaste sin darme cuenta.



para ver la app por favor visite   https://distance-balance.herokuapp.com/ 