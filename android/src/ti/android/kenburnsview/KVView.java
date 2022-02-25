package ti.android.kenburnsview;

import java.util.HashMap;
import java.util.List;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollPropertyChange;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.view.TiUIView;

import android.content.Context;
import android.view.animation.Interpolator;

import androidx.core.view.animation.PathInterpolatorCompat;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.flaviofaria.kenburnsview.KenBurnsView.TransitionListener;

public class KVView extends TiUIView{

    private static final String LCAT = "kenburnsview";

    Context context;
    KenBurnsView kview;
    TiViewProxy static_proxy;
    KrollDict props = new KrollDict();

    public KVView(TiViewProxy proxy) {

        super(proxy);

        static_proxy = proxy;
        context = proxy.getActivity();
        kview = new KenBurnsView(context);

        kview.setTransitionListener(new TransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                static_proxy.fireEvent(KenburnsviewModule.TRANSITION_ENDED, props);
            }
            @Override
            public void onTransitionStart(Transition transition) {
                static_proxy.fireEvent(KenburnsviewModule.TRANSITION_STARTED, props);
            }
        });

        setNativeView(kview);
    }

    @Override
    public void processProperties(KrollDict d)
    {
        super.processProperties(d);

        String[] properties = {
                "image",
                "interpolation",
        };

        for (String key : properties) {
            if (d.containsKey(key)) {
                this.applyPropertyChanges(key, d.get(key));
            }
        }
    }

    public void applyPropertyChanges(String key, Object value) {

        if (key.equals("image")) {
            String imageUrl = TiConvert.toString(value);
            Glide.with(context).load(imageUrl).into(kview);
        }

        if (key.equals("interpolation")) {

            HashMap<?, ?> interpolation = (HashMap<?, ?>) value;

            if(interpolation.containsKey("duration")){

                Integer duration = (Integer) interpolation.get("duration");

                Interpolator customInterpolator = PathInterpolatorCompat.create(0.390f, 0.575f, 0.565f, 1.000f);

                if (interpolation.containsKey("customEasing")) {

                    HashMap<?, ?> customEasing = (HashMap<?, ?>) interpolation.get("customEasing");

                    if (customEasing.containsKey("x1") &&
                            customEasing.containsKey("x2") &&
                            customEasing.containsKey("y1") &&
                            customEasing.containsKey("y2")) {

                        Float x1 = Float.parseFloat((String) customEasing.get("x1"));
                        Float x2 = Float.parseFloat((String) customEasing.get("x2"));
                        Float y1 = Float.parseFloat((String) customEasing.get("y1"));
                        Float y2 = Float.parseFloat((String) customEasing.get("y2"));

                        customInterpolator = PathInterpolatorCompat.create(x1, x2, y1, y2);
                    }

                    if(interpolation.containsKey("easing")){
                        Log.w(LCAT, "EASING value will be ignored, because you are also using CUSTOMEASING.");
                    }

                }else if(interpolation.containsKey("easing")){
                    Integer easing = (Integer) interpolation.get("easing");

                    switch (easing) {
                        case KenburnsviewModule.LINEAR:
                            customInterpolator = PathInterpolatorCompat.create(0.250f, 0.250f, 0.750f, 0.750f);
                            break;
                        case KenburnsviewModule.EASE:
                            customInterpolator = PathInterpolatorCompat.create(0.250f, 0.100f, 0.250f, 1.000f);
                            break;

                        case KenburnsviewModule.EASE_IN:
                            customInterpolator = PathInterpolatorCompat.create(0.420f, 0.000f, 1.000f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT:
                            customInterpolator = PathInterpolatorCompat.create(0.420f, 0.000f, 0.580f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_IN_QUAD:
                            customInterpolator = PathInterpolatorCompat.create(0.550f, 0.085f, 0.680f, 0.530f);
                            break;
                        case KenburnsviewModule.EASE_IN_CUBIC:
                            customInterpolator = PathInterpolatorCompat.create(0.550f, 0.055f, 0.675f, 0.190f);
                            break;
                        case KenburnsviewModule.EASE_IN_QUART:
                            customInterpolator = PathInterpolatorCompat.create(0.895f, 0.030f, 0.685f, 0.220f);
                            break;
                        case KenburnsviewModule.EASE_IN_QUINT:
                            customInterpolator = PathInterpolatorCompat.create(0.755f, 0.050f, 0.855f, 0.060f);
                            break;
                        case KenburnsviewModule.EASE_IN_SINE:
                            customInterpolator = PathInterpolatorCompat.create(0.470f, 0.000f, 0.745f, 0.715f);
                            break;
                        case KenburnsviewModule.EASE_IN_EXPO:
                            customInterpolator = PathInterpolatorCompat.create(0.950f, 0.050f, 0.795f, 0.035f);
                            break;
                        case KenburnsviewModule.EASE_IN_CIRC:
                            customInterpolator = PathInterpolatorCompat.create(0.600f, 0.040f, 0.980f, 0.335f);
                            break;
                        case KenburnsviewModule.EASE_IN_BACK:
                            customInterpolator = PathInterpolatorCompat.create(0.600f, -0.280f, 0.735f, 0.045f);
                            break;

                        case KenburnsviewModule.EASE_OUT:
                            customInterpolator = PathInterpolatorCompat.create(0.000f, 0.000f, 0.580f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_QUAD:
                            customInterpolator = PathInterpolatorCompat.create(0.250f, 0.460f, 0.450f, 0.940f);
                            break;
                        case KenburnsviewModule.EASE_OUT_CUBIC:
                            customInterpolator = PathInterpolatorCompat.create(0.215f, 0.610f, 0.355f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_QUART:
                            customInterpolator = PathInterpolatorCompat.create(0.165f, 0.840f, 0.440f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_QUINT:
                            customInterpolator = PathInterpolatorCompat.create(0.230f, 1.000f, 0.320f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_SINE:
                            customInterpolator = PathInterpolatorCompat.create(0.390f, 0.575f, 0.565f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_EXPO:
                            customInterpolator = PathInterpolatorCompat.create(0.190f, 1.000f, 0.220f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_CIRC:
                            customInterpolator = PathInterpolatorCompat.create(0.075f, 0.820f, 0.165f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_OUT_BACK:
                            customInterpolator = PathInterpolatorCompat.create(0.175f, 0.885f, 0.320f, 1.275f);
                            break;

                        case KenburnsviewModule.EASE_IN_OUT_QUAD:
                            customInterpolator = PathInterpolatorCompat.create(0.455f, 0.030f, 0.515f, 0.955f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_CUBIC:
                            customInterpolator = PathInterpolatorCompat.create(0.645f, 0.045f, 0.355f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_QUART:
                            customInterpolator = PathInterpolatorCompat.create(0.770f, 0.000f, 0.175f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_QUINT:
                            customInterpolator = PathInterpolatorCompat.create(0.860f, 0.000f, 0.070f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_SINE:
                            customInterpolator = PathInterpolatorCompat.create(0.445f, 0.050f, 0.550f, 0.950f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_EXPO:
                            customInterpolator = PathInterpolatorCompat.create(1.000f, 0.000f, 0.000f, 1.000f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_CIRC:
                            customInterpolator = PathInterpolatorCompat.create(0.785f, 0.135f, 0.150f, 0.860f);
                            break;
                        case KenburnsviewModule.EASE_IN_OUT_BACK:
                            customInterpolator = PathInterpolatorCompat.create(0.680f, -0.550f, 0.265f, 1.550f);
                            break;
                    }
                }

                RandomTransitionGenerator generator = new RandomTransitionGenerator(duration, customInterpolator);
                kview.setTransitionGenerator(generator);
            }else{
                Log.w(LCAT, "DURATION is required to use EASING and CUSTOMEASING. Using lib standard easing instead...");
            }
        }
    }

    @Override
    public void propertyChanged(String key, Object oldValue, Object newValue, KrollProxy proxy) {
        super.propertyChanged(key, oldValue, newValue, proxy);

        this.applyPropertyChanges(key, newValue);
    }

    @Override
    public void propertiesChanged(List<KrollPropertyChange> changes, KrollProxy proxy) {
        for (KrollPropertyChange change : changes) {
            propertyChanged(change.getName(), change.getOldValue(), change.getNewValue(), proxy);
        }
    }

    // Methods
    public void pause()
    {
        this.kview.pause();
    }

    public void resume()
    {
        this.kview.resume();
    }

    public KenBurnsView getKVView(){
        return this.kview;
    };
}