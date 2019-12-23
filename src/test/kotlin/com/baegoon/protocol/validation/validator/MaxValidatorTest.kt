package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MaxValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.max.descriptor)

    @Test
    @DisplayName("최대값 검증 테스트 성공")
    fun testValidateMax() {
        val value = 10
        val max = value + 1

        Assertions.assertTrue(validator!!.validate(value, max.toString()))
    }
}
