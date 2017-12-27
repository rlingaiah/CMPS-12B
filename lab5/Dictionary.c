/*Rhea Lingaiah
 * rlingaia@ucsc.edu
 * 12M lab5*/


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include "Dictionary.h"
/*Private Functions */
/* creates the node data type*/
typedef struct NodeObj{
	char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(char* k, char* v) {
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N->key = k;
	N->value = v;
	N->next = NULL;
	return(N);
}
void freeNode(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		free(*pN);
		*pN = NULL;
	}
}
typedef struct DictionaryObj{
	Node head;
	int numItems;
}DictionaryObj;

Node findKey(Node H, char* targetKey){
while(H != NULL){
	if(strcmp(H->key, targetKey)==0){
		break; 
	}
	H = H->next;
}
return H;
}
void myString(FILE* out, Node H){
	if(H!=NULL){
	myString(out, H->next);
	fprintf(out, "%s %s\n", H->key, H->value);
	 }
	}
void deleteAll(Node N){
	if (N!=NULL){
		deleteAll(N->next);
		freeNode(&N); 
	}
	}

/*Public*/
/*Constructor for the Dictionary ADT */
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D!=NULL);
	D->head = NULL;
	D->numItems = 0;
	return D;	
}
/*free's heap memory for the Dicitonary ADT*/
void freeDictionary(Dictionary* pD){
	if(pD!=NULL && *pD!=NULL){
		if(!isEmpty(*pD))
			makeEmpty(*pD);
			free(*pD);
		*pD = NULL;
	}
}
/*isEmpty
 
 */ 
int isEmpty(Dictionary D){
	if( D == NULL){
		fprintf(stderr,"Dictionary Error: calling isEmpty() on NULL DICTIONARY\n");
		exit(EXIT_FAILURE);
	}
	return(D->numItems==0);
}
/*size

 */
int size(Dictionary D){
	return(D->numItems);
}
/*lookup
 
 */
char* lookup(Dictionary D, char* k){
	Node N= findKey(D->head,k);
	if(N== NULL){
		return NULL;
	}
		else{
			return N->value;
		}
}
/*insert

 */
void insert(Dictionary D, char* k, char* v){
	Node N;
	if(findKey(D->head, k)!=NULL){
		fprintf(stderr, "DICTIONARY Error: calling insert() when key alreadyexists\n");
		exit(EXIT_FAILURE);
	}
	N = newNode(k, v);
	N->next = D->head;
	D->head = N;
	N = NULL;
	D->numItems++;
}
	
/*delete
 
 */
void delete(Dictionary D, char* k){
	Node N;
	if(findKey(D->head, k)==NULL){
		fprintf(stderr, "Dictionary Error: calling delete() on EmptyDictionary\n");
		exit(EXIT_FAILURE);
	}
	if(findKey(D->head, k)==D->head){
		N = D->head;
		D->head = D->head->next;
		N->next = NULL;
	}
	else{
		N = findKey(D->head, k);
		Node prev = D->head;
		Node temp = D->head->next;
		while(temp !=N){
			temp=temp->next;
			prev=prev->next;
		}
		prev->next=N->next;
		N->next=NULL;
	}
	D->numItems--;
	freeNode(&N);
}
/*makeEmpty
 
 */
void makeEmpty(Dictionary D){
	if( D==NULL ){
		fprintf(stderr,"DICTIONARY Error: calling makeEmpty() on NULL Dictionaryreference\n");
		exit(EXIT_FAILURE);
	}
	if(D->numItems==0){
		fprintf(stderr, "DICTIONARY Error: calling makeEmpty() on emptyDictionary\n");
		exit(EXIT_FAILURE);
	}
	deleteAll(D->head);
	D->head = NULL;
	D->numItems = 0;

}
/*printDictionary

 */
void printDictionary(FILE* out, Dictionary D){	
	if( D==NULL )
		{fprintf(stderr,"DICTIONARY Error: calling printDictionary() on NULL Dictionaryreference\n");
	exit(EXIT_FAILURE);
}
	myString(out,D->head);

}
