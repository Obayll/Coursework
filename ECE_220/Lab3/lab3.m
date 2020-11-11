%% b
figure(1)
b = [1];
a = [1 3];
t = 0:0.01:10;
sys = tf(b,a);
x = ones(1,length(t));
y = lsim(sys,x,t);
plot(t,y);
%% c
figure(2)
x = zeros(1,length(t));
x(t>=1) = 5;
y = lsim(sys,x,t);
plot(t,y);
%% d
figure(3)
y = step(sys,t);
plot(t,y);
%% e
figure(4)
y = impulse(sys,t);
plot(t,y);
%% f
figure(5)
x = exp(-2*t) .* (ones(1, length(t)));
y = lsim(sys,x,t);
plot(t,y);