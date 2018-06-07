# Ti.Android.KevinBurnsView

Titanium Android library that provides access to [Flávio Faria](https://github.com/flavioarfaria) [KenBurnsView](https://github.com/flavioarfaria/KenBurnsView) library. It is an extension to *ImageView* that creates an immersive
experience by animating its drawable using the [Ken Burns Effect](https://en.wikipedia.org/wiki/Ken_Burns_effect).

![enter image description here](https://github.com/deckameron/Ti.Android.KevinBurnsView/blob/master/documentation/anim.gif)

## How to use
```javascript
var KenBurnsView = require('ti.android.kenburnsview');

var window = Titanium.UI.createWindow({
backgroundColor : '#262626',
theme : 'Theme.MyTheme'
});

var scrollView = Titanium.UI.createScrollView({
	top : 0,
	height : 1000,
	layout : "vertical",
	backgroundColor : "#FFF"
});
window.add(scrollView);

var kbView = KenBurnsView.createImageView({
	top : 0,
	left : 0,
	right : 0,
	height : 250,
	interpolation : {
		duration : 25000,
		easing : KenBurnsView.EASE_IN
	},
	image : "https://images3.alphacoders.com/823/thumb-1920-82317.jpg"
});
scrollView.add(kbView);

kbView.addEventListener(KenBurnsView.TRANSITION_STARTED, function(){
	Titanium.API.info("TRANSITION_STARTED");
});

kbView.addEventListener(KenBurnsView.TRANSITION_ENDED, function(){
	Titanium.API.info("TRANSITION_ENDED");
});

window.open();
```

# Methods
Simple methods to control the view animation
|Methods                |Description                          |
|----------------|-------------------------------|
|pause			|Pauses the view animation                     
|resume    	|Resumes the view animation     

# Attributes
Simple methods to control the view animation
|attributes                |Description                          |
|----------------|-------------------------------|
|image			|Image resource (local or url)            
|transition    	|An object with **duration**, **easing**  and customEasing**strong text**    

## transition
|attributes                |Description                          |
|----------------|-------------------------------|
|duration			|duration of animation in milliseconds       
|easing    	|Pre-created easing curves you would to use     
|customEasing| Your own custom curve. A Object with **x1**, **x2**, **y1**, **y2**

## Easing curves
|attributes      | 
|----------------|
|LINEAR			
|EASE_IN    	
|EASE_IN_OUT    
|EASE_IN_QUAD    	
|EASE_IN_CUBIC   
|EASE_IN_QUART    	
|EASE_IN_QUINT    
|EASE_IN_SINE    
|EASE_IN_EXPO    
|EASE_IN_CIRC    	
|EASE_IN_BACK    
|EASE_OUT
|EASE_OUT_QUAD
|EASE_OUT_CUBIC
|EASE_OUT_QUART
|EASE_OUT_QUINT
|EASE_OUT_SINE
|EASE_OUT_EXPO
|EASE_OUT_CIRC
|EASE_OUT_BACK
|EASE_IN_OUT_QUAD
|EASE_IN_OUT_CUBIC
|EASE_IN_OUT_QUART
|EASE_IN_OUT_QUINT
|EASE_IN_OUT_SINE
|EASE_IN_OUT_EXPO
|EASE_IN_OUT_CIRC
|EASE_IN_OUT_BACK


# Custom Easing
You can use [this website](https://matthewlein.com/tools/ceaser) to create your own easing curves
       
![enter image description here](https://github.com/deckameron/Ti.Android.KevinBurnsView/blob/master/documentation/easing.png)

