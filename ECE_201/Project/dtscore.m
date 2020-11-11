function sc = dtscore(xx, hh)
%DTSCORE
% usage: sc = dtscore(xx, hh)
% returns a score based on the max amplitude of the filtered output
% xx = input DTMF tone
% hh = impulse response of ONE bandpass filter
%
% The signal detection is done by filtering xx with a length-L
% BPF, hh, and then finding the maximum amplitude of the output.
% The score is either 1 or 0.
% sc = 1 if max(|y[n]|) is greater than or equal to 0.95
% sc = 0 if max(|y[n]|) is less than 0.95
%
xx = xx*(2/max(abs(xx))); %---Scale x[n] to the range [-2,+2]
yy = conv(xx,hh); % convolution of xx and hh
if max(yy(100:length(yy)-100)) >= 0.95
    sc = 1; % scores 1 if >= .95
else
    sc = 0; % or else it = 0
end

