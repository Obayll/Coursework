% 3.2.1
% A
q = 0.85;
xx = [1 zeros(1,99)];
aa = 1;
bb = [1 -q];
M = 15;
r = 0.85;
zz = 1;
for k = 1:M
    zz = [zz r^k];
end
ww = filter(bb,aa,xx);
yy = filter(zz,aa,ww);
subplot(3,1,1);
stem(xx);
title('x[n]');
subplot(3,1,2);
stem(ww);
title('w[n]');
subplot(3,1,3);
stem(yy);
title('y[n]');

% C
% Perfect convolution: h1[n]*h2[n] = x[n]
% There is not perfect, but near perfect deconvolution

% 3.2.2
% A
% P = 1200

% B
load('Lab6dat.mat');
ss = [1 zeros(1,1200) 0.85];
rr = filter(ss, 1, v1);
soundsc(rr,fs);