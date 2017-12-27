
#Rhea Lingaiah
#rlingaia@ucsc.edu
#12M
#7/4/17	
JAVASRC	= Search.java
SOURCES	= README Makefile $(JAVASRC)
MAINCLASS	=Search
CLASSES	=Search.class
JARFILE	=Search
SUBMIT	=submit cmps012b-pt.u17 pa2
all:	$(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES)	$(JARFILE)
submit:	$(SOURCES)
	$(SUBMIT)	$(SOURCES)
