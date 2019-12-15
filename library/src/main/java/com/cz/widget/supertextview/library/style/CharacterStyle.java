/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cz.widget.supertextview.library.style;

import android.text.TextPaint;

/**
 * The classes that affect character-level text formatting extend this
 * class.  Most extend its subclass {@link MetricAffectingSpan}, but simple
 * ones may just implement {@link UpdateAppearance}.
 */
public abstract class CharacterStyle {
	public abstract void updateDrawState(TextPaint tp);

    /**
     * A given CharacterStyle can only applied to a single region of a given
     * Spanned.  If you need to attach the same CharacterStyle to multiple
     * regions, you can use this method to wrap it with a new object that
     * will have the same effect but be a distinct object so that it can
     * also be attached without conflict.
     */
    public static CharacterStyle wrap(CharacterStyle cs) {
        if (cs instanceof MetricAffectingSpan) {
            return new MetricAffectingSpan.Passthrough((MetricAffectingSpan) cs);
        } else {
            return new Passthrough(cs);
        }
    }

    /**
     * Returns "this" for most CharacterStyles, but for CharacterStyles
     * that were generated by {@link #wrap}, returns the underlying
     * CharacterStyle.
     */
    public CharacterStyle getUnderlying() {
        return this;
    }

    /**
     * A Passthrough CharacterStyle is one that
     * passes {@link #updateDrawState} calls through to the
     * specified CharacterStyle while still being a distinct object,
     * and is therefore able to be attached to the same Spannable
     * to which the specified CharacterStyle is already attached.
     */
    private static class Passthrough extends CharacterStyle {
        private CharacterStyle mStyle;

        /**
         * Creates a new Passthrough of the specfied CharacterStyle.
         */
        public Passthrough(CharacterStyle cs) {
            mStyle = cs;
        }

        /**
         * Passes updateDrawState through to the underlying CharacterStyle.
         */
        @Override
        public void updateDrawState(TextPaint tp) {
            mStyle.updateDrawState(tp);
        }

        /**
         * Returns the CharacterStyle underlying this one, or the one
         * underlying it if it too is a Passthrough.
         */
        @Override
        public CharacterStyle getUnderlying() {
            return mStyle.getUnderlying();
        }
    }
}
