function hh = dtdesign(fcent, L, fs)
%DTDESIGN
% hh = dtdesign(fcent, L, fs)
% returns a matrix (L by length(fcent)) where each column is the
% impulse response of a BPF, one for each frequency in fcent
% fcent = vector of center frequencies
% L = length of FIR bandpass filters
% fs = sampling freq
%
% The BPFs must be scaled so that the maximum magnitude
% of the frequency response is equal to one.
ll = 0:L-1; % length vector
ww = 0:pi/300:pi; % omega vector
for ii = 1:length(fcent)
    h1(ii,:) = cos(2*pi*fcent(ii)*ll/fs); % band pass filter
    bb(ii,:) = abs(1/(max(freqz(h1(ii,:),1,ww)))); % get bb scaler coefficients
    h2(ii,:) = h1(ii,:)*bb(ii,:); % concatenate vectors
end
hh = h2'; % turn filters into matrix