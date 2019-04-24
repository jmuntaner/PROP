# DriverUsuari

## Operativa

### Execució interactiva:

```sh

java -jar DriverUsuari.jar

```

Es presenta un menu amb opcions per a utilitzar les diferents parts de la classe.

### Excució jocs de proves:

```sh

java -jar DriverUsuari.jar -q < jp_in.txt > sortida.txt

```

Per a comprobar la validesa del resultat, executar:

```sh
diff sortida.txt jp_out.txt
```

## Objecte de la prova

Provar el correcte funcionament de la classe Usuari.

## Altres elements integrats a la prova

Cap.

## Drivers

DriverUsuari: permet fer crides als metodes de la classe Usuari utilitzant la linea de comandes.

## Stubs

Cap, ja que utilitzem testing incremental.

## Valors i Efectes estudiats

Hem utilitzat la metodologia de caixa gris, generant entrades que comproven els casos generals i els limits, i monitoritzant que es llencin excepcions en cas necessari. Mes informacio sobre els tests concreats a l'arxiu `jp_explicat.txt`.
