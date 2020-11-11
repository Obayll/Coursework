/* Jordan Ditzler */
/* G00967092      */
/* ECE 446        */
/* FALL 2020      */

#include <stdio.h>
#include <stdlib.h>
#include "hw2.h"

/* Inserts node at the head of the list */
void insert_entry(struct node** head, int *bib, int *time, char *name,
	char *school)
{
	/* Allocate memory for new node */
	struct node* newNode = (struct node*)malloc(sizeof(struct node));
	
	/* Add data */
	newNode->bib = *bib;
	newNode->time = *time;
	newNode->name = name;
	newNode->school = school;
	
	/* The prev of head will always be NULL */
	newNode->prev = NULL;
	
	/* If list is populated */
	if (*head != NULL)
	{
		printf("List is populated; adding to list.\n");
		
		/* Update previous head pointers */
		newNode->next = *head;
		(*head)->prev = newNode;
	}
	/* If list is empty */
	else
	{
		printf("List is empty; making new list.\n");
		
		/* Make the first entry in the list */
		newNode->next = NULL;
	}
	
	/* Update head pointer */
	*head = newNode;
}

/* Removes node at the head of the list */
void remove_entry(struct node** head, struct node* remove)
{
	/* If list is populated */
	if (*head != NULL)
	{
		printf("List is populated; removing.\n");
		
		/* Sets head to next in list */
		*head = remove->next;
		
		/* Frees old head node */
		free(remove);
	}
	/* If list is empty */
	else
	{
		printf("List is empty; cannot remove.\n");
	}
}

/* Prints the entire linked list, starting from the head */
void print_list(struct node** head)
{
	/* If list is populated */
	if (*head != NULL)
	{
		printf("Printing list.\n");
		
		/* Dummy pointer for iteration */
		struct node* iterNode = *head;
		
		/* Loops through list and prints contents */
		while (iterNode != NULL)
		{
			printf("Bib number: %i\n", iterNode->bib);
			printf("Runner    : %s\n", iterNode->name);
			printf("School    : %s\n", iterNode->school);
			printf("Time      : %i\n", iterNode->time);
			iterNode = iterNode->next;
		}
		
		/* Frees dummy pointer memory */
		free(iterNode);
		iterNode = NULL;
		
		printf("Printing complete.\n");
	}
	/* If list is empty */
	else
	{
		printf("List is empty; cannot print.\n");
	}
}

int main(void)
{
	/* Head node pointer */
	struct node* head = NULL;
	
	/* Function pointers */
	void (*insert)(struct node**, int*, int*, char*, char*) = &insert_entry;
	void (*remove)(struct node**, struct node*) = &remove_entry;
	void (*print)(struct node**) = &print_list;
	
	/* List data to be added for testing */
	int bib[8] = {1,2,3,4,5,6,7,8};
	char name[][8] = {"Mark","David","Guy","Mathew","James","Garry","Ash",
		"Marcus"};
	char school[][8] = {"UCLA","ASA","VT","UVA","VCU","GMU","JMU","UA"};
	int time[8] = {200, 101, 329, 189, 99, 310, 289, 178};
	
	
	/* Start test */
	int i, j;
	for (i = 1; i < 3; i++)
	{
		remove(&head, head);
		
		for (j = 0; j < 8; j++)
		{
			insert(&head, &bib[j], &time[j], name[j], school[j]);
		}
		print(&head);
		
		for (j = 0; j < 8; j++)
		{
			remove(&head, head);
		}
		print(&head);
		
		printf("Test round #%i complete.\n", i);
	}
	
	return 0;
}
