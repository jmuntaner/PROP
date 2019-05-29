PANDOC=pandoc
PANDOC_OPTIONS=--pdf-engine xelatex -f markdown-implicit_figures --listings --resource-path=imatges_JP

SOURCE_DOCS := $(wildcard */*.md)

EXPORTED_DOCS= $(SOURCE_DOCS:.md=.pdf) 


%.pdf: %.md
	$(PANDOC) $(PANDOC_OPTIONS)  -o $@ $<

.PHONY: all 

all : $(EXPORTED_DOCS)
