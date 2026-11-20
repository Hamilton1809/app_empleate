package com.example.app_empleate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class GraficoAvanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val porcentajes = floatArrayOf(40f, 10f, 30f, 7f, 13f)
    private val colores = intArrayOf(
        Color.parseColor("#00A8E8"), // 40% Azul claro
        Color.parseColor("#FFA500"), // 10% Naranja
        Color.parseColor("#2B7DE9"), // 30% Azul medio
        Color.parseColor("#7AC143"), // 7% Verde
        Color.parseColor("#6C757D")  // 13% Gris
    )

    private val paintSector = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val paintTexto = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 24f
        textAlign = Paint.Align.CENTER
        isFakeBoldText = true
    }

    private val rectF = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val size = minOf(width, height).toFloat()
        val padding = 8f
        rectF.set(padding, padding, size - padding, size - padding)

        var anguloInicio = -90f
        val radioTexto = (size / 2f) * 0.62f
        val centroX = size / 2f
        val centroY = size / 2f

        for (i in porcentajes.indices) {
            val barrido = (porcentajes[i] / 100f) * 360f
            paintSector.color = colores[i]
            canvas.drawArc(rectF, anguloInicio, barrido, true, paintSector)

            val anguloMedioRad = Math.toRadians((anguloInicio + barrido / 2f).toDouble())
            val x = centroX + (radioTexto * cos(anguloMedioRad)).toFloat()
            val y = centroY + (radioTexto * sin(anguloMedioRad)).toFloat() + 8f
            canvas.drawText("${porcentajes[i].toInt()}%", x, y, paintTexto)

            anguloInicio += barrido
        }
    }
}