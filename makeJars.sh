#!/bin/bash
# generador de jars per a drivers de prop
# (c) 2019 Alex Torregrosa 

CPATH="-C out" 
DOMAIN="${CPATH} domain ${CPATH} drivers/GenericDriver.class ${CPATH} utils";
BASEJAR="jar cfm"; 
BASEMANIFEST="Manifest-version: 1.0\nMain-Class: drivers.";


for f in FONTS/drivers/Driver*.java; do
	# Creació del jar
	name="$(echo "$f" | sed 's/FONTS\/drivers\/\([^.]*\).*/\1/')";
	echo -e "${BASEMANIFEST}${name}\n" > manifest.mf;
	jardir="EXE/${name}/";
	if [ ! -d "$jardir" ]; then
		mkdir "$jardir";
	fi
	cmd="${BASEJAR} ${jardir}${name}.jar manifest.mf ${DOMAIN} ${CPATH} drivers/${name}.class"
	$cmd;

	jpdir="jocsProves/${name}/"

	# Crea el directori si no existeix
	if [ ! -d "$jpdir" ]; then
                mkdir "$jpdir";
        fi
done
# Crea JAR junit
# Crea el directori si no existeix
if [ ! -d "EXE/TestJunit/test.jar" ]; then
	mkdir "EXE/TestJunit";
fi
echo -e "${BASEMANIFEST}${name}\n" > manifest.mf;
cmd="jar cf EXE/TestJunit/test.jar ${DOMAIN} ${CPATH} test/domain/M1Test.class"
$cmd;

# Borra el manifest temporal
rm manifest.mf;
