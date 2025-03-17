package com.big.river.learn

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PropertyDelegate : ReadWriteProperty<Any?, String> {

    private var value: String = ""

    override fun getValue(thisRef: Any ? , property: KProperty<*>): String {
        val name = property.name
        println("PropertyDelegate.getValue: $name")
        return value
    }

    override fun setValue(thisRef: Any ? , property: KProperty<*>, value: String) {
        val name = property.name
        println("PropertyDelegate.setValue: $name")
        this.value = value
    }

}