package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

class RegexValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>?
    ): Boolean {
        return rule?.let {
            val regex = it.value.toString().toRegex()
            regex.matches(fieldValue.toString())
        } ?: false
    }
}
