package com.baegoon.protocol.validation.validator

class MinValidator : Validator {

    override fun <T> validate(fieldValue: T, rule: String): Boolean {
        return when (fieldValue) {
            is Long -> fieldValue >= rule.toLong()
            is Int -> fieldValue >= rule.toInt()
            is Double -> fieldValue >= rule.toDouble()
            is Float -> fieldValue >= rule.toFloat()
            else -> false
        }
    }
}
