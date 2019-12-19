package com.baegoon.protocol.validation.validator

import com.google.protobuf.Descriptors

class MinLengthValidator : Validator {

    override fun <T> validate(
        fieldName: String,
        fieldValue: T,
        rule: Map.Entry<Descriptors.FieldDescriptor, Any>?
    ): Boolean {
        return rule?.let {
            fieldValue.toString().length >= it.value.toString().toInt()
        } ?: false
    }
}
