%%
%Part 1
figure(1)
tau = 0.01;
T = 10;
t = 0:tau:T-tau;
N = T/tau;
y = length(N);
for n = 1:N
    y(n) = exp(-2*abs(t(n)));
end
Y = fftshift(tau*fft(y));

w = -(pi/tau)+(0:N-1)*(2*pi/(N*tau));

X = Y .* exp(-1i*w*5);

subplot(211), plot(w, abs(X)), title('X(jw) Magnitude');
subplot(212), plot(w, angle(X)), title('X(jw) Phase');

figure(2)
Z = length(N);
for n = 1:N
    Z(n) = 4/(4+w(n)^2);
end
subplot(211), plot(w, abs(Z)), title('Analytical Magnitude');
subplot(212), plot(w, angle(Z)), title('Analytical Phase');

figure(3)
subplot(211), plot(w, abs(Y)), title('Y(jw) Magnitude');
subplot(212), plot(w, angle(Y)), title('Y(jw) Phase');
%%
%Part 2
figure(4)
tau = 0.01;
load splat;
y = y(1:8192);

N = 8192;
fs = 8192;
%soundsc(y, fs);

w = (-pi:2*pi/N:pi-pi/N)*fs;
Y = fftshift(tau*fft(y));
plot(w, abs(Y)), title('Splat Magnitude');

y = ifft(fftshift(Y));
y = real(y);
%soundsc(y, fs);

y1 = conj(Y);
y1 = ifft(fftshift(y1));
y1 = real(y1);
%soundsc(y1, fs);
 
y2 = abs(Y);
y2 = ifft(fftshift(y2));   
%soundsc(y2, fs);
 
y3 = angle(Y);
y3 = ifft(fftshift(y3));
y3 = real(y3);
%soundsc(y3, fs);
%%
%Part 3
figure(5)
w = linspace(0,10);

a = [1 3];
b = [3];
s1 = freqs(b, a, w);

a = [1 1/3];
b = [1/3];
s2 = freqs(b, a, w);

subplot(211), plot(w, abs(s1)), title('System 1 Frequency Response');
subplot(212), plot(w, abs(s2)), title('System 2 Frequency Response');

figure(6)
wc = 3;
[b2, a2] = butter(2,wc,'s');

s3 = freqs(b2, a2, w);
subplot(211), plot(w, abs(s1)), title('System 1 Frequency Response');
subplot(212), plot(w, abs(s3)), title('Butterworth Filter Frequency Response');