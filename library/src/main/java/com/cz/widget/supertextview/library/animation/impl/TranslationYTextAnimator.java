package com.cz.widget.supertextview.library.animation.impl;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.graphics.Region;

import com.cz.widget.supertextview.library.animation.AnimationLetter;
import com.cz.widget.supertextview.library.animation.ITextAnimator;

/**
 * @author Created by cz
 * @date 2019-05-15 16:53
 * @email bingo110@126.com
 */
public class TranslationYTextAnimator implements ITextAnimator {
    @Override
    public Animator getEnterAnimator(AnimationLetter animationLetter) {
        Rect bounds = animationLetter.getBounds();
        animationLetter.setTranslationY(-bounds.height());
        animationLetter.setClipRect(bounds, Region.Op.INTERSECT);
        ObjectAnimator valueAnimator = ObjectAnimator.ofFloat(animationLetter,"translationY",0);
        return valueAnimator;
    }

    @Override
    public Animator getExitAnimator(AnimationLetter animationLetter) {
        Rect bounds = animationLetter.getBounds();
        animationLetter.setClipRect(bounds, Region.Op.INTERSECT);
        ObjectAnimator valueAnimator = ObjectAnimator.ofFloat(animationLetter,"translationY",bounds.height());
        return valueAnimator;
    }
}
