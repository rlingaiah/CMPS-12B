
#Rhea Lingaiah
#rlingaia@ucsc.edu
#12M
#7/4/17	
JAVASRC	= HelloUser.java HelloUser2.java
SOURCES	= README Makefile $(JAVASRC)
MAINCLASS	=HelloUser
CLASSES	=HelloUser.class
JARFILE	=HelloUser
SUBMIT	=submit cmps012b-pt.u17 lab1
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
