package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class RegexValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.regex.descriptor)

    @Test
    @DisplayName("정규식 검증 테스트 성공")
    fun testValidateRegex() {
        val value = "b"
        val regex = "^[a-c]\$"

        Assertions.assertTrue(validator!!.validate(value, regex))
    }
}
