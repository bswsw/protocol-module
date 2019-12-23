package com.baegoon.protocol.validation.validator

import java.lang.Double.parseDouble

class NumericValidator : Validator {

    override fun <T> validate(fieldValue: T, rule: String): Boolean {
        return try {
            parseDouble(fieldValue.toString())
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}
