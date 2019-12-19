package com.baegoon.protocol.exception

class ProtobufValidationException(fieldName: String, fieldValue: Any, message: String) :
    RuntimeException("$message ($fieldName : $fieldValue)")
