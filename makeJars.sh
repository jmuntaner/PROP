#!/bin/bash
# generador de jars per a drivers de prop
# (c) 2019 Alex Torregrosa 

CPATH="-C out" 
BASEMANIFEST="Manifest-version: 1.0\nMain-Class: views.Escacs";
cd out;
echo -e "${BASEMANIFEST}" > manifest.mf;
if [ -e "res" ]; then
	rm res;
fi
ln -s ../res res;
jar cmf manifest.mf  ../EXE/Escacs.jar  domain views utils controllers data res; 


# Borra el manifest temporal
rm manifest.mf;
cd ..;
