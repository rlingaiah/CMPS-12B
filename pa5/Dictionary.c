//Rhea Lingaiah
//rlingaia@ucsc.edu
//PA5
//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize = 101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
   unsigned int result = 0xBAE86554;
   while (*input) { 
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = calloc(strlen(x)+1, sizeof(char));
   strcpy(N->key, x);
   N->value = calloc(strlen(y)+1, sizeof(char));
   strcpy(N->value, y);
   N->next = NULL;
   return(N);
}
// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
	  Node N = *pN;
	  free(N->key);
	  free(N->value);
	  free(*pN);
      *pN = NULL;
   }
}
typedef struct DictionaryObj{
   Node* table;
   int numItems;
}DictionaryObj;

//private helper function
Node findKey(Dictionary D, char* x){
   Node N = D->table[hash(x)];
      while(N != NULL && strcmp(N->key, x) != 0) {
	       N = N->next;
	  }
   return N;
}

Node findPrev(Dictionary D, char* x){
   Node N = D->table[hash(x)];
      while(N->next != NULL && strcmp(N->next->key, x) != 0) {
	    N = N->next;
	  }
   return N;
}
//deletes and frees the nodes
void deleteAll(Node N){
   if( N!=NULL ){
      deleteAll(N->next);
      freeNode(&N);
   }
}

// public ---------------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D != NULL);
   D->table = calloc(tableSize, sizeof(Node*));
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   Dictionary D = *pD;   
   makeEmpty(D);
   free(*pD);
   *pD = NULL;
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL){
      fprintf(stderr, "Dictionary Error: calling isEmpty() on Null Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if( D==NULL){
      fprintf(stderr, "Dictionary Error: calling size() on Null Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    if( D==NULL){
       fprintf(stderr, "Dictionary Error: calling lookup() on Null Dictionary reference\n");
       exit(EXIT_FAILURE);
    }
    if( D->numItems == 0){
       fprintf(stderr, "Dictionary Error: calling lookup() on empty Dictionary\n");
       exit(EXIT_FAILURE);
    }
    Node N = findKey(D, k);
    if(N != NULL) return N->value;
    else return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems > 0 && lookup(D, k) != NULL){
      fprintf(stderr, "Dictionary: insert() cannot insert duplicate keys.");
      exit(EXIT_FAILURE);
   }
   else if(D->table[hash(k)] == NULL){
      Node N = newNode(k, v);
      D->table[hash(k)] = N;
      D->numItems++;
   }
   else{
      Node N = D->table[hash(k)];
      while(N->next != NULL) N = N->next;
      N->next = newNode(k,v);
      D->numItems++;
   }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node N = findKey(D, k);
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numItems==0 ){
      fprintf(stderr, "Dictionary Error: calling delete() on empty Dictionary\n");
	  exit(EXIT_FAILURE);
   }
   if(D->table[hash(k)] != NULL && D->table[hash(k)]->next == NULL){
      freeNode(&N);
      D->table[hash(k)] = NULL;
      D->numItems--;
   }
   else if(lookup(D,k) != NULL && N == D->table[hash(k)]){
      D->table[hash(k)] = N->next;
      freeNode(&N);
      D->numItems--;
   }
   else{
      Node P = findPrev(D, k);
      P->next = N->next;
      freeNode(&N);
      D->numItems--;
   }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, "Dictionary Error: calling makeEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i = 0; i < tableSize; i++){
      deleteAll(D->table[i]);
      D->table[i] = NULL;
   }
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i = 0; i < tableSize; i++){
      if(D->table[i] != NULL){
         for(Node N = D->table[i]; N!=NULL; N=N->next) fprintf(out, "%s %s\n", N->key, N->value);
      fprintf(out, "\n");
      }
   }
}
