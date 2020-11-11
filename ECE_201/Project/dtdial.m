function dtmfsig = dtdial(nums,fs)
%DTMFDIAL Create a vector of tones which will dial
% a DTMF (Touch Tone) telephone system.
%
% usage: dtmfsig = dtmfdial(nums,fs)
% nums = vector of numbers ranging from 1 to 12
% fs = sampling frequency
% dtmfsig = vector containing the corresponding tones.
%
tone_cols = ones(4,1)*[1209,1336,1477];
tone_rows = [697;770;852;941]*ones(1,3);
% dur_tone = 0.15; % duration of tone
% dur_pause = 0.05; % duration of pause in between tones
% tt = 0:1/fs:dur_tone; % time vector
dtmfsig = [ ];
for ii = 1:length(nums) % go through each key
    kk = nums(ii); % gets num from input
    dtmfsig = [dtmfsig,zeros(1,501)];
    krow = ceil(kk/3);
    kcol = rem(kk-1,3)+1; % gets row/col position
    f1 = tone_rows(krow,kcol);
    f2 = tone_cols(krow,kcol); % gets tone from row/col position
    dtmfsig = [dtmfsig, cos(2*pi*f1/fs*(0:2499))+cos(2*pi*f2/fs*(0:2499))]; % Combines tones
end