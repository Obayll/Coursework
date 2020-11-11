% 3.1
% A
bb = [1 , -0.85];
aa = 1;
xx = rem(0:99,30)>9;
yy = filter(bb,aa,xx);
x2 = [rem(0:19,20)>9 rem(0:19,20)>9 rem(0:19,20)>9 rem(0:19,20)>9 rem(0:19,20>9)];
% B
first = 1;
last  = length(xx);
nn = first:last;
subplot(2,1,1)
stem(nn-1,xx(nn));
title('x(n)');
subplot(2,1,2);
stem(nn-1,yy(nn), 'filled');
title('w(n)');
xlabel('Time Index (n)');
% C
% x[n] is 9 zeroes and 29 1s alternating.
% -0.85x[n-1] is the same fucntion, but delayed by 1 and multiplied by -0.85.
% When you combine the two, the slots where there are two numbers that != 0,
% you get 0.15 because 1-0.85 = 0.15. Since x[n-1] is delayed, there will be
% a combination of some that is 1,0 and 0,-0.85 with 1 being at the beggining
% and -0.85 at the end since x[n-1] is delayed to the right by one number.

% 3.1.1
% A
zz = 1;
M = 15;
r = 0.85;
for k = 1:M
    zz = [zz r^k];
end
rr = filter(zz,aa,yy);
% B
% subplot(2,1,1);
% stem(nn-1,yy(nn));
% title('w(n)');
% subplot(2,1,2);
% stem(nn-1,rr(nn),'filled');
% title('y(n');
% xlabel('Time Index (n)');
% C
dd = xx - rr;
tt = 1:100;
stem(tt,dd);
xlabel('Time Index (n)');
ylabel('Error Amount');
% D
% If we were to use r = 0.84, it would perform worse.
% Since bk = 0.85, the farther we go from the original signal coefficant,
% the worse of an output signal we get.