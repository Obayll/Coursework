%1.1
figure(1)                       %designates plot as figure 1
t=0:0.01:10;                    %sets t as 1001 long vector
subplot(311)                    %sets plot as 1x3 grid of subplots
p1=cos(3*pi/5*t);               %calculates sinusoidal wave with w=3pi/5
plot(t,p1)                      %plots waveform vs t and lables it
xlabel('Time(s)')
ylabel('Amplitude')
title('p(t)=cos(3pi/5+0)')
subplot(312)                    %shifts down 1 subplot
p2=cos(3*pi/2*t);               %calculates sinusoidal wave with w=3pi/2
plot(t,p2);                     %plots new waveform and lables it
xlabel('Time(s)')
ylabel('Amplitude')
title('p(t)=cos(3pi/2+0)')
subplot(313)                    %shifts down 1 subplot
p3=cos(3*pi/5*t+pi/2);          %calculates sinusoidal wave with w=3pi/5 and phase=pi/2
plot(t,p3)                      %plots new waveform and lables it
xlabel('Time(s)')
ylabel('Amplitude')
title('p(t)=cos(3pi/5+pi/2)')
%%
%1.2b
figure(2)                                   %designates plot as figure 2
t=0:0.01:15;                                %sets t as 1501 long vector
x1=exp(-0.2.*t);                            %calculates x1 as an exponential with power of -0.2
x2=exp(-0.5.*t);                            %calculates x2 as an exponential with power of -0.5
x3=exp(-0.8.*t);                            %calculates x3 as an exponential with power of -0.8
plot(x1,t,x2,t,x3,t)                        %plots x1, x2, and x3 on the same graph with lables and a legend
xlabel('Time(s)')
ylabel('Amplitude')
title('x1=e^-0.2t,x2=e^-0.5t,x3=e^-0.8t')
legend('x1','x2','x3')
%%
%1.2c
figure(3)                                   %designates plot as figure 3
t=0:0.01:15;                                %sets t as 1501 long vector
x1=exp(-0.2.*t);                            %calculates x1 as an exponential with power of -0.2
x4=exp(0.2.*t);                             %calculates x4 as an exponential with power of 0.2
plot(x1,t,x4,t)                             %plots x1 and x4 on the same graph with lables and a legend
xlabel('Time(s)')
ylabel('Amplitude')
xlim([0 5])
title('x1=e^-0.2t,x4=e^0.2t')
legend('x1','x4')
%%
%2b
figure(4)                                           %designates plot as figure 4
load('parameterSetOne.mat')                         %loads variables from .mat file
tmax=8*pi/w0;                                       %calculates tmax as 4 periods of the signal
t=0:Tsample:tmax;                                   %generates time vector
r1 = 0;                                             %initializes r1
for n=0:length(Cn)-1                                %calculates signal r1 via for loop
    r1=(Cn(n+1)*cos((n+1)*w0*t+thetan(n+1)))+r1;
end
plot(t,r1)                                          %plots r1 vs twith lables
xlim([0,tmax])
xlabel('Time(s)')
ylabel('Amplitude')
title('r1')
%%
%2c
figure(5)                                           %designates plot as figure 5
load('parameterSetTwo.mat')                         %loads variables from .mat file
tmax=8*pi/w0;                                       %calculates tmax as 4 periods of the signal
t=0:Tsample:tmax;                                   %generates time vector
r2 = 0;                                             %initializes r2
for n=0:length(Cn)-1                                %calculates signal r2 via for loop
    r2=(Cn(n+1)*cos((n+1)*w0*t+thetan(n+1)))+r2;
end
plot(t,r2)                                          %plots r2 vs t with lables
xlim([0,tmax])
xlabel('Time(s)')
ylabel('Amplitude')
title('r2')
%%
%2d
figure(6)                                           %designates plot as figure 6
load('parameterSetThree.mat')                       %loads variables from .mat file
tmax=4*pi/w0;                                       %calculates tmax as 2 periods of the signal
t=0:Tsample:tmax;                                   %generates time vector
r3 = 0;                                             %initializes r3
for n=0:length(Cn)-1                                %calculates signal r3 via for loop
    r3=(Cn(n+1)*cos((n+1)*w0*t+thetan(n+1)))+r3;
end
plot(t,r3)                                          %plots r3 vs t with lables
xlim([0,tmax])
xlabel('Time(s)')
ylabel('Amplitude')
title('r3')
soundsc(r3)                                         %plays signal r3 as a sound