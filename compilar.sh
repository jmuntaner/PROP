#!/bin/bash
# Compila codi java
# (c) 2019 Alex Torregrosa
if [ ! -d out ]; then
                mkdir out;
        fi
shopt -s globstar;
javac -d out FONTS/**/**.java -cp FONTS/ -cp EXE/junit-4.12.jar;
