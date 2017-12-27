/* Rhea Lingaiah
rlingaia@ucsc.edu
12M Lab4*/


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w);
int main(int argc, char* argv[]){
	FILE* in;        // handle for input file                  
	FILE* out;       // handle for output file                 
	char* line;      // string holding input line
	char* caseType;
	char* num;
	char* punc;
	char* whitespace;
	int n=1;

	// check command line for correct number of arguments 
   if(argc!=3){
      printf("Usage: %s input-file outputfile \n", argv[0]);
      exit(EXIT_FAILURE);

   }
   /*throws an error if the input file is null*/
   in = fopen(argv[1], "r");
   if(in ==  NULL){
      printf("Unable to read input file \n ");
      exit(EXIT_FAILURE);
   }
        /*throws an error if the output file is null*/
   out = fopen(argv[2],"w");
   if(out == NULL){
      printf("Unable to write to output file \n");
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   caseType = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   num = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punc = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   whitespace = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && caseType!=NULL && num!=NULL && punc!=NULL && whitespace!=NULL);

   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_chars(line, caseType,num,punc,whitespace);
      fprintf(out, "line %d contains: \n", n);
      if(strlen(caseType)>1){/*all if statements check if the are multiple characters in the catogory*/
         fprintf(out,"%d alphabetic characters: %s\n",(int)strlen(caseType),caseType);
      }
      else{
         fprintf(out,"%d alphabetic character: %s\n",(int)strlen(caseType),caseType);
      }
      if(strlen(num)>1){
         fprintf(out,"%d numeric characters: %s\n",(int)strlen(num),num);
      }
      else{
         fprintf(out,"%d numeric character: %s\n",(int)strlen(num),num);
      }
      if(strlen(punc)>1){
         fprintf(out,"%d punctuation characters: %s\n",(int)strlen(punc),punc);
      }
      else{
         fprintf(out,"%d punctuation character: %s\n",(int)strlen(punc),punc);
      }
      if(strlen(whitespace)>1){
         fprintf(out,"%d whitespace characters: %s\n",(int)strlen(whitespace),whitespace);
      }
      else{
         fprintf(out,"%d whitespace character: %s\n",(int)strlen(whitespace),whitespace);
      } 
      
      n++;
   }

   // free heap memory 
   free(line);
   free(caseType);
   free(num);
   free(punc);
   free(whitespace);

   // close input and output files 
   fclose(in);
   fclose(out);
   return EXIT_SUCCESS;
}
void extract_chars(char* s, char* a, char* d, char* p, char* w){
	int b=0;
	int c=0;
	int e=0;
	int f=0;
	int g=0;
	while(s[b]!='\0' && b<MAX_STRING_LENGTH){
      if( isupper((int)s[b]) ) {
      a[c] = s[b];
      c++;
      }
      else if(isalpha((int)s[b])){
      a[c]=s[b];
      c++;
      }
      else if(isdigit((int)s[b])){
      d[e]=s[b];
      e++;
      }
      else if(ispunct((int)s[b])){
      p[f]=s[b];
      f++;
      }
      else{
      w[g]=s[b];
      g++;
      }
      b++;
   }
   a[c] = '\0';
   d[e] ='\0';
   p[f] ='\0';
   w[g] ='\0';
}
