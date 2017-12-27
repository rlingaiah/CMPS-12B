
#Rhea Lingaiah
#rlingaia@ucsc.edu
#12M
#7/4/17	
JAVASRC	=	Recursion.java
SOURCES	=	README Makefile $(JAVASRC)
MAINCLASS =	Recursion
CLASSES	=	Recursion.class
JARFILE =	Recursion
SUBMIT	=	submit cmps012b-pt.u17 pa1
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
