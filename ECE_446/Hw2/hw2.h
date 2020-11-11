/* Jordan Ditzler */
/* G00967092      */
/* ECE 446        */
/* FALL 2020      */

/* Node of doubly linked list with extra data fields */
struct node
{
	struct node* prev;
	struct node* next;
	int bib, time;
	char *name, *school;
};

/* Inserts node at the head of the list */
void insert_entry(struct node** head, int *bib, int *time, char *name,
	char *school);

/* Removes node at the head of the list */
void remove_entry(struct node** head, struct node* remove);

/* Prints the entire linked list, starting from the head */
void print_list(struct node** head);
