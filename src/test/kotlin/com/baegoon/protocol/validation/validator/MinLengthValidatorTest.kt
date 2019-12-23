package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MinLengthValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.minLength.descriptor)

    @Test
    @DisplayName("문자열 최소길이 테스트 성공")
    fun testValidateMinLength() {
        val value = "안녕"
        val minLength = value.length - 1

        Assertions.assertTrue(validator!!.validate(value, minLength.toString()))
    }
}
