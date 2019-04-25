#!/bin/bash
shopt -s globstar;
javac -d out FONTS/**/**.java -cp FONTS/ -cp EXE/junit-4.12.jar;
