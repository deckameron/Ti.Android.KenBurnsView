var window = Titanium.UI.createWindow({
    backgroundColor : '#262626',
    theme : 'Theme.MyTheme'
});

var KenBurnsView = require('ti.android.kenburnsview');

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
