package com.baegoon.protocol.validation.validator

class PhoneValidator : Validator {

    companion object {
        private const val PHONE_REGEX = "^01(([1-9]\\d{7})|[0-9]\\d{8})\$"
    }

    override fun <T> validate(fieldValue: T, rule: String): Boolean {
        return PHONE_REGEX.toRegex().matches(fieldValue.toString())
    }
}
