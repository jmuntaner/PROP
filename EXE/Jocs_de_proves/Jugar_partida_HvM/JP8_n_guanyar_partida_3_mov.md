# JP8: Jugar partida humà contra màquina de tres moviments

## Tipus JP

Normal

## Efectes estudiats

Es pot jugar i guanyar una partida humà contra màquina de tres moviments atacant.

## Entrada

Abans d'executar l'aplicació, crear a la carpeta EXE (O la carpeta on es trobi el jar) una carpeta anomenada `bases` (Esborrar-la si ja existeix) i al seu interior copiar els continguts de `bases_JP` d'aquest directori.

Executar el programa i iniciar sessió (a perfil) amb les següents dades:

- **Usuari:** Human
- **Contrasenya:** 1234

Seleccionar el problema "Problema 4" i prémer "Jugar HvM". Seleccionar com a oponent a "Xicu (M1)". Prémer "Atacar".

S'iniciarà la partida. Realitzar els següents moviments:

- Matar el peó del costat del rei amb la reina (de dalt) fent escac.
- Segons el que hagi fet la màquina:
    - Si la màquina menja amb el cavall, menjar el cavall amb la reina.
    La màquina mou el rei a l'esquerra (única) i fer mat movent la reina a l'última casella de la columna
    - Si la màquina mou el rei a la esquerra, moure la dama a l'última casella de la fila. 
    La màquina es taparà amb el cavall (única) i fer mat movent l'altre reina dos caselles sota la de la cantonada (en diagonal al rei).

Repetir el procediment seleccionant a "Barja (M2)" com oponent.

## Resposta esperada

Apareix la pantalla de victoria, indicant com a nom de jugador guanyador "Human".

Apareix la pantalla de victoria, indicant com a nom de jugador guanyador "Human".

## Captures de pantalla de la sortida

![Selecció de l'oponent (M1)](../imatges_JP/hvm_oponent_1_p4.png)

![Selecció de defensar](../imatges_JP/hvm_atacant_4.png)

![Estat inicial del tauler](../imatges_JP/hvm_partida_ini_4_0.png)

![Opcions primer moviment](../imatges_JP/hvm_partida_4_1.png)

![Estat tauler després del primer moviment de la maquina](../imatges_JP/hvm_partida_4_2.png)

![Opcions segon moviment](../imatges_JP/hvm_partida_4_3.png)

![Estat tauler després del segon moviment de la maquina](../imatges_JP/hvm_partida_4_4.png)

![Opcions tercer moviment](../imatges_JP/hvm_partida_4_5.png)

![Pantalla de victòria Human](../imatges_JP/fi_victoria_hvm.png)

![Selecció de l'oponent (M2)](../imatges_JP/hvm_oponent_2_p4.png)

![Selecció de defensar](../imatges_JP/hvm_atacant_4.png)

![Estat inicial del tauler](../imatges_JP/hvm_partida_ini_4_0.png)

![Opcions primer moviment](../imatges_JP/hvm_partida_4_1.png)

![Estat tauler després del primer moviment de la maquina](../imatges_JP/hvm_partida_4_2_1.png)

![Opcions segon moviment](../imatges_JP/hvm_partida_4_2_2.png)

![Estat tauler després del segon moviment de la maquina](../imatges_JP/hvm_partida_4_2_3.png)

![Opcions tercer moviment](../imatges_JP/hvm_partida_4_2_4.png)

![Pantalla de victòria Human](../imatges_JP/fi_victoria_hvm.png)
