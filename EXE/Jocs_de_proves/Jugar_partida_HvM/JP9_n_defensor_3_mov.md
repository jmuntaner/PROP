# JP9: Jugar partida de tres moviments humà defensant contra màquina

## Tipus JP

Normal

## Efectes estudiats

Es pot jugar una partida humà contra màquina de tres moviments defensant.

## Entrada

Abans d'executar l'aplicació, crear a la carpeta EXE (O la carpeta on es trobi el jar) una carpeta anomenada `bases` (Esborrar-la si ja existeix) i al seu interior copiar els continguts de `bases_JP` d'aquest directori.

Executar el programa i iniciar sessió (a perfil) amb les següents dades:

- **Usuari:** Human
- **Contrasenya:** 1234

Seleccionar el problema "Problema 4" i prémer "Jugar HvM". Seleccionar com a oponent a "Xicu (M1)". Prémer "Defensar".

S'iniciarà la partida. La màquina menjarà el peó amb la dama fent escac. Realitzar el següent moviment:

- Menjar la dama amb el cavall. -> la màquina menjarà el cavall amb la dama fent escac.
- Moure el rei a l'esquerra. -> la màquina farà mat.

Repetir el procediment seleccionant a "Barja (M2)" com oponent.

## Resposta esperada

Apareix la pantalla de victoria, indicant com a nom de jugador guanyador "Xicu".

Apareix la pantalla de victoria, indicant com a nom de jugador guanyador "Barja".

## Captures de pantalla de la sortida

![Selecció de l'oponent (M1)](../imatges_JP/hvm_oponent_1_p4.png)

![Selecció de defensar](../imatges_JP/hvm_atacant_4.png)

![Estat inicial del tauler abans del moviment de la màquina](../imatges_JP/hvm_partida_ini_4_1.png)

![Estat inicial del tauler després del moviment de la màquina](../imatges_JP/hvm_defensor_p4_1.png)

![Opcions primer moviment](../imatges_JP/hvm_defensor_p4_2.png)

![Estat del tauler després del moviment de la màquina](../imatges_JP/hvm_defensor_p4_3.png)

![Opcions primer moviment](../imatges_JP/hvm_defensor_p4_4.png)

![Pantalla de derrota Xicu](../imatges_JP/fi_derrota_xicu.png)

![Selecció de l'oponent (M2)](../imatges_JP/hvm_oponent_2_p4.png)

![Selecció de defensar](../imatges_JP/hvm_atacant_4.png)

![Estat inicial del tauler després del moviment de la màquina](../imatges_JP/hvm_partida_ini_4_2.png)

![Estat inicial del tauler després del moviment de la màquina](../imatges_JP/hvm_defensor_p4_1.png)

![Opcions primer moviment](../imatges_JP/hvm_defensor_p4_2.png)

![Estat del tauler després del moviment de la màquina](../imatges_JP/hvm_defensor_p4_3.png)

![Opcions primer moviment](../imatges_JP/hvm_defensor_p4_4.png)

![Pantalla de derrota Barja](../imatges_JP/fi_derrota_barja.png)
