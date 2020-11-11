[ss,fs] = audioread('Lab3Audio.wav');
zz = zeros(1000,1);
s1 = [zz;ss];
s2 = [ss;zz];
s3 = s1 + s2;
soundsc(s3,fs);
delay = 1000/fs;