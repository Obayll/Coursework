function y=lab2systems(t,x,sysnum)
%%  LAB2SYSTEMS   Function that implements 4 continuous-time systems
%%
%%  Function call:  y=lab2systems(t,x,sysnum)
%%
%%   Input Variables:  
%%          t = vector of times
%%          x = vector of input signal values (at times in vector t)
%%     sysnum = # of system to implement (1, 2, 3, or 4)
%%
%%   Output Variable:  
%%          y = vector of output signal values (at times in vector t)
%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%--------------------------------------------------------------------
%%% Pre-processing:  Check that x and t are column vectors

x=x(:);
t=t(:);
%--------------------------------------------------------------------
%%% Implement the 4 systems

if sysnum==1				% System #1
  y=t.*x;
elseif sysnum==2			% System #2
  y=sin(3*pi/5)*x;
elseif sysnum==3			% System #3
  y=x.*(t>=0);
elseif sysnum==4			% System #4
  y=x.^2;
else					% Error:  invalid system number
  error('LAB2SYSTEMS:  Invalid system.  Input sysnum must be 1, 2, 3, or 4');
end
%--------------------------------------------------------------------
%%% Return to calling function
return
