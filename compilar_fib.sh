#!/bin/bash
# Compila codi java
# (c) 2019 Alex Torregrosa
if [ ! -d out ]; then
                mkdir out;
        fi
shopt -s globstar;
/usr/java/jdk1.8.0_45/bin/javac -d out FONTS/**/**.java -cp FONTS/ 
