#include <linux/init.h>
#include <linux/module.h>

/* Name the module */
MODULE_LICENSE("My First Driver");

/* Allow int parameter to be passed on module load */
int userParam = 0;
module_param(userParam, int, S_IRUGO);

/* Initializes module and prints messages to syslog */
static int driver_init(void)
{
	printk(KERN_ALERT "Hello world\n");
	printk(KERN_ALERT "User Parameter = %d\n", userParam);
	return 0;
}

/* Exits module and prints message to syslog */
static void driver_exit(void)
{
	printk(KERN_ALERT "Goodbye\n");
}

/* Calls both initializer and exit methods */
module_init(driver_init);
module_exit(driver_exit);
