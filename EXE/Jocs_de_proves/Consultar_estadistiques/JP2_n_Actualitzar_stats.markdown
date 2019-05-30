# JP2: Actualitzar Estadístiques

## Tipus JP

Normal

## Efectes estudiats

Les estadístiques s'actualitzen correctament.

## Entrada

Executar l'aplicació i entrar a la secció "Perfil".

Registrar un nou usuari amb nom "testStats".

Entrar a la secció "Jugar", procedir a carregar un problema i jugar 3 partides diferents tornant a la secció del
"Perfil" per tal de consultar les estadístiques. Les partides es faran una sense acabar 
(prement tornar al entrar al problema), una acabant però no guanyant 
i l'última acabant i guanyant.


## Resposta esperada

Les estadístiques s'actualitzen correctament, primer augmentant en 1 els intents, 
després els acabats i finalment els guanyats, quedant "Intents: 3", "Acabats: 2/3" i "Guanyats: 1/2".

## Captures de pantalla de la sortida

![Estadistiques buides](../imatges_JP/stats_buides.png)

![Estadistiques intent](../imatges_JP/stats_intent.png)

![Estadistiques acabat](../imatges_JP/stats_acabat.png)

![Estadistiques guanyat](../imatges_JP/stats_guanyat.png)
