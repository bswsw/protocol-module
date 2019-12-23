package com.baegoon.protocol.validation.validator

class RegexValidator : Validator {

    override fun <T> validate(fieldValue: T, rule: String): Boolean {
        val regex = rule.toRegex()
        return regex.matches(fieldValue.toString())
    }
}
