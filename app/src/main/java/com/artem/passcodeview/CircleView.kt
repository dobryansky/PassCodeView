package com.artem.passcodeview
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutLinearInInterpolator

internal class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val fillCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.green)
        style = Paint.Style.FILL
    }

   private val fillAndStrokeCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.green)
        style = Paint.Style.FILL_AND_STROKE
    }

    private var radius = 16f
    private var animator: ValueAnimator? = null
    private var inputAndRemoveAnimationDuration = 200L

    private var progress = 0.0f
        set(value) {
            field = value
            postInvalidateOnAnimation()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = (2 * radius).toInt()
        val height = (2 * radius).toInt()
        setMeasuredDimension(width, height)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        // fill circle
        canvas.drawCircle(
            radius,
            radius,
            lerp(radius, 0f, progress),
            fillCirclePaint
        )
        // fill and stroke circle
        canvas.drawCircle(
            radius,
            radius,
            lerp(0f, radius, progress),
            fillAndStrokeCirclePaint
        )
    }

    fun animateAndInvoke(onEnd: (() -> Unit)? = null) {
        if (animator != null) {
            return
        }
        val newProgress = if (progress == 0f) 1f else 0f
        animator = ValueAnimator.ofFloat(progress, newProgress).apply {
            duration = inputAndRemoveAnimationDuration
            addUpdateListener {
                progress = it.animatedValue as Float
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    animator = null
                    onEnd?.invoke()
                }
            })
            interpolator = FastOutLinearInInterpolator()
        }
        animator?.start()
    }

    fun setRadius(radius: Float) {
        this.radius = radius
        invalidate()
    }

    fun setFillCircleColor(color: Int) {
        fillCirclePaint.color = color
        postInvalidateOnAnimation()
    }


    fun setFillAndStrokeCircleColor(color: Int) {
        fillAndStrokeCirclePaint.color = color
        postInvalidateOnAnimation()
    }


    fun isAnimating(): Boolean = animator != null

    fun getFillAndStrokeCircleColor(): Int = fillAndStrokeCirclePaint.color

    fun getFillCircleColor(): Int = fillCirclePaint.color

    fun setInputAndRemoveAnimationDuration(duration: Long) {
        inputAndRemoveAnimationDuration = duration
    }

    /*
     * Linearly interpolate between two values.
     */
    private fun lerp(a: Float, b: Float, t: Float): Float {
        return a + (b - a) * t
    }
}