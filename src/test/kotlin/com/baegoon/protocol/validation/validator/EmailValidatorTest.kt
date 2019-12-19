package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class EmailValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.email.descriptor)

    @Test
    @DisplayName("이메일 검증 성공 테스트")
    fun testValidateEmail() {
        val fieldName = "email"
        val fieldValue = "baegoony@gmail.com"

        Assertions.assertTrue(validator!!.validate(fieldName, fieldValue, null))
    }
}
