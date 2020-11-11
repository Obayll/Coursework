fs = 11025; %<--use this sampling rate in all functions
xx = dtdial( 1:12, fs );
soundsc(xx, fs)
L = 75; %<--overkill, this filter length is way too long
dtrun(xx, L, fs)