package com.baegoon.protocol.validation.validator

class MaxLengthValidator : Validator {

    override fun <T> validate(fieldValue: T, rule: String): Boolean {
        return fieldValue.toString().length <= rule.toInt()
    }
}
