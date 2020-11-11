# $PATH Variable and Messages
-----

I have included my **.profile** which sets my **$PATH** variable on login.

My **$PATH** variable on compliation:
```
/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin
```

My messages:
```
Sep 26 14:29:17 ubuntu kernel: [  200.922905] Hello world
Sep 26 14:29:17 ubuntu kernel: [  200.922907] User Parameter = 8
Sep 26 14:29:40 ubuntu kernel: [  223.861465] Goodbye
```
Logs can also be found in the syslog file on lines **8617-9**.

# How to Compile
-----

I have included the **Makefile** which will handle the compilation steps. To compile:
```
$ make
```
in the directory which both **argDriver.c** and **Makefile** exist.

# How to Load and Unload the Module
-----

To load the file with a parameter:
```
$ insmod argDriver.ko userParam=X
```
where **X** is a user specified integer. If no parameter is given, the default is **0**.

To unload the file:
```
$ rmmod argDriver
```

Remember to use **sudo** if you need higher privelage.

# Module Testing
-----

My module works because I tested it by loading and unloading it, using the steps above, and reading the syslog with:
```
$ dmesg | tail
```
I could have also read the syslog file, but the command is easier. Since I could see the print statement of loading the module, parameter confirmation, and unloading the module, I can confirm it is working as intended.
