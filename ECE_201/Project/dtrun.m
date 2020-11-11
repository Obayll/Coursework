function keys = dtrun(xx,L,fs)
%DTRUN keys = dtrun(xx,L,fs)
% returns the list of key numbers found in xx.
% xx = DTMF waveform
% L = filter length
% fs = sampling freq
%
freqs = [697,770,852,941,1209,1336,1477];
hh = dtdesign( freqs,L,fs );
% hh = L by 7 MATRIX of all the filters. Each column contains the
% impulse response of one BPF (bandpass filter)
%
[nstart,nstop] = dtcut(xx,fs); %<--Find the tone bursts
keys = [ ];
temp_loc = [ ];
key_table = [1 2 3;4 5 6;7 8 9;10 11 12]; % table of keypad
for kk = 1:length(nstart) % for each tone
    x_seg = xx(nstart(kk):nstop(kk)); %<--Extract one DTMF tone
    temp_loc = [ ]; % resets location variable
    for jj = 1:length(freqs) % goes through each column
        temp_loc = [temp_loc,dtscore(x_seg,hh(:,jj))]; % tests for frequency components
    end
    oo = find(temp_loc == 1); % finds where ones occur
    if length(oo) ~= 2 || oo(1) > 4 || oo(2) < 5 % Skips bad scores
        continue
    end
    row = oo(1);
    col = oo(2)-4; % gets row/col position
    key_num = key_table(row,col);
    keys = [keys,key_num]; % sets current key to a key from the key_table
end