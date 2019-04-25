#!/bin/bash

#rm -rf EXE/*;
CPATH="-C out/production/prop-qp19" 
DOMAIN="${CPATH} domain ${CPATH} drivers/GenericDriver.class";
BASEJAR="jar cfm"; 
BASEMANIFEST="Manifest-version: 1.0\nMain-Class: drivers.";
BASICREADME="# DNAME\n
Utilitzacio:\n
\`\`\`sh\n
java -jar DNAME.jar\n
\`\`\`\n
Excució jocs de proves:\n
\`\`\`sh\n
java -jar DNAME.jar -q < jp_in.txt > sortida.txt\n
\`\`\`\n"


for f in FONTS/drivers/Driver*.java; do
	# Creació del jar
	name="$(echo "$f" | sed 's/FONTS\/drivers\/\([^.]*\).*/\1/')";
	echo -e "${BASEMANIFEST}${name}\n" > manifest.mf;
	jardir="EXE/${name}/";
	mkdir "$jardir";
	cmd="${BASEJAR} ${jardir}${name}.jar manifest.mf ${DOMAIN} ${CPATH} drivers/${name}.class"
	$cmd;

	# Creació de README
	#echo -e "${BASICREADME//DNAME/$name}" > "${jardir}README.md";
	
	### Jocs de proves

	jpdir="jocsProves/${name}/"
	jpin="${jpdir}jp_in.txt";
	jpout="${jpdir}jp_out.txt";
	jpex="${jpdir}jp_explicat.txt";

	# Crea el directori i arxius si no existeixen
	if [ ! -d "$jpdir" ]; then
                mkdir "$jpdir";
        fi
	if [ ! -f "$jpin" ]; then
		touch "$jpin";
	fi
	if [ ! -f "$jpout" ]; then
                touch "$jpout";
        fi
	if [ ! -f "$jpex" ]; then
                touch "$jpex";
        fi
	# Copia els jocs de proves
	#cp -r "${jpdir}." "${jardir}";
done
# Borra el manifest temporal
rm manifest.mf;
