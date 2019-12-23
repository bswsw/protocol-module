package com.baegoon.protocol.validation.validator

interface Validator {
    fun <T> validate(fieldValue: T, rule: String): Boolean
}
