package com.ec3_ihb_app;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class NavigationIconClickListener implements View.OnClickListener {
    //se debe dar una animacion para desplegar en "y" el menu:
    private final AnimatorSet animatorSet = new AnimatorSet();

    private Context context;
    private View sheet;
    private Interpolator interpolator;

    //altura de la pantalla:
    private int height;

    //en false para que no muestre el menu hasta que no se haga click en el menu hamburguesa
    private boolean backdropShown = false;
    private Drawable openIcon;
    private Drawable closeIcon;

    NavigationIconClickListener(Context context, View sheet) { //se pasa el contexto, la vista
        //returna el contexto, su vista e indicar si tiene interpolacion o no el elemento:
        this(context, sheet, null);
    }

    NavigationIconClickListener(Context context, View sheet, @Nullable Interpolator interpolator) {
        this(context, sheet, interpolator, null, null);
    }

    //implementacion de la clase:
    NavigationIconClickListener(Context context, View sheet, @Nullable Interpolator interpolator,
                                @Nullable Drawable openIcon, @Nullable Drawable closeIcon) {
        this.context = context;
        this.sheet = sheet;
        this.interpolator = interpolator;
        this.openIcon = openIcon;
        this.closeIcon = closeIcon;

        //saber el alto de mi dispositivo usando libreria DisplayMetrics:
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
    }

    @Override
    public void onClick(View view) {
        //invertir accion: si esta abierto, lo cierra y viceversa
        backdropShown = !backdropShown;

        //remover efectos de animacion:
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        //actualice el icono:
        updateIcon(view);

        //trasladar el elemento en y cuando se haga click al menu:

        //la cantidad de dp que se trasladara en y:
        final  int translateY = height - context.getResources().getDimensionPixelSize(R.dimen.course_grid_reveal_height);

        //animacion:
        ObjectAnimator animator = ObjectAnimator.ofFloat(sheet, "translationY", backdropShown ? translateY : 0);
        //duracion de la animacio: 500ms
        animator.setDuration(500);

        if(interpolator != null) {
            animator.setInterpolator(interpolator);
        }

        //ejecutar animacion e iniciarla:
        animatorSet.play(animator);
        animator.start();
    }

    private void  updateIcon(View view) {
        //validar si la vista esta cerrada:
        if(openIcon != null & closeIcon != null) {
            //validar si es que en la vista existe una imagen:
            if(!(view instanceof ImageView)) {
                //si no existe la imagen, muestra un mensaje de error:
                throw new IllegalArgumentException("update Icon must be called on an ImageView");
            }
            //cuando el backdrop esta en true:
            if(backdropShown) {
                //quiero que muestre el icono de cerrado:
                ((ImageView) view).setImageDrawable(closeIcon);
            } else {
                //sino, el icono de abierto:
                ((ImageView) view).setImageDrawable(openIcon);
            }
        }
    }
}
