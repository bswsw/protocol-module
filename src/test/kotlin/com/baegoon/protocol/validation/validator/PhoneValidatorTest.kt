package com.baegoon.protocol.validation.validator

import com.baegoon.protocol.proto.Rule
import com.baegoon.protocol.validation.ValidationContext
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PhoneValidatorTest {

    private val validator = ValidationContext.getValidator(Rule.phone.descriptor)

    @Test
    @DisplayName("전화번호 검증 테스트 성공")
    fun testValidatePhone() {
        val value = "01012345678"

        Assertions.assertTrue(validator!!.validate(value, ""))
    }
}
