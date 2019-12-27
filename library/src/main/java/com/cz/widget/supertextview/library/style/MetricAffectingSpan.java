package com.cz.widget.supertextview.library.style;

import android.text.TextPaint;

/**
 * The classes that affect character-level text formatting in a way that
 * changes the width or height of characters extend this class.
 */
public abstract class MetricAffectingSpan extends CharacterStyle implements UpdateLayout {

	public abstract void updateMeasureState(TextPaint p);

    /**
     * Returns "this" for most MetricAffectingSpans, but for 
     * MetricAffectingSpans that were generated by {@link #wrap},
     * returns the underlying MetricAffectingSpan.
     */
    @Override
    public MetricAffectingSpan getUnderlying() {
        return this;
    }

    /**
     * A Passthrough MetricAffectingSpan is one that
     * passes {@link #updateDrawState} and {@link #updateMeasureState}
     * calls through to the specified MetricAffectingSpan 
     * while still being a distinct object,
     * and is therefore able to be attached to the same Spannable
     * to which the specified MetricAffectingSpan is already attached.
     */
    /* package */ public static class Passthrough extends MetricAffectingSpan {
        private MetricAffectingSpan mStyle;
        
        /**
         * Creates a new Passthrough of the specfied MetricAffectingSpan.
         */
        public Passthrough(MetricAffectingSpan cs) {
            mStyle = cs;
        }

        /**
         * Passes updateDrawState through to the underlying MetricAffectingSpan.
         */
        @Override
        public void updateDrawState(TextPaint tp) {
            mStyle.updateDrawState(tp);
        }

        /**
         * Passes updateMeasureState through to the underlying MetricAffectingSpan.
         */
        @Override
        public void updateMeasureState(TextPaint tp) {
            mStyle.updateMeasureState(tp);
        }
    
        /**
         * Returns the MetricAffectingSpan underlying this one, or the one
         * underlying it if it too is a Passthrough.
         */
        @Override
        public MetricAffectingSpan getUnderlying() {
            return mStyle.getUnderlying();
        }
    }
}
