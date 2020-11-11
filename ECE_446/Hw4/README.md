# $PATH Variable and Messages
-----

I have included my **.profile** which sets my **$PATH** variable on login.

My **$PATH** variable on compliation:
```
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin
```

My messages:
[  728.339858] Hello world!
[  728.341117] I am thread: 1
[  728.341118] List is empty; making new list.
[  728.341323] I am thread: 2
[  728.341323] List is populated; adding to list.
[  728.343382] I am thread: 3
[  728.343383] List is populated; adding to list.
[  728.343495] Thread 1 stopped
[  728.343496] Thread 2 stopped
[  728.343497] Thread 3 stopped
[  728.343498] I am thread: 5
[  728.343499] List is populated; adding to list.
[  728.343886] I am thread: 4
[  728.343887] List is populated; adding to list.
[  728.343888] Printing list.
[  728.343888] Bib number: 4
[  728.343889] Runner    : John
[  728.343889] School    : UVA
[  728.343889] Time      : 183
[  728.343889] Bib number: 5
[  728.343890] Runner    : James
[  728.343890] School    : VCU
[  728.343890] Time      : 99
[  728.343890] Bib number: 3
[  728.343890] Runner    : Guy
[  728.343890] School    : VT
[  728.343891] Time      : 327
[  728.343891] Bib number: 2
[  728.343891] Runner    : David
[  728.343891] School    : ASA
[  728.343891] Time      : 101
[  728.343892] Bib number: 1
[  728.343892] Runner    : Mark
[  728.343892] School    : UCLA
[  728.343892] Time      : 200
[  728.343892] Printing complete.
[  728.343914] Thread 4 stopped
Logs can also be found in the syslog file on lines **45579** and **45616-7**.

# How to Compile
-----

I have included the **Makefile** which will handle the compilation steps. To compile:
```
$ make
```
in the directory which both **listDriver.c** and **Makefile** exist.

# How to Load and Unload the Module
-----

To load the file with a parameter:
```
$ insmod listDriver
```

To unload the file:
```
$ rmmod listDriver
```

Remember to use **sudo** if you need higher privelage.

# Module Testing
-----

I've tested my module to properly work with mutual exclusion. You can check the syslog and my code to see that I use kthreads to create a multi-threaded enviroment w/o race conditions. This means mutual exlcusion is being properly maintained in the code's critical section.

Unfortunately, I don't know much about these kthreads, and I could not figure out how to properly close each thread whilst loading and unloading the module even after using completion structs and waits. You can see in the syslog that all threads close except the 5th one, which I could not figure out, after plenty of crashes. However, it does eventually close as seen on line **45616**, but I could not explain why this occurs.
