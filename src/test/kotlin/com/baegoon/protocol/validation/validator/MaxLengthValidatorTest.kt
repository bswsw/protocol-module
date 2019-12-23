package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MaxLengthValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.maxLength.descriptor)

    @Test
    @DisplayName("문자열 최대길이 검증 성공 테스트")
    fun testValidateMaxLength() {
        val value = "안녕하세요."
        val maxLength = value.length + 1

        Assertions.assertTrue(validator!!.validate(value, maxLength.toString()))
    }
}
