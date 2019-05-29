PANDOC=pandoc
PANDOC_OPTIONS=--template eisvogel -V lang=es -V author="Grup 3.1: Àlex, Roger i Joan" -V title="Jocs de proves" --resource-path=imatges_JP

SOURCE_DOCS := $(wildcard */*.md)

EXPORTED_DOCS= $(SOURCE_DOCS:.md=.pdf) 


%.pdf: %.md
	$(PANDOC) $(PANDOC_OPTIONS)  -o $@ $<

.PHONY: all clean

all : $(EXPORTED_DOCS)

clean:
	rm */*.pdf
