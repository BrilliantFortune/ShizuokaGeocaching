package com.alone.navigationview

/**
 * Created by cs16083 on 2017/11/29.
 */

class Mapcolor(cate:String) {
    private val category = cate
    public var sc = 0
    public var fc = 0

    init{
        when(category){
            "spot" -> {
                sc = -16728065
                fc = -1291780097
            }
            "restaurant" -> {
                sc = -60269
                fc = -1275106892
            }
            else ->{}
        }
    }
}