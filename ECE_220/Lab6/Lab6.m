%Part I: Effect of Pole Locations on System Magnitude Response
%
%   1. Defining h1 - h4

x = 4;  %switches between h1-h4
wn = 1;
dr = [0, 1/4, 1, 2];

%
h(x) = tf(wn^2 , [1, 2*dr(x)*wn, wn^2]);
pzmap(h(x)), title(['Pole Zero Map of h',num2str(x)]);
%
%   2. Plotting |H(jw)|

omega = -5:0.1:5;
[H, omega]= freqs(wn^2 , [1, 2*dr(x)*wn, wn^2], omega);
plot(omega, abs(H)), title(['Frequency response of H', num2str(x)]);
xlabel('Angular Frequency, \omega (rad/s)');
ylabel('|H_4(j\omega)|');

%Part II: Design of Butterworth Filters
%
%   3

wc = 10*pi;
N=1;
omega= 0:1/100:1000;

[w, omega] = freqs(1, [-1, 0, wc^2]);
plot(omega, abs(w));
title('Magnitude');
xlabel('Angular Frequency, \omega (rad/s)');
ylabel('|H(s)H(-s)|');

%   4

s = tf('s');
N = 3;
H =  1/(1+(s/(wc))^(2*N));

p = pole(H);    %poles are listed in the vector p
pzmap(H);
axis('equal');


%Part III: Surface Plots of Laplace Transforms
%
%   1
a1 = 4;
b1 = [1, 2, 17];

r = roots(b1);
[p, r2] = pzplot(b1, a1, -1);

%   2
omega = [-10:0.5:10];
[H1, omega] = freqs(a1, b1, omega);
plot(omega, abs(H1));
title('Magnitude of H_1(s)');
xlabel('Angular Frequency, \omega (rad/s)');
ylabel('|H_1(s)|');

%   3
sigma=-1+(1/8)*[1:32];
[sigmagrid,omegagrid] = meshgrid(sigma,omega);
sgrid = sigmagrid+j*omegagrid;

H1 = polyval(b1,sgrid)./polyval(a1,sgrid);
mesh(sigma,omega,abs(H1));

hold on;
plot3(zeros(1,41),omega,abs(H1(: ,8))+0.05,'c')
hold off;
