package com.example.endmillcalculator.utils

fun eccentricContactCalc(landWidth:Double, helixAngel:Double):Double{
    val contactKof =  1 / Math.tan(Math.toRadians(helixAngel))
    return  (contactKof * landWidth).limitAfterPoint(3)
}

fun Double.limitAfterPoint(x:Int):Double{
    val limit:Double = x.toDouble()
    val divader:Double = Math.pow(10.0, limit)
    return Math.floor(this * divader) / divader
}

fun HelixConvertToLead(toolDiameter:Double, helixAngel: Double):Double{
    val dp = toolDiameter * Math.PI
    val tgHel = Math.tan(Math.toRadians(helixAngel))
    return dp/tgHel
}

fun LeadConvertToHelix(toolDiameter: Double, lead:Double):Double{
    val dp = toolDiameter * Math.PI
    val hel = Math.atan(dp / lead)
    return Math.toDegrees(hel)
}

fun backTaperCalculationDiameter(toolOd:Double, lowering:Double, length:Double):Double{
    val lowPerMm = lowering / 100.0
    val lowPerLen = lowPerMm * length
    return toolOd - lowPerLen
}

fun backTaperCalculationAngel(toolOd:Double, lowering:Double, length:Double):Double{
    val lowD = lowering
    val angel = Math.atan(lowD / 100.0)
    return Math.toDegrees(angel)
}