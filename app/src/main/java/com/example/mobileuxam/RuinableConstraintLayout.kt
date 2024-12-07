package com.example.mobileuxam

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class RuinableConstraintLayout : ConstraintLayout, ViewRuinerComponent {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (Random.nextBoolean()) {
            canvas.rotate(90.0f)
        }
    }
}