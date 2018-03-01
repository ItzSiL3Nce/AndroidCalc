package silence.it.calcavanzata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num0Btn.setOnClickListener { onNumberClick(it) }
        num1Btn.setOnClickListener { onNumberClick(it) }
        num2Btn.setOnClickListener { onNumberClick(it) }
        num3Btn.setOnClickListener { onNumberClick(it) }
        num4Btn.setOnClickListener { onNumberClick(it) }
        num5Btn.setOnClickListener { onNumberClick(it) }
        num6Btn.setOnClickListener { onNumberClick(it) }
        num7Btn.setOnClickListener { onNumberClick(it) }
        num8Btn.setOnClickListener { onNumberClick(it) }
        num9Btn.setOnClickListener { onNumberClick(it) }

        sqrtBtn.setOnClickListener { onFunctionClick(it) }
        logBtn.setOnClickListener { onFunctionClick(it)}
        fibBtn.setOnClickListener { onFunctionClick(it) }

        addBtn.setOnClickListener { onOperatorClick(it) }
        subBtn.setOnClickListener { onOperatorClick(it) }
        multBtn.setOnClickListener{ onOperatorClick(it) }
        divBtn.setOnClickListener { onOperatorClick(it) }
        powBtn.setOnClickListener { onOperatorClick(it) }
        percBtn.setOnClickListener { onOperatorClick(it) }

        piBtn.setOnClickListener { onConstantClick(it) }
        eBtn.setOnClickListener { onConstantClick(it) }
        
        pointBtn.setOnClickListener { onPointClick() }

        equalsBtn.setOnClickListener { onEqualsClick() }
        cancelBtn.setOnClickListener { onCancelClick() }
        backBtn.setOnClickListener { onBackClick() }

    }

    private fun onNumberClick(v: View) {
        val text = result.text.toString()
        if(canPutNumber(text))
            result.text = ("$text${(v as Button).text}")
    }

    private fun onFunctionClick(v: View) {
        val text = result.text.toString()
        if(canPutFunction(text))
            result.text = ("$text${(v as Button).text}")
    }

    private fun onConstantClick(v: View) {
        val text = result.text.toString()
        if(canPutConstant(text))
            result.text = ("$text${(v as Button).text}")
    }

    private fun onOperatorClick(v: View) {
        val text = result.text.toString()
        if(canPutOperator(text))
            result.text = ("$text${(v as Button).text}")
    }

    private fun onPointClick() {
        val text = result.text.toString()
        if(canPutPoint(text))
            result.text = ("$text.")
    }

    private fun onEqualsClick() {
        val text = result.text.toString()
        if(canCalculate(text))
            result.text = Calculator.calc(this, text)
    }

    private fun onBackClick() {
        val text = result.text.toString()
        if(!text.isEmpty())
            result.text = text.substring(0, text.length - 1)
    }

    private fun onCancelClick() {
        result.text = ""
    }
}
