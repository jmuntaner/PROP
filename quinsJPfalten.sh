#!/bin/bash

for f in jocsProves/* 
do
	if [ ! -s "${f}/jp_in.txt" ]; then
		echo "$f";
	fi
done
