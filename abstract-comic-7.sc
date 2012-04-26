SynthDefPool.gui;

(
s.waitForBoot{
	//--Korg Kontrol variables
	var mute1 = true;
	var mute2 = true;
	var mute3 = true;
	var mute4 = true;
	var mute5 = true;
	var mute6 = true;
	var mute7 = true;
	var mute8 = true;
	var mute9 = true;

	var syn1_mute = 0, syn1_amp = 0, syn1_pan = 0;
	var drum1_mute = 0, drum1_amp = 0, drum1_pan = 0;
	var syn2_mute = 0, syn2_amp = 0, syn2_ptn = 0, syn2_pan = 0;
	var drum2_mute = 0, drum2_amp = 0, drum2_pan = 0;
	var drum3_mute = 0, drum3_amp = 0, drum3_pan = 0;
	var syn3_mute = 0, syn3_amp = 0, syn3_ptn = 0, syn3_pan = 0;
	var drum4_mute = 0, drum4_amp = 0, drum4_pan = 0;
	var syn4_mute = 0, syn4_amp = 0, syn4_ptn = 0, syn4_pan = 0;
	var ura_amp = 0;

	var hihat, shaker;
	var clock;
	var sfl;
	var rev;
	
	var kick_ptn, snare_ptn, hihat_ptn, synthdrum_ptn;
	var drum_pdefs;
	var drum_dur = 0.25;
	
	//--GUI variables 
	var w;
	var p1, m1, s1, l1, t1;
	var p2, m2, s2, l2, t2;
	var p3, m3, s3, l3, t3, b3_1, b3_2, b3_3, b3_4, b3_5;
	var p4, m4, s4, l4, t4;
	var p5, m5, s5, l5, t5;
	var p6, m6, s6, l6, t6, b6_1, b6_2;
	var p7, m7, s7, l7, t7;
	var p8, m8, s8, l8, t8, b8_1, b8_2, b8_3, b8_4;
	var p9, m9, s9, l9, t9, b9_1, b9_2, b9_3, b9_4, b9_5;
	var b10_1, b10_2, b10_3, b10_4,
	    b10_5, b10_6, b10_7, b10_8, b10_9;

	sfl = 1.0/8.0;	
	clock = TempoClock(75/60);
	n = NanoKontrol.new;
	~host = NetAddr("localhost", 12000);

	//--control 1
	n.knob1.onChanged = { |vel|
						  syn1_pan = vel.linlin(0, 127, -1, 1);
						  p1.value_( vel.linlin(0, 127, 0, 1) );
						  // vel.postln;
						};
	n.topBt1.onPress = { "MUTE-1".postln; 
					  	 ~host.sendMsg("/mute", 1);
						 if(mute1 == true,
						 	{ mute1 = false;
						   	  syn1_mute = 1; 
							  m1.value_(0); },
						 	{ mute1 = true;
						   	  syn1_mute = 0; 
							  m1.value_(1); });
					   };
	n.bottomBt1.onPress = { "SOLO-1".postln;
					  	    ~host.sendMsg("/solo", 1);
					      };
	n.fader1.onChanged = { |vel| 
						   syn1_amp = vel.linlin(0, 127, 0, 1);
						   l1.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 2
	n.knob2.onChanged = { |vel|
						  drum1_pan = vel.linlin(0, 127, -1, 1);
						  p2.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt2.onPress = { "MUTE-2".postln; 
					  	 ~host.sendMsg("/mute", 2);
						 if(mute2 == true,
						 	{ mute2 = false;
						   	  drum1_mute = 1; 
							  m2.value_(0); },
						 	{ mute2 = true;
						   	  drum1_mute = 0; 
							  m2.value_(1); });
					   };
	n.bottomBt2.onPress = { "SOLO-2".postln;
					  	    ~host.sendMsg("/solo", 2);
					      };
	n.fader2.onChanged = { |vel| 
						   drum1_amp = vel.linlin(0, 127, 0, 1);
						   l2.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 3
	n.knob3.onChanged = { |vel|
						  syn2_pan = vel.linlin(0, 127, -1, 1);
						  p3.value_( vel.linlin(0, 127, 0, 1) );
						  // vel.postln;
						};
	n.topBt3.onPress = { "MUTE-3".postln; 
					  	 ~host.sendMsg("/mute", 3);
						 if(mute3 == true,
						 	{ mute3 = false;
						   	  syn2_mute = 1; 
							  m3.value_(0); },
						 	{ mute3 = true;
						   	  syn2_mute = 0; 
							  m3.value_(1); });
					   };
	n.bottomBt3.onPress = { "SOLO-3".postln;
					  	    ~host.sendMsg("/solo", 3);
						  };
	n.fader3.onChanged = { |vel| 
						   syn2_amp = vel.linlin(0, 127, 0, 1);
						   l3.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 4
	n.knob4.onChanged = { |vel|
						  // syn3_pan = vel.linlin(0, 127, -1, 1);
						  // drum2_pan = vel.linlin(0, 127, -1, 1);
						  p4.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt4.onPress = { "MUTE-4".postln; 
					  	 ~host.sendMsg("/mute", 4);
						 if(mute4 == true,
						 	{ mute4 = false;
						   	  drum2_mute = 1; 
							  m4.value_(0); },
						 	{ mute4 = true;
						   	  drum2_mute = 0; 
							  m4.value_(1); });
					   };
	n.bottomBt4.onPress = { "SOLO-4".postln;
					  	    ~host.sendMsg("/solo", 4);
						  };
	n.fader4.onChanged = { |vel| 
						   drum2_amp = vel.linlin(0, 127, 0, 1);
						   l4.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 5
	n.knob5.onChanged = { |vel|
						  // syn3_pan = vel.linlin(0, 127, -1, 1);
						  p5.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt5.onPress = { "MUTE-5".postln; 
					  	 ~host.sendMsg("/mute", 5);
						 if(mute5 == true,
						 	{ mute5 = false;
						   	  drum3_mute = 1; 
							  m5.value_(0); },
						 	{ mute5 = true;
						   	  drum3_mute = 0; 
							  m5.value_(1); });
					   };
	n.bottomBt5.onPress = { "SOLO-5".postln;
					  	    ~host.sendMsg("/solo", 5);
						  };
	n.fader5.onChanged = { |vel| 
						   drum3_amp = vel.linlin(0, 127, 0, 1);
						   l5.value_(vel.linlin(0, 127, 0, 1));
						 };
						 
	//--control 6
	n.knob6.onChanged = { |vel|
						  syn3_pan = vel.linlin(0, 127, -1, 1);
						  p6.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt6.onPress = { "MUTE-6".postln; 
					  	 ~host.sendMsg("/mute", 6);
						 if(mute6 == true,
						 	{ mute6 = false;
						   	  syn3_mute = 1; 
							  m6.value_(0); },
						 	{ mute6 = true;
						   	  syn3_mute = 0; 
							  m6.value_(1); });
					   };
	n.bottomBt6.onPress = { "SOLO-6".postln;
					  	    ~host.sendMsg("/solo", 6);
						  };
	n.fader6.onChanged = { |vel| 
						   syn3_amp = vel.linlin(0, 127, 0, 1);
						   l6.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 7
	n.knob7.onChanged = { |vel|
						  drum4_pan = vel.linlin(0, 127, -1, 1);
						  p7.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt7.onPress = { "MUTE-7".postln; 
					  	 ~host.sendMsg("/mute", 7);
						 if(mute7 == true,
						 	{ mute7 = false;
						   	  drum4_mute = 1; 
							  m7.value_(0); },
						 	{ mute7 = true;
						   	  drum4_mute = 0; 
							  m7.value_(1); });
					   };
	n.bottomBt7.onPress = { "SOLO-7".postln;
					  	    ~host.sendMsg("/solo", 7);
						  };
	n.fader7.onChanged = { |vel| 
						   drum4_amp = vel.linlin(0, 127, 0, 1);
						   l7.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 8
	n.knob8.onChanged = { |vel|
						  syn4_pan = vel.linlin(0, 127, -1, 1);
						  p8.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt8.onPress = { "MUTE-8".postln; 
					  	 ~host.sendMsg("/mute", 8);
						 if(mute8 == true,
						 	{ mute8 = false;
						   	  syn4_mute = 1; 
							  m8.value_(0); },
						 	{ mute8 = true;
						   	  syn4_mute = 0; 
							  m8.value_(1); });
					   };
	n.bottomBt8.onPress = { "SOLO-8".postln;
					  	    ~host.sendMsg("/solo", 8);
						  };
	n.fader8.onChanged = { |vel| 
						   syn4_amp = vel.linlin(0, 127, 0, 1);
						   l8.value_(vel.linlin(0, 127, 0, 1));
						 };

	//--control 9
	n.knob9.onChanged = { |vel|
						  p9.value_( vel.linlin(0, 127, 0, 1) );
						};
	n.topBt9.onPress = {};
	n.bottomBt9.onPress = {};
	n.fader9.onChanged = { |vel| 
						   ura_amp = vel.linlin(0, 127, 0, 1);
						   l9.value_(vel.linlin(0, 127, 0, 1));
						 };
						 
	SynthDef("syn1", {
		arg freq=440, gate=1, amp=1.0, source, osc, env1, pan = 0.0,  fmratio = 0.5 ;
		env1 = EnvGen.kr(Env.perc(0.0001, 0.1, 1, -6), gate, 1, 0);
		osc = Pan2.ar( SinOsc.ar(SinOsc.ar(freq*2, 0, env1*9000+2000, freq), 0.51, amp), pan ) * env1;
		source = osc+AllpassN.ar(AllpassN.ar(osc,1,ExpRand(0.02,0.3),2),1,ExpRand(0.02,0.3),2)*Line.kr(1,0,1.5,doneAction:2);
		Out.ar(0, source);
	}).store;

	SynthDef("drum1", {
		arg freq=440, gate=1, amp=1.0, source, pan = 0.0 ;
		source = 
		Pan2.ar(
		SinOsc.ar(EnvGen.kr(Env.perc(0.0001, 2.5, 1, -200), gate, 1000, 45, doneAction:2), 1, 1) +
		((BPF.ar([GrayNoise.ar(8),GrayNoise.ar(8)],EnvGen.kr(Env.perc(0.001,0.3,1,-200),gate,6000,70),1.5)).distort
		* Line.kr(0.3,0,0.1)) *
		EnvGen.kr(Env.perc(0.001, 0.22, amp, 8)),pan);
		Out.ar(0, source);
	}).store;
						   
	SynthDef("syn2", {
		arg freq=440, gate=1, amp=1.0, source, pan = 0.0 ;
		source = 
		Pan2.ar(
		// Pulse.ar(freq, [0.6, 0.4], amp * 0.8) + SinOsc.ar(freq, 0, amp) * 
		Pulse.ar(freq, [0.6, 0.4], amp * 0.4) + SinOsc.ar(freq, 0, amp) * 
		EnvGen.kr(Env.adsr(0.01, 0.2, 0.9, 0.01, 1, -6), gate, doneAction:2), pan );
		Out.ar(0, source);
	}).store;

	SynthDef("drum2", {
		arg freq=440, gate=1, amp=1.0, source, pan = 0.0 ;
		source = 
		Pan2.ar(BPF.ar(LFPulse.ar(BPF.ar(WhiteNoise.ar(5),1200,0.1).distort * 8000 + 6600, 0.7),7000,1) * 
		EnvGen.kr(Env.perc(0.01, 0.4, 1, -6), doneAction:2),0);
		Out.ar(0, source * amp);
	}).store;

	SynthDef("drum3", {
		arg freq=440, gate=1, amp=1.0, source, pan = 0.0 ;
		source = 
		Pan2.ar(BPF.ar(WhiteNoise.ar(16),10000,0.2).distort * 
		EnvGen.kr(Env.perc( (1-amp*0.02)+0.01 , ((amp*amp)*1.0 + 0.1), amp*amp*0.8+0.2, -6), gate, doneAction:2),
		Rand(-0.3,0.3));
		Out.ar(0, source * amp);
	}).store;
			
	SynthDef("drum_hihat", {
		arg freq=440, gate=1, amp=1.0, source, pan = 0.0 ;
		source = 
		Pan2.ar(BPF.ar(LFPulse.ar(BPF.ar(WhiteNoise.ar(5),1200,0.1).distort * 8000 + 6600, 0.7),7000,1) * 
		EnvGen.kr(Env.perc(0.01, 0.4, 1, -6), doneAction:2),0);
		Out.ar(0, source * amp);
	}).store;

	SynthDef(\atari2600, {|out= 0, gate= 1, tone0= 5, tone1= 8, freq0= 10, freq1= 20, amp= 1, pan= 0|
		var e, z;
		e= EnvGen.kr(Env.asr(0.01, amp, 0.05), gate, doneAction:2);
		z= Atari2600.ar(tone0, tone1, freq0, freq1, 15, 15);
		Out.ar(out, Pan2.ar(z*e, pan));
	}).store;

	SynthDef(\rev, {|in= 10, mix= 0.5|
		var z= InFeedback.ar(0, 2);	//read stereo sound from bus number 'in'
		z= FreeVerb.ar(z, mix, 0.8);
		Out.ar(0, z);
	}).store;

	Pdef(\syn1, Pbind(
		\instrument, \syn1,
		\amp, (1/8) * Pfunc({syn1_mute}) * Pfunc({syn1_amp}),
		// \dur, Pseq([12, 4]/4.0, inf),
		\dur, Pseq([6, 2], inf),
		// \midinote, Pseq([\, 55, \, 60, \, 53]+24, inf),
		\midinote, Pfunc({ rrand(21, 108) }),
		\pan, Prand([1, -1, 0.5, -0.5], inf),
		\dummy, Pfunc({arg evt;
			~host.sendMsg("/syn1", evt.at(\midinote));
			})
	));
	Pdef(\syn1).play(clock);

	SynthDef("synth_chord2", {
		arg freq=440, gate=1, amp=1.0, source, env1, env2, pan = 0.0 ;
		env1 = EnvGen.kr(Env.adsr(0.001, 1, 0.0, 0.6, 1, -6), gate, doneAction:2);
		env2 = EnvGen.kr(Env.adsr(0.01, 0.2, 0.0, 0.2, 1, Rand(-50,-10)), gate, ExpRand(40,13000), 400);
		source = Pan2.ar(LPF.ar(LFSaw.ar(freq, [Rand(0,1),Rand(0,1)], amp), env2) * env1, pan);
		2.do({source = AllpassN.ar(source, 0.07, ExpRand(0.01,0.07), 3)});
		Out.ar(0, source * amp);
	}).store;
	
	SynthDef("drum4", {
		arg freq=440, gate=1, amp=1.0, source, pan = 0.0 ;
		source = 
		Pan2.ar((BPF.ar(BPF.ar(WhiteNoise.ar(16),2300,0.3),8300,0.3)*5).distort * 
		EnvGen.kr(Env.perc(0.01, 0.1, 1, 0), doneAction:2),0);
		Out.ar(0, source * amp);
	}).store;
	
	SynthDef(\syn4, {
		arg amp=0.1, sustain=0.1, freq=440, filtfreq1 = 5000, filtfreq2=1000, releaseTime = 0.11;
		var env, sound;
		env=EnvGen.ar(Env([0,1,1,0],[0.01,releaseTime,0.05]),doneAction:2);
		sound=LPF.ar(LFSaw.ar(freq,0,amp),Line.kr(filtfreq1, filtfreq2,0.1));
		Out.ar(0,Pan2.ar(sound*env, 0.0))
	}).store;

	Pdef(\drum1, Pbind(
	\instrument, \drum1,
	\amp, (1/8) * Pfunc({drum1_mute}) * Pfunc({drum1_amp}),
	\pan, Pfunc({drum1_pan}),
	\dur, 1.0,
	\dummy, Pfunc({arg evt;
			~host.sendMsg("/drum1", evt.at(\amp));
			})
	));
	Pdef(\drum1).play(clock);

	Pdef(\syn2, Pbind(
		// \instrument, \synth_melody,
		\instrument, \syn2,
		\amp, (1/8) * Pfunc({syn2_mute}) * Pfunc({syn2_amp}),
		\pan, Pfunc({syn2_pan}),
	    // \midinote, Pseq([60, 55, 64, 60, 60, 60, 55, 64, 60], inf),
		\midinote, Pseq([\, \, 64, 60, \, \, \, 64, 60], inf),
		\dur, Pseq([0.5, 0.25, 0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.5], inf),
		\dummy, Pfunc({arg evt;
			~host.sendMsg("/syn2", evt.at(\midinote));
			})
	));
	Pdef(\syn2).play(clock);

	Pdef(\drum2, Pbind(
	\instrument, \drum_hihat,
	\amp, (1/8) * Pfunc({drum2_mute}) * Pfunc({drum2_amp}),
	\dur, Pseq([1, 1]/2,inf),
	\midinote, Pseq([\, 1],inf)
	));
	Pdef(\drum2).play(clock);

	/*
	Pdef(\drum3, Pbind(
	\instrument, \drum3,
	// \amp, Pseq([ 0.3, 0.1, 1.0, 0.4, 0.6, 0.3, 1.0, 0.4 ]/4, inf) * Ptuple([Pfunc({drum2_amp})]),
	\amp, 0.4 * Pfunc({drum3_mute}) * Pfunc({drum3_amp}),
	// \dur, Pseq([1+sfl, 1-sfl]/4,inf),
	\dur, 1/4,
	\midinote, 3
	));
	*/

	Pdef(\drum3, Pbind(
	\instrument, \drum3,
	\amp, Pseq([0.3,0.1,1.0,0.4,0.6,0.3,1.0,0.4],inf) * 
	 	  Ptuple([(1/8)*Pfunc({drum3_mute})*Pfunc({drum3_amp})]),
	\dur, Pseq([1+sfl, 1-sfl]/4,inf),
	\midinote, 3
	));
	Pdef(\drum3).play(clock);

	Pdef(\syn3, Pbind(
	\instrument, \synth_chord2,
	\amp, (1/8) * Pfunc({syn3_mute}) * Pfunc({syn3_amp}),
	\pan, Pfunc({syn3_pan}),
	\dur, 2/4,
	// \midinote, Pseq([[55,62,66],[59,62,69],[55,62,66]+12,[59,62,69],[54,61,66],[57,61,69],[54,61,66],[57,61,69]] , inf)
	\midinote, Pseq([[60,64,67]], inf),
	\dummy, Pfunc({arg evt;
		~host.sendMsg("/syn3", evt.at(\midinote));
	})
	));
	Pdef(\syn3).play(clock);

	Pdef(\drum4, Pbind(
	\instrument, \drum4,
	\amp, (1/8) * Pfunc({drum4_mute}) * Pfunc({drum4_amp}),
	\dur, Pseq([1, 1],inf),
	\midinote, Pseq([\, 1],inf)
	));
	Pdef(\drum4).play(clock);

	Pdef(\syn4, Pbind(
	\instrument, \syn4,
	// \amp, 0.3 * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
	\amp, Pseq([1,0,1,1,Pwrand([1,0],[0.1,0.9],1),0,1,0,
	    		1,1,0,1,Pwrand([1,0],[0.5,0.5],1),0,1,1],inf) *
				(1/8) * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
	\dur, 0.25,
	\midinote, Prand([Pseq([64,60,64,60,65,60,65,60,64,60,64,60,55,59,60,\], 1),
					  Pseq([64,60,64,60,67,60,67,60,65,60,65,60,55,59,60,\], 1),
					  Pseq([60,60,64,60,65,60,64,60,\,60,64,60,55,59,60,\], 1)
					 ], inf)
	));
	Pdef(\syn4).play(clock);

//-- chord 1
Pdef(\1,
Pbind(
\instrument, \atari2600,
\tone0, Pseq([6],inf),
\freq0, Prand([Pseq([31,15,31,15,9,21],1),
 			   Pseq([31,31,15,15,9,21],1)],inf),
\tone1, 0,
\freq1, 0,
\dur, 0.125,
\legato, 0.8,	// 0.2 ~ 0.8
\amp, (1/4) * Pfunc({ura_amp})
));

//-- chord 2
Pdef(\2,
Pbind(
\instrument, \atari2600,
\tone1, 12,
\freq1, Prand([Pseq([1,9,8,3,0,6,0,6],1),
			   Pseq([1,9,8,4,0,2,0,1],1),
			   Pseq([1,9,8,5,0,8,2,5],1),
			   Pseq([1,9,8,7,0,6,1,6],1)],inf)+10,
\tone0, 0,
\freq0, 0,
\dur, Pseq([1,2,2,1,1,2,1,1],inf)*0.125,
\legato, 0.2,	// 0.2 ~ 1.2
\amp, (1/4) * Pfunc({ura_amp})
));

//-- chord 3
Pdef(\3,
Pbind(
\instrument, \atari2600,
\tone0, Pseq([Pseq([2,1],32),Pseq([6,5],32)],inf),
\freq0, 10,
\tone1, Pseq([Pseries(10,14,16),Pseries(10,5,16)],inf),
\freq1, 0,
\dur, 0.125*2,
\amp, (1/4) * Pfunc({ura_amp}),
\pan, Prand([1,-1,0.5,-0.5],inf)
));

//-- chord 4 
Pdef(\4,
Pbind(
\instrument, \atari2600,
\tone0, 4,
\freq0, Pseq([Pseries(30,10,12)],inf),
\tone1, 0,
\freq1, 0,
\dur, 2,
\legato, 2,
\amp, (1/4) * Pfunc({ura_amp})
));

	drum_pdefs = {
		Pdef(\drum1, Pbind(
			\instrument, \drum1,
			\amp, kick_ptn * Ptuple([(1/8) * Pfunc({drum1_mute}) * Pfunc({drum1_amp})]),
			\dur, Pfunc({drum_dur}),
			\dummy, Pfunc({arg evt;
				~host.sendMsg("/drum1", evt.at(\amp).at(0));
			})
		));

		Pdef(\drum2, Pbind(
			\instrument, \drum_hihat,
			\amp, snare_ptn	* Ptuple([(1/8) * Pfunc({drum2_mute}) * Pfunc({drum2_amp})]),
			\dur, Pfunc({drum_dur}),
			\dummy, Pfunc({arg evt;
				~host.sendMsg("/drum2", evt.at(\amp).at(0));
			})
		));

		Pdef(\drum3, Pbind(
			\instrument, \snare_oto309,
			\amp, hihat_ptn * Ptuple([(1/8) * Pfunc({drum3_mute}) * Pfunc({drum3_amp})]),
			\dur, Pfunc({drum_dur}),
			\dummy, Pfunc({arg evt;
				~host.sendMsg("/drum3", evt.at(\amp).at(0));
			})
		));

		Pdef(\drum4, Pbind(
			\instrument, \drum4,
			\amp, synthdrum_ptn * Ptuple([(1/8) * Pfunc({drum4_mute}) * Pfunc({drum4_amp})]),
			\dur, Pfunc({drum_dur}),
			\dummy, Pfunc({arg evt;
				~host.sendMsg("/drum4", evt.at(\amp).at(0));
			})
		));
	};

	// バリエーションG
	kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0],inf);
	snare_ptn     = Pseq([0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0],inf);
	hihat_ptn     = Pseq([1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,1],inf);
	synthdrum_ptn = Pseq([0,0,1,0,0,1,0,0,1,0,0,1,0,1,1,1],inf);
	drum_pdefs.value;

	/*
	shaker = Pbind(
	\instrument, \drum_shaker,
	// \amp, Pseq([ 0.3, 0.1, 1.0, 0.4, 0.6, 0.3, 1.0, 0.4 ]/4, inf) * Ptuple([Pfunc({drum2_amp})]),
	\amp, 0.4 * Pfunc({drum2_mute}) * Pfunc({drum2_amp}),
	// \dur, Pseq([1+sfl, 1-sfl]/4,inf),
	\dur, 1/4,
	\midinote, 3
	).play(clock);
			
	hihat = Pbind(
	\instrument, \drum_hihat,
	\amp, 0.4 * Pfunc({drum2_mute}) * Pfunc({drum2_amp}),
	\dur, Pseq([1, 1]/2,inf),
	\midinote, Pseq([\, 1],inf)
	).play(clock);
	*/

	//--window
	w = Window("KONTROL", Rect(0, 0, 640, 480));
	
	//--control 1
	p1 = Knob(w, Rect(20, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m1 = Button(w, Rect(20, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 1);
					if(mute1 == true,
					 	{ mute1 = false;
					   	  syn1_mute = 1; },
					 	{ mute1 = true;
					   	  syn1_mute = 0; }); 
				});
	s1 = Button(w, Rect(20, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l1 = Slider(w, Rect(30, 115, 20, 100))
				.action_({
					syn1_amp = l1.value;
				});
	t1 = StaticText(w, Rect(20, 220, 40, 20))
				.string_("SYN1");

	//--control 2
	p2 = Knob(w, Rect(75, 20, 40, 40))
				.centered_(true)
				.value_(0.5)
				.action_({|v|
					drum1_pan = v.value.linlin(0, 1, -1, 1);
				});
	m2 = Button(w, Rect(75, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 2);
					 if(mute2 == true,
					 	{ mute2 = false;
					   	  drum1_mute = 1; },
					 	{ mute2 = true;
					   	  drum1_mute = 0; });
				});
	s2 = Button(w, Rect(75, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l2 = Slider(w, Rect(85, 115, 20, 100))
				.action_({
					drum1_amp = l2.value;
				});
	t2 = StaticText(w, Rect(75, 220, 50, 20))
				.string_("DRUM1");

	//--control 3
	p3 = Knob(w, Rect(130, 20, 40, 40))
			.centered_(true)
			.value_(0.5)
			.action_({|v|
				syn2_pan = v.value.linlin(0, 1, -1, 1);
			});
	m3 = Button(w, Rect(130, 65, 40, 20))
			.states_([
				["M", Color.black, Color.clear],
				["M", Color.black, Color.red]
				])
			.action_({
			 	~host.sendMsg("/mute", 3);
					 if(mute3 == true,
					 	{ mute3 = false;
					   	  syn2_mute = 1; },
					 	{ mute3 = true;
					   	  syn2_mute = 0; });
			});
	s3 = Button(w, Rect(130, 90, 40, 20))
			.states_([
				["S", Color.black, Color.clear],
				["S", Color.black, Color.red]
				])
			.action_({ arg butt;
				butt.value.postln;
			});
	l3 = Slider(w, Rect(140, 115, 20, 100))
			.action_({ 
				syn2_amp = l3.value;
			});
	t3 = StaticText(w, Rect(134, 220, 50, 20))
			.string_("SYN2");
	b3_1 = Button(w, Rect(130, 245, 40, 20))
			.states_([
				["PTN1", Color.black, Color.clear],
				["PTN1", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 0;
				Pdef(\syn2, Pbind(
					\instrument, \syn2,
					\amp, (1/8) * Pfunc({syn2_mute}) * Pfunc({syn2_amp}),
					\pan, Pfunc({syn2_pan}),
					\midinote, Pseq([\, \, 64, 60, \, \, \, 64, 60], inf),
					\dur, Pseq([0.5, 0.25, 0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.5], inf),
					\dummy, Pfunc({arg evt;
						~host.sendMsg("/syn2", evt.at(\midinote));
					})
				));
				b3_2.value_(0);
				b3_3.value_(0);
				b3_4.value_(0);
				b3_5.value_(0);
			});
	b3_2 = Button(w, Rect(130, 270, 40, 20))
			.states_([
				["PTN2", Color.black, Color.clear],
				["PTN2", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 1;
				Pdef(\syn2, Pbind(
					\instrument, \syn2,
					\amp, (1/8) * Pfunc({syn2_mute}) * Pfunc({syn2_amp}),
					\pan, Pfunc({syn2_pan}),
					\midinote, Pseq([60, \, 64, 60, \, 60, \, 64, 60], inf),
					\dur, Pseq([0.5, 0.25, 0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.5], inf),
					\dummy, Pfunc({arg evt;
						~host.sendMsg("/syn2", evt.at(\midinote));
					 })
				));
				b3_1.value_(0);
				b3_3.value_(0);
				b3_4.value_(0);
				b3_5.value_(0);
			});
	b3_3 = Button(w, Rect(130, 295, 40, 20))
			.states_([
				["PTN3", Color.black, Color.clear],
				["PTN3", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 2;
				Pdef(\syn2, Pbind(
					\instrument, \syn2,
					\amp, (1/8) * Pfunc({syn2_mute}) * Pfunc({syn2_amp}),
					\pan, Pfunc({syn2_pan}),
					\midinote, Pseq([Pseq([60, 55, 64, 60, 60, 60, 55, 64, 60], 2),
				  	       				   67, 65, 64, 65, 64, 62, 55, 59, 62], inf),
					\dur, Pseq([0.5, 0.25, 0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.5], inf),
					\dummy, Pfunc({arg evt;
						~host.sendMsg("/syn2", evt.at(\midinote));
					 })
				));
				b3_1.value_(0);
				b3_2.value_(0);
				b3_4.value_(0);
				b3_5.value_(0);
			});
	b3_4 = Button(w, Rect(130, 320, 40, 20))
			.states_([
				["PTN4", Color.black, Color.clear],
				["PTN4", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 2;
				Pdef(\syn2, Pbind(
					\instrument, \syn2,
					\amp, (1/8) * Pfunc({syn2_mute}) * Pfunc({syn2_amp}),
					\pan, Pfunc({syn2_pan}),
					\midinote, Pseq([Pseq([60, 55, 64, 60, 60, 60, 55, 64, 60], 2),
				  	       				   67, 65, 64, 65, 64, 62, 55, 59, 62], inf).stutter(2),
					\dur, Pseq([0.5, 0.25, 0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.5], inf).stutter(2)/2,
					\dummy, Pfunc({arg evt;
						~host.sendMsg("/syn2", evt.at(\midinote));
					 })
				));
				b3_1.value_(0);
				b3_2.value_(0);
				b3_3.value_(0);
				b3_5.value_(0);
			});
	b3_5 = Button(w, Rect(130, 345, 40, 20))
			.states_([
				["RAND", Color.black, Color.clear],
				["RAND", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 2;
				Pdef(\syn2, Pbind(
					\instrument, \syn2,
					\amp, (1/8) * Pfunc({syn2_mute}) * Pfunc({syn2_amp}),
					\pan, Pfunc({syn2_pan}),
					\midinote, Prand([Pseq([ \,  \, 64, 60,  \,  \,  \, 64, 60], 1),
						  			  Pseq([60,  \, 64, 60,  \, 60,  \, 64, 60], 1),
						  			  Pseq([60, 55, 64, 60, 60, 60, 55, 64, 60], 1),
				  	      			  Pseq([67, 65, 64, 65, 64, 62, 55, 59, 62], 1)], inf),
					\dur, Pseq([0.5, 0.25, 0.5, 0.5, 0.5, 0.5, 0.25, 0.5, 0.5], inf),
					\dummy, Pfunc({arg evt;
						~host.sendMsg("/syn2", evt.at(\midinote));
					 })
				));
				b3_1.value_(0);
				b3_2.value_(0);
				b3_3.value_(0);
				b3_4.value_(0);
			});

	//--control 4
	p4 = Knob(w, Rect(185, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m4 = Button(w, Rect(185, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 4);
					 	if(mute4 == true,
							{ mute4 = false;
						   	  drum2_mute = 1; },
						 	{ mute4 = true;
						   	  drum2_mute = 0; });
				});
	s4 = Button(w, Rect(185, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l4 = Slider(w, Rect(195, 115, 20, 100))
				.action_({
					drum2_amp = l4.value;
				});
	t4 = StaticText(w, Rect(185, 220, 50, 20))
				.string_("DRUM2");

	//--control 5	
	p5 = Knob(w, Rect(240, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m5 = Button(w, Rect(240, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 5);
						if(mute5 == true,
						 	{ mute5 = false;
						   	  drum3_mute = 1; },
						 	{ mute5 = true;
						   	  drum3_mute = 0; });
				});
	s5 = Button(w, Rect(240, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l5 = Slider(w, Rect(250, 115, 20, 100))
				.action_({
					drum3_amp = l5.value;
				});
	t5 = StaticText(w, Rect(240, 220, 50, 20))
				.string_("DRUM3");
				
	//--control 6
	p6 = Knob(w, Rect(295, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m6 = Button(w, Rect(295, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 6);
						if(mute6 == true,
						 	{ mute6 = false;
						   	  syn3_mute = 1; },
						 	{ mute6 = true;
						   	  syn3_mute = 0; });
				});
	s6 = Button(w, Rect(295, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l6 = Slider(w, Rect(305, 115, 20, 100))
				.action_({
					syn3_amp = l6.value;
				});
	t6 = StaticText(w, Rect(299, 220, 50, 20))
				.string_("SYN3");
	b6_1 = Button(w, Rect(295, 245, 40, 20))
				.states_([
					["PTN1", Color.black, Color.clear],
					["PTN1", Color.black, Color.green]
					])
				.action_({
					Pdef(\syn3, Pbind(
						\instrument, \synth_chord2,
						\amp, (1/8) * Pfunc({syn3_mute}) * Pfunc({syn3_amp}),
						\pan, Pfunc({syn3_pan}),
						\dur, 2/4,
						\midinote, Pseq([[60,64,67]], inf),
						\dummy, Pfunc({arg evt;
							~host.sendMsg("/syn3", evt.at(\midinote));
						})
					));
					b6_2.value_(0);
				});
	Pdef(\syn3).play(clock);
	b6_2 = Button(w, Rect(295, 270, 40, 20))
				.states_([
					["PTN2", Color.black, Color.clear],
					["PTN2", Color.black, Color.green]
					])
				.action_({
					Pdef(\syn3, Pbind(
						\instrument, \synth_chord2,
						\amp, (1/8) * Pfunc({syn3_mute}) * Pfunc({syn3_amp}),
						\pan, Pfunc({syn3_pan}),
						\dur, 2/4,
						\midinote, Pseq([[55,62,66],[59,62,69],[55,62,66]+12,
						[59,62,69],[54,61,66],[57,61,69],[54,61,66],[57,61,69]] , inf),
						\dummy, Pfunc({arg evt;
							~host.sendMsg("/syn3", evt.at(\midinote));
						})
					));
					b6_1.value_(0);
				});

	//--control 7	
	p7 = Knob(w, Rect(350, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m7 = Button(w, Rect(350, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 7);
						if(mute7 == true,
						 	{ mute7 = false;
						   	  drum4_mute = 1; },
						 	{ mute7 = true;
						   	  drum4_mute = 0; });
				});
	s7 = Button(w, Rect(350, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l7 = Slider(w, Rect(360, 115, 20, 100))
				.action_({
					drum4_amp = l7.value;
				});
	t7 = StaticText(w, Rect(350, 220, 50, 20))
				.string_("DRUM4");
				
	//--control 8	
	p8 = Knob(w, Rect(405, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m8 = Button(w, Rect(405, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({
					~host.sendMsg("/mute", 8);
						if(mute8 == true,
						 	{ mute8 = false;
						   	  syn4_mute = 1; },
						 	{ mute8 = true;
						   	  syn4_mute = 0; });
				});
	s8 = Button(w, Rect(405, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l8 = Slider(w, Rect(415, 115, 20, 100))
				.action_({
					syn4_amp = l8.value;
				});
	t8 = StaticText(w, Rect(405, 220, 50, 20))
				.string_("SYN4");
	b8_1 = Button(w, Rect(405, 245, 40, 20))
			.states_([
				["PTN1", Color.black, Color.clear],
				["PTN1", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 0;
				Pdef(\syn4, Pbind(
				\instrument, \syn4,
				// \amp, 0.3 * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
	    		\amp, Pseq([1,0,1,1,Pwrand([1,0],[0.1,0.9],1),0,1,0,
		           			1,1,0,1,Pwrand([1,0],[0.5,0.5],1),0,1,1],inf) *
							(1/8) * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
				\dur, 0.25,
				\midinote, Prand([Pseq([64,60,64,60,65,60,65,60,64,60,64,60,55,59,60,\], 1),
				  				  Pseq([64,60,64,60,67,60,67,60,65,60,65,60,55,59,60,\], 1),
				  				  Pseq([60,60,64,60,65,60,64,60,\,60,64,60,55,59,60,\], 1)
				 				 ], inf)
				));
				b8_2.value_(0);
				b8_3.value_(0);
				b8_4.value_(0);
			});
	b8_2 = Button(w, Rect(405, 270, 40, 20))
			.states_([
				["PTN2", Color.black, Color.clear],
				["PTN2", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 0;
				Pdef(\syn4, Pbind(
				\instrument, \syn4,
				// \amp, 0.3 * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
	    		\amp, Pseq([1,0,1,1,Pwrand([1,0],[0.1,0.9],1),0,1,0,
		           			1,1,0,1,Pwrand([1,0],[0.5,0.5],1),0,1,1],inf).stutter(2) *
							(1/8) * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
				\dur, 0.25/2,
				\midinote, Prand([Pseq([64,60,64,60,65,60,65,60,64,60,64,60,55,59,60,\], 1).stutter(2),
				  				  Pseq([64,60,64,60,67,60,67,60,65,60,65,60,55,59,60,\], 1).stutter(2),
				  				  Pseq([60,60,64,60,65,60,64,60,\,60,64,60,55,59,60,\], 1).stutter(2)
				 				 ], inf)
				));
				b8_1.value_(0);
				b8_3.value_(0);
				b8_4.value_(0);
			});
	b8_3 = Button(w, Rect(405, 295, 40, 20))
			.states_([
				["PTN3", Color.black, Color.clear],
				["PTN3", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 1;
				Pdef(\syn4, Pbind(
				\instrument, \syn4,
				\amp, (1/8) * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
				\dur, 0.25,
				\midinote, Prand([Pseq([64,60,64,60,65,60,65,60,64,60,64,60,55,59,60,\], 1),
				  				  Pseq([64,60,64,60,67,60,67,60,65,60,65,60,55,59,60,\], 1),
				  				  Pseq([60,60,64,60,65,60,64,60,\,60,64,60,55,59,60,\], 1)
				 				 ], inf)
				));
				b8_1.value_(0);
				b8_2.value_(0);
				b8_4.value_(0);
			});
	b8_4 = Button(w, Rect(405, 320, 40, 20))
			.states_([
				["PTN4", Color.black, Color.clear],
				["PTN4", Color.black, Color.green]
				])
			.action_({ syn2_ptn = 1;
				Pdef(\syn4, Pbind(
				\instrument, \syn4,
				\amp, (1/8) * Pfunc({syn4_mute}) * Pfunc({syn4_amp}),
				\dur, 0.25/2,
				\midinote, Prand([Pseq([64,60,64,60,65,60,65,60,64,60,64,60,55,59,60,\], 1).stutter(2),
				  				  Pseq([64,60,64,60,67,60,67,60,65,60,65,60,55,59,60,\], 1).stutter(2),
				  				  Pseq([60,60,64,60,65,60,64,60,\,60,64,60,55,59,60,\], 1).stutter(2)
				 				 ], inf)
				));
				b8_1.value_(0);
				b8_2.value_(0);
				b8_3.value_(0);
			});

	//--control 9	
	p9 = Knob(w, Rect(460, 20, 40, 40))
				.centered_(true)
				.value_(0.5);
	m9 = Button(w, Rect(460, 65, 40, 20))
				.states_([
					["M", Color.black, Color.clear],
					["M", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	s9 = Button(w, Rect(460, 90, 40, 20))
				.states_([
					["S", Color.black, Color.clear],
					["S", Color.black, Color.red]
					])
				.action_({ arg butt;
					butt.value.postln;
				});
	l9 = Slider(w, Rect(470, 115, 20, 100))
				.action_({
					ura_amp = l9.value;
				});	
	t9 = StaticText(w, Rect(460, 220, 50, 20))
				.string_("SYN4");
	b9_1 = Button(w, Rect(460, 245, 40, 20))
			.states_([
				["PTN1", Color.black, Color.clear],
				["PTN1", Color.black, Color.green]
				])
			.action_({ arg butt;
				if(butt.value == 1,
				   { Pdef(\1).play;
				   	 Pdef(\syn1).stop;
				   	 Pdef(\syn2).stop;
				   	 Pdef(\syn3).stop;
				   	 Pdef(\syn4).stop;
					 Pdef(\drum1).stop;
					 Pdef(\drum2).stop;
					 Pdef(\drum3).stop;
					 Pdef(\drum4).stop;
					 /*
				     syn1_mute = 0;
				     syn2_mute = 0;
				     syn3_mute = 0;
				     syn4_mute = 0;
					 drum1_mute = 0;
					 drum2_mute = 0;
					 drum3_mute = 0;
					 drum4_mute = 0; 
					 */
					 },
				   { Pdef(\1).stop; 
					 Pdef(\syn1).play(clock);
					 Pdef(\syn2).play(clock);
					 Pdef(\syn3).play(clock);
					 Pdef(\syn4).play(clock);
					 Pdef(\drum1).play(clock);
					 Pdef(\drum2).play(clock);
					 Pdef(\drum3).play(clock);
					 Pdef(\drum4).play(clock);
					 /*
				     syn1_mute = 1;
				     syn2_mute = 1;
				     syn3_mute = 1;
				     syn4_mute = 1;
					 drum1_mute = 1;
					 drum2_mute = 1;
					 drum3_mute = 1;
					 drum4_mute = 0; 
					 */
					 });
					~host.sendMsg("/invert", 0);
				});
	b9_2 = Button(w, Rect(460, 270, 40, 20))
			.states_([
				["PTN2", Color.black, Color.clear],
				["PTN2", Color.black, Color.green]
				])
			.action_({ arg butt;
				if(butt.value == 1,
				   { Pdef(\2).play; },
				   { Pdef(\2).stop; });
				});
	b9_3 = Button(w, Rect(460, 295, 40, 20))
			.states_([
				["PTN3", Color.black, Color.clear],
				["PTN3", Color.black, Color.green]
				])
			.action_({ arg butt;
				if(butt.value == 1,
				   { Pdef(\3).play; },
				   { Pdef(\3).stop; });
				});
	b9_4 = Button(w, Rect(460, 320, 40, 20))
			.states_([
				["PTN4", Color.black, Color.clear],
				["PTN4", Color.black, Color.green]
				])
			.action_({ arg butt;
				if(butt.value == 1,
				   { Pdef(\4).play; },
				   { Pdef(\4).stop; });
				});
	b9_5 = Button(w, Rect(460, 345, 40, 20))
			.states_([
				["ECHO", Color.black, Color.clear],
				["ECHO", Color.black, Color.green]
				])
			.action_({ arg butt;
				if(butt.value == 1,
				   { rev = Synth(\rev); },
				   { rev.free; });
				~host.sendMsg("/echo", 0);
			});

	//--control 10
	b10_1 = Button(w, Rect(515, 245, 40, 20))
				.states_([
					["PTN1", Color.black, Color.clear],
					["PTN1", Color.black, Color.blue]
					])
				.action_({
					// バリエーションG
					kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0],inf);
					snare_ptn     = Pseq([0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0],inf);
					hihat_ptn     = Pseq([1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,1],inf);
					synthdrum_ptn = Pseq([0,0,1,0,0,1,0,0,1,0,0,1,0,1,1,1],inf);
					drum_pdefs.value;
					b10_2.value_(0);
					b10_3.value_(0);
					b10_4.value_(0);
					b10_5.value_(0);
					b10_6.value_(0);
					b10_7.value_(0);
					b10_8.value_(0);
				});
	b10_2 = Button(w, Rect(515, 270, 40, 20))
				.states_([
					["PTN2", Color.black, Color.clear],
					["PTN2", Color.black, Color.blue]
					])
				.action_({
					// バリエーションI
					kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0],inf);
					snare_ptn     = Pseq([0,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0],inf);
					hihat_ptn     = Pseq([1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,1],inf);
					synthdrum_ptn = Pseq([1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_3.value_(0);
					b10_4.value_(0);
					b10_5.value_(0);
					b10_6.value_(0);
					b10_7.value_(0);
					b10_8.value_(0);
				});
	b10_3 = Button(w, Rect(515, 295, 40, 20))
				.states_([
					["PTN3", Color.black, Color.clear],
					["PTN3", Color.black, Color.blue]
					])
				.action_({
					// バリエーションK
					kick_ptn      = Pseq([1,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0],inf);
					snare_ptn     = Pseq([0,0,0,0,1,0,0,1,0,1,0,0,1,0,0,0],inf);
					hihat_ptn     = Pseq([0,0,1,0,0,1,0,0,1,0,1,0,0,1,0,1],inf);
					synthdrum_ptn = Pseq([1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_2.value_(0);
					b10_4.value_(0);
					b10_5.value_(0);
					b10_6.value_(0);
					b10_7.value_(0);
					b10_8.value_(0);
				});
	b10_4 = Button(w, Rect(515, 320, 40, 20))
				.states_([
					["PTN4", Color.black, Color.clear],
					["PTN4", Color.black, Color.blue]
					])
				.action_({
					// バリエーションH
					kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,1,1,0,1,0],inf);
					snare_ptn     = Pseq([0,0,1,0,0,1,0,1,0,0,1,0,0,1,0,1],inf);
					hihat_ptn     = Pseq([0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,1],inf);
					synthdrum_ptn = Pseq([0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_2.value_(0);
					b10_3.value_(0);
					b10_5.value_(0);
					b10_6.value_(0);
					b10_7.value_(0);
					b10_8.value_(0);
				});
	b10_5 = Button(w, Rect(570, 245, 40, 20))
				.states_([
					["PTN5", Color.black, Color.clear],
					["PTN5", Color.black, Color.blue]
					])
				.action_({
					// バリエーションE
					kick_ptn      = Pseq([1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0],inf);
					snare_ptn     = Pseq([0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0],inf);
					hihat_ptn     = Pseq([0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1],inf);
					synthdrum_ptn = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_2.value_(0);
					b10_3.value_(0);
					b10_4.value_(0);
					b10_6.value_(0);
					b10_7.value_(0);
					b10_8.value_(0);
				});
	b10_6 = Button(w, Rect(570, 270, 40, 20))
				.states_([
					["PTN6", Color.black, Color.clear],
					["PTN6", Color.black, Color.blue]
					])
				.action_({
					// バリエーションC
					kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0],inf);
					snare_ptn     = Pseq([0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0],inf);
					hihat_ptn     = Pseq([0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,0],inf);
					synthdrum_ptn = Pseq([0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_2.value_(0);
					b10_3.value_(0);
					b10_4.value_(0);
					b10_5.value_(0);
					b10_7.value_(0);
					b10_8.value_(0);
				});
	b10_7 = Button(w, Rect(570, 295, 40, 20))
				.states_([
					["PTN7", Color.black, Color.clear],
					["PTN7", Color.black, Color.blue]
					])
				.action_({
					// バリエーションF
					kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0],inf);
					snare_ptn     = Pseq([0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0],inf);
					hihat_ptn     = Pseq([1,1,0,1,1,0,1,0,1,1,0,1,1,1,0,1],inf);
					synthdrum_ptn = Pseq([1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_2.value_(0);
					b10_3.value_(0);
					b10_4.value_(0);
					b10_5.value_(0);
					b10_6.value_(0);
					b10_8.value_(0);
				});
	b10_8 = Button(w, Rect(570, 320, 40, 20))
				.states_([
					["PTN8", Color.black, Color.clear],
					["PTN8", Color.black, Color.blue]
					])
				.action_({
					// バリエーションM
					kick_ptn      = Pseq([1,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0],inf);
					snare_ptn     = Pseq([0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],inf);
					hihat_ptn     = Pseq([1,1,0,0,1,1,1,0,1,0,1,0,1,0,1,1],inf);
					synthdrum_ptn = Pseq([0,0,0,1,0,0,1,1,0,0,1,1,0,0,1,1],inf);
					drum_pdefs.value;
					b10_1.value_(0);
					b10_2.value_(0);
					b10_3.value_(0);
					b10_4.value_(0);
					b10_5.value_(0);
					b10_6.value_(0);
					b10_7.value_(0);
				});
	b10_9 = Button(w, Rect(515, 345, 40+15+40, 20))
				.states_([
					["SLOW", Color.black, Color.green],
					["FAST", Color.black, Color.red]
					])
				.action_({ arg butt;
					if(butt.value == 0,
						{ drum_dur = 0.25; },
						{ drum_dur = 0.25/2; });
					drum_pdefs.value;
				});
	
	//--initialize GUI variables
	m1.value_(1);
	m2.value_(1);
	m3.value_(1);
	m4.value_(1);
	m5.value_(1);
	m6.value_(1);
	m7.value_(1);
	m8.value_(1);
	b3_1.value_(1);
	b6_1.value_(1);
	b8_1.value_(1);
	b10_1.value_(1);

	w.front;
};
)
