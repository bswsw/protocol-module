package com.baegoon.protocol.validation.validator

class EmailValidator : Validator {

    companion object {
        private const val EMAIL_REGEX =
            "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$"
    }

    override fun <T> validate(fieldValue: T, rule: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(fieldValue.toString())
    }
}
