#include <linux/init.h>
#include <linux/module.h>
#include <linux/mutex.h>
#include <linux/slab.h>
#include <linux/kthread.h>
#include <linux/completion.h>

MODULE_LICENSE("Linked List/Mutual Exclusion Driver");

/* Node of doubly linked list with extra data fields */
struct node
{
	struct node* prev;
	struct node* next;
	int bib, time;
	char *name, *school;
};

static struct task_struct *thread1,*thread2,*thread3,*thread4,*thread5;

struct node* listHead;

static DEFINE_MUTEX(threadLock);
static DECLARE_COMPLETION(t_1);
static DECLARE_COMPLETION(t_2);
static DECLARE_COMPLETION(t_3);
static DECLARE_COMPLETION(t_4);
static DECLARE_COMPLETION(t_5);

static int i, counter;
static int bib[]        = {1,2,3,4,5};
static char name[][6]   = {"Mark","David","Guy","John","James"};
static char school[][5] = {"UCLA","ASA","VT","UVA","VCU"};
static int time[]       = {200,101,327,183,99};

/* Inserts node at the head of the list */
void insert_entry(struct node** head, int *bib, int *time, char *name,
	char *school)
{
	/* Allocate memory for new node */
	struct node* newNode = (struct node*)kmalloc(sizeof(struct node),
		GFP_KERNEL);
	
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
		printk(KERN_ALERT "List is populated; adding to list.\n");
		
		/* Update previous head pointers */
		newNode->next = *head;
		(*head)->prev = newNode;
	}
	/* If list is empty */
	else
	{
		printk(KERN_ALERT "List is empty; making new list.\n");
		
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
		printk(KERN_ALERT "List is populated; removing.\n");
		
		/* Sets head to next in list */
		*head = remove->next;
		
		/* Frees old head node */
		kfree(remove);
	}
	/* If list is empty */
	else
	{
		printk(KERN_ALERT "List is empty; cannot remove.\n");
	}
}

/* Prints the entire linked list, starting from the head */
void print_list(struct node** head)
{
	/* If list is populated */
	if (*head != NULL)
	{
		/* Dummy pointer for iteration */
		struct node* iterNode = (struct node*)kmalloc(sizeof(struct
			node), GFP_KERNEL);
		iterNode = *head;
		
		printk(KERN_INFO "Printing list.\n");
		
		/* Loops through list and prints contents */
		while (iterNode != NULL)
		{
			printk(KERN_INFO "Bib number: %i\n", iterNode->bib);
			printk(KERN_INFO "Runner    : %s\n", iterNode->name);
			printk(KERN_INFO "School    : %s\n", iterNode->school);
			printk(KERN_INFO "Time      : %i\n", iterNode->time);
			iterNode = iterNode->next;
		}
		
		/* Frees dummy pointer memory */
		kfree(iterNode);
		iterNode = NULL;
		
		printk(KERN_INFO "Printing complete.\n");
	}
	/* If list is empty */
	else
	{
		printk(KERN_ALERT "List is empty; cannot print.\n");
	}
}

/* Thread function to add element to list and print if completed */
static int thread_fn(void *arg)
{
	/* Lock for critical section */
	mutex_lock(&threadLock);
	
	/* Gets thread # from passed thread name and prints thread name*/
	i = current->comm[6] - 49;
	printk(KERN_INFO "I am thread: %d\n", i+1);
	
	/* Inserts new entry into the list */
	insert_entry(&listHead, &bib[i], &time[i], name[i], school[i]);
	
	/* Check for completion and prints if it is */
	if (++counter > 4) print_list(&listHead);
	
	/* Switch statement for completetion variables */
	switch (i+1) {
		case 1:
			complete(&t_1);
			break;
		case 2:
			complete(&t_2);
			break;
		case 3:
			complete(&t_3);
			break;
		case 4:
			complete(&t_4);
			break;
		case 5:
			complete(&t_5);
			break;
		default:
			break;
	}
	
	/* Unlock from critical section */
	mutex_unlock(&threadLock);
	
	return 0;
}

static int driver_init(void)
{
	int ret;
	listHead = (struct node*)kmalloc(sizeof(struct node),GFP_KERNEL);
	listHead = NULL;
	
	printk(KERN_ALERT "Hello world!\n");
	
	/* Starts threads */
	thread1 = kthread_run(thread_fn, NULL, "thread1");
	thread2 = kthread_run(thread_fn, NULL, "thread2");
	thread3 = kthread_run(thread_fn, NULL, "thread3");
	thread4 = kthread_run(thread_fn, NULL, "thread4");
	thread5 = kthread_run(thread_fn, NULL, "thread5");
	
	/* Waits for completion for threads before exiting */
	wait_for_completion(&t_1);
	ret = kthread_stop(thread1);
	if (!ret) printk(KERN_ALERT "Thread 1 stopped");
	
	wait_for_completion(&t_2);
	ret = kthread_stop(thread2);
	if (!ret) printk(KERN_ALERT "Thread 2 stopped");
	
	wait_for_completion(&t_3);
	ret = kthread_stop(thread3);
	if (!ret) printk(KERN_ALERT "Thread 3 stopped");
	
	wait_for_completion(&t_4);
	ret = kthread_stop(thread4);
	if (!ret) printk(KERN_ALERT "Thread 4 stopped");
	
	wait_for_completion(&t_5);
	ret = kthread_stop(thread5);
	if (!ret) printk(KERN_ALERT "Thread 5 stopped");
	
	return 0;
}

static void driver_exit(void)
{
	printk(KERN_ALERT "Goodbye World!\n");
}

module_init(driver_init);
module_exit(driver_exit);
