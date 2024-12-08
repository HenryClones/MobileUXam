package com.example.mobileuxam

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout

class RuinableConstraintLayout : ConstraintLayout, ViewRuinerComponent {
    companion object {
        // Helene stole decorator functionality. Oops!!
        var justMessMyStuffUp: Boolean = false
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (justMessMyStuffUp) {
            canvas.drawColor(Color.argb(160, 240, 240, 240))
        }
    }
}