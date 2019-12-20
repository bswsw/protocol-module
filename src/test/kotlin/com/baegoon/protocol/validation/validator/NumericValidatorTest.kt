package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class NumericValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.numeric.descriptor)

    @Test
    @DisplayName("숫자 검증 성공")
    fun testValidateNumeric() {
        val fieldName = "나이"
        val fieldValue = 25

        Assertions.assertTrue(validator!!.validate(fieldName, fieldValue, null))
    }
}
