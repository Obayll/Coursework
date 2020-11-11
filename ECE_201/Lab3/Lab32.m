[ss,fs] = audioread('Lab3Audio.wav');
tt = linspace(0,length(ss)/fs,length(ss));
plot(tt,ss);
soundsc(ss,fs);
s4 = ss(1:4:end);
soundsc(s4,fs);
soundsc(s4,fs/4);