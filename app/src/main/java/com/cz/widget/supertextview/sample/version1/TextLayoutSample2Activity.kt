package com.cz.widget.supertextview.sample.version1

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.EmbossMaskFilter
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.cz.widget.supertextview.library.span.*
import com.cz.widget.supertextview.library.spannable.SpannableString
import com.cz.widget.supertextview.sample.R
import com.cz.widget.supertextview.sample.linedecoration.HighlightLineDecoration
import com.okay.sampletamplate.ToolBarActivity
import com.okay.sampletamplate.data.DataProvider
import kotlinx.android.synthetic.main.activity_text_layout_sample2.*
import kotlin.random.Random


class TextLayoutSample2Activity : ToolBarActivity() {
    companion object{
        private const val TAG="TextLayoutSample2Activity"
        private const val SPAN_COUNT=13
    }
    private val output=StringBuilder()
    private var lineDecoration: HighlightLineDecoration?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_layout_sample2)
        //点击设置不同的布局
        lineDecoration=HighlightLineDecoration(this)
        createText()
        copyButton.setOnClickListener {
            val myClipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = output.toString()
            val myClip = ClipData.newPlainText("text", text)
            myClipboard.primaryClip = myClip
            Toast.makeText(applicationContext, "$text 已复制~", Toast.LENGTH_SHORT).show()
        }
        randomButton.setOnClickListener {
            createText()
        }
    }

    private fun createText(){
        val spanCount=20+Random.nextInt(10)
        val text=(0 until spanCount).
            map { DataProvider.ITEMS[DataProvider.RANDOM.nextInt(DataProvider.ITEMS.size)] }.joinToString(" ")
        val spannableString=SpannableString(text)
        //初始化span
        var start=0
        val positionList= mutableListOf<Int>()
        val spanPositionList= mutableListOf<Int>()
        while(-1!=start){
            val i=Random.nextInt(SPAN_COUNT)
            var index=text.indexOf(" ",start+1)
            if(-1!=index){
                spanPositionList.add(i)
                val textSpan = getTextSpan(i)
                if(null!=textSpan){
                    positionList.add(index)
                    spannableString.setSpan(textSpan, index, Math.min(text.length,index+5), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
            }
            start=index
        }
        output.clear()
        output.append("$text\n")
        output.append("$positionList\n")
        output.append("$spanPositionList\n")
        textLayout.clear()
        textLayout.setLineDecoration(lineDecoration)
        textLayout.setText(spannableString)

//        val spanCount=8
//        val text=(0 until spanCount).
//            map { DataProvider.ITEMS[it] }.joinToString(" ")
//        val spannableString=SpannableString(text)
//        var lineStart=19
//        var textSpan = getTextSpan(1)
//        if(null!=textSpan){
//            spannableString.setSpan(textSpan, lineStart, lineStart+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        lineStart=28
//        textSpan = getTextSpan(0)
//        if(null!=textSpan){
//            spannableString.setSpan(textSpan, lineStart, lineStart+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }

//        Carre de l'Est Swiss Sap Sago Oschtjepka Capriole Banon Brie de Meaux Sainte Maure Acorn Pelardon des Corbieres Grand Vatel Abbaye de Belloc Frinault Venaco Pave de Chirac Olde York Kashta Petit-Suisse Cornish Pepper Truffe Allgauer Emmentaler Vendomois
//        [5, 8, 14, 20, 24, 29, 40, 49, 55, 60, 63, 69, 76, 82, 88, 97, 101, 111, 117, 123, 130, 133, 140, 149, 156, 161, 164, 171, 176, 181, 188, 201, 209, 216, 223, 232, 243]
//        [12, 1, 6, 2, 9, 6, 4, 12, 6, 6, 3, 0, 5, 5, 4, 9, 0, 9, 4, 0, 1, 0, 5, 8, 6, 6, 7, 5, 2, 10, 5, 0, 8, 10, 11, 3, 7]

//        val text="Carre de l'Est Swiss Sap Sago Oschtjepka Capriole Banon Brie de Meaux Sainte Maure Acorn Pelardon des Corbieres Grand Vatel Abbaye de Belloc Frinault Venaco Pave de Chirac Olde York Kashta Petit-Suisse Cornish Pepper Truffe Allgauer Emmentaler Vendomois"
//        val spannableString=SpannableString(text)
//        //初始化span
//        val positionList= mutableListOf(5, 8, 14, 20, 24, 29, 40, 49, 55, 60, 63, 69, 76, 82, 88, 97, 101, 111, 117, 123, 130, 133, 140, 149, 156, 161, 164, 171, 176, 181, 188, 201, 209, 216, 223, 232, 243)
//        val spanPositionList= mutableListOf(12, 1, 6, 2, 9, 6, 4, 12, 6, 6, 3, 0, 5, 5, 4, 9, 0, 9, 4, 0, 1, 0, 5, 8, 6, 6, 7, 5, 2, 10, 5, 0, 8, 10, 11, 3, 7)
//        positionList.forEachIndexed { index, position->
//            var textSpan = getTextSpan(spanPositionList[index])
//            spannableString.setSpan(textSpan, position, position+5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        //更新布局
//        textLayout.clear()
//        textLayout.setLineDecoration(lineDecoration)
//        textLayout.setText(spannableString)
    }

    /**
     * 根据位置动态获得Span对象
     */
    private fun getTextSpan(index:Int):Any?{
        return when(index){
            0->{
                val layoutInflater = LayoutInflater.from(this)
//                val index=Random.nextInt(3)
                val index=0
                val layout = when(index){
                    0->layoutInflater.inflate(R.layout.image_layout1, textLayout, false)
                    1->{
                        val textLayout=layoutInflater.inflate(R.layout.text_layout1, textLayout, false)
                        val textView=textLayout.findViewById<TextView>(R.id.textView)
                        textView.text=DataProvider.ITEMS[DataProvider.RANDOM.nextInt(DataProvider.ITEMS.size)]
                        textLayout
                    }
                    else->layoutInflater.inflate(R.layout.progress_layout1, textLayout, false)
                }
                ViewSpan(layout)
            }
            1->{
//                val d1 = resources.getDrawable(R.mipmap.ic_launcher)
//                d1.setBounds(0, 0, 100, 100)
//                ImageSpan(d1)
                val layout=layoutInflater.inflate(R.layout.progress_layout1, textLayout, false)
                ViewSpan(layout)
            }
            2-> BackgroundColorSpan(Color.RED)
            3-> ForegroundColorSpan(Color.YELLOW)
            4-> RelativeSizeSpan(2f)
            5-> MaskFilterSpan(BlurMaskFilter(3f, BlurMaskFilter.Blur.OUTER))
            6->MaskFilterSpan(EmbossMaskFilter(floatArrayOf(1f, 1f, 3f), 1.5f, 8f, 3f))
            7->StrikethroughSpan()
            8->UnderlineSpan()
            9->AbsoluteSizeSpan(20, true)
            10->RelativeSizeSpan(2f)
            11->ScaleXSpan(2f)
            12->StyleSpan(Typeface.BOLD_ITALIC)
            else -> null
        }
    }

}