package com.assesmenttest.v2survey.utils

fun getOptionsArray(options: String): MutableList<String> {
    var i = 0
    var start = 0
    var end = start
    val data = mutableListOf<String>()
    while (i != options.length) {
        if(options[i] == ',') {
            end = i
            data.add(options.substring(start, end))
            start = i+1
        }
        i++
    }
    end = i
    data.add(options.substring(start, end).trim())
    return data
}