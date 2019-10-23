package fptt.example.bookshop;

import android.app.Activity;
import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

public class KProgressHUDManager {
    public static KProgressHUD showProgessBar(Context context, String label){
        if(!((Activity)(context)).isFinishing()){
            KProgressHUD hud =  KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(label)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
            return hud;
        }
        return null;
    }

}
