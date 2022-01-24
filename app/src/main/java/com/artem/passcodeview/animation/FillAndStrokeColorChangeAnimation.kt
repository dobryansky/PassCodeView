import com.artem.passcodeview.CircleView



internal class FillAndStrokeColorChangeAnimation(
    private val circleView: CircleView
) : ColorChangeAnimation(circleView) {

    override fun getColor(): Int = circleView.getFillAndStrokeCircleColor()

    override fun setColor(color: Int) {
        circleView.setFillAndStrokeCircleColor(color)
    }
}
