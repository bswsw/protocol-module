package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MinValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.min.descriptor)

    @Test
    @DisplayName("최소값 검증 테스트 성공")
    fun testValidateMin() {
        val value = 10
        val min = 10 - 1

        Assertions.assertTrue(validator!!.validate(value, min.toString()))
    }
}
