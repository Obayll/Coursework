%Define test signals
t = -2:.005:10;
x1 = (t>=0 & t<1);
x2 = (t>=-1.5 & t<-0.5);

%subplot(211), plot(t, x1);
%subplot(212), plot(t, x2);

x = 1;

%Time Invariance Check
figure(1)
y1=lab2systems(t, x1, x);
y2=lab2systems(t, x2, x);

subplot(411), plot(t, x1), title(['System ',num2str(x) ,' Time Invariance Check']);
subplot(412), plot(t, x2);
subplot(413), plot(t, y1);
subplot(414), plot(t, y2);


%Linearity Check
figure(2)
y1=lab2systems(t, x1, x);
y2=lab2systems(t, x2, x);

subplot(311), plot(t, (x1 + x2)), title(['System ',num2str(x) ,' Linearity Check']);
subplot(312), plot(t, 2*(x1+x2));
subplot(313), plot(t, 2*(y1+y2));