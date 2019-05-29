---
mainfont: Roboto
monofont: DejaVu Sans Mono
header-includes:
  - \lstset{breaklines=true}
  - \lstset{extendedchars=true}
  - \lstset{tabsize=2}
  - \lstset{columns=fixed}
  - \lstset{showstringspaces=false}
  - \lstset{frame=single}
---

# JP2: Carregar arxiu de FENs

## Preparació

Dirigir-se a la carpeta `EXE` i esborrar (si existeix) la carpeta `base`

```bash
> cd EXE
> rm -rf bases
```

## Execució

Executar Escacs.jar:

```bash
> java -jar Escacs.jar
```

Apareixerà la pantalla principal:

![Pantalla Principal](../imatges_JP/pantalla_principal.png)

Seleccionar la opció `Jugar`, apareixerà una llista buida:

![Llista de Problemes Buida](../imatges_JP/llista_buida.png)

Fer clic a `Carregar Arxiu`, apareixerà un buscador d'arxius, on cal seleccionar l'arxiu associat aj joc de proves.

![Buscador d'arxiu](../imatges_JP/busca_arxiu.png)

Es carregaran els FENs de l'arxiu seleccionat. Comprovar comparant amb les captures de cada joc de proves.

Per a cada joc de proves, seguir el procediment anterior seleccionant l'arxiu donat.

### Jocs de proves senzills

```
jp_s_1.fendb
```

EN PROCES
