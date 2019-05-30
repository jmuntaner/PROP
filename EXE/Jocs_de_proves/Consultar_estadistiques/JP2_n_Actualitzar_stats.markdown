# JP2: Actualitzar Estadístiques

## Tipus JP

Normal

## Efectes estudiats

Les estadístiques s'actualitzen correctament.

## Entrada

Abans d'executar l'aplicació, crear a la carpeta EXE (O la carpeta on es trobi el jar) una carpeta anomenada `bases` 
(esborrar-la si ja existeix) i al seu interior copiar els continguts de `bases_JP` d'aquest directori.

Executar l'aplicació i entrar a la secció "Perfil".

Registrar un nou usuari amb nom "testStats".

Entrar a la secció "Jugar", procedir a carregar el problema `Problema Test` i jugar 3 partides diferents tornant a la secció del
"Perfil" per tal de consultar les estadístiques. Les partides es faran una sense acabar 
(prement tornar al entrar al problema), una acabant però no guanyant (moure només el rei)
i l'última acabant i guanyant (movent la torre a la columna del rei).




## Resposta esperada

Les estadístiques s'actualitzen correctament, primer augmentant en 1 els intents, 
després els acabats i finalment els guanyats, quedant "Intents: 3", "Acabats: 2/3" i "Guanyats: 1/2".

## Captures de pantalla de la sortida

![Estadistiques buides](../imatges_JP/stats_buides.png)

![Estadistiques intent](../imatges_JP/stats_intent.png)

![Estadistiques acabat](../imatges_JP/stats_acabat.png)

![Estadistiques guanyat](../imatges_JP/stats_guanyat.png)
