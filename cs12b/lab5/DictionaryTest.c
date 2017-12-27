/*Rhea Lingaiah
 * rlingaia@ucsc.edu
 * 12M lab5*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"hello","there","viewer","im","rhea","testing"};
   char* word2[] = {"soo","cool","aaaaa","gah","ya","uh"};
   int i;

   for(i=0; i<6; i++){
      insert(A, word1[i], word2[i]);
   }

   printDictionary(stdout, A);

   for(i=0; i<6; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   delete(A, "viewer");
   delete(A, "hello");

   printDictionary(stdout, A);

   for(i=0; i<6; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   printf("%d\n", size(A));
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
//-----------------------------------------------------------------------------
