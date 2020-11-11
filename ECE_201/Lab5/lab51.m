% A)
freq = 440;
fs1 = 1000;
dur = 2;
tt1 = 0:1/fs1:dur;
x1 = cos(2*pi*freq*tt1);
soundsc(x1,fs1);
% Nyquist freq = 2*fmax = 880

% B)
freq = 880;
x2 = cos(2*pi*freq*tt1);
soundsc(x2,fs1);
% Yes, because the frequency of x2 is 440*2 which is 880.

% C)
[c1,fs2] = audioread('clarinet.wav');
% The fs of 'clarinet.wav' is 44100 Hz. The length of 'clarinet.wav' is
% 2.3129s s.

% D)
tt2 = 1:1/fs2:1.025;
plot(tt2,c1(fs2:floor(1.025*fs2)));
xlabel('Time(s)');
% The Td = 0.004 and fd = 250.

% E)
ff = linspace(-fs2/2,fs2/2,length(c1));
plot(ff,abs(fftshift(fft(c1))));

% i) The fd, based on the spectrum, is fd = 260
% ii) This note is middle C (C4).
% iii) There are 16 significant harmonics.

% G)
% The Nyquist frequency is 2*4152 = 8304.

% Experiment
% i)
fs3 = 8304;
y1 = resample(c1,fs3,44100);
soundsc(y1,fs3);
% It sounds like it lost samples.

% ii)
fs4 = floor(fs3 * 0.8);
y2 = resample(c1,fs4,44100);
soundsc(y2,fs4);
% It's losing samples everytime we downsample it. It sounds like parts of
% it are missing.