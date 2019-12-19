package com.baegoon.protocol.interceptor

import com.baegoon.protocol.exception.ProtobufValidationException
import com.baegoon.protocol.validation.ValidationContext
import com.google.protobuf.Descriptors
import com.google.protobuf.GeneratedMessageV3
import io.grpc.ForwardingServerCallListener
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.grpc.Status

class ProtobufValidationServerInterceptor : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        val listener = next.startCall(call, headers)

        return object : ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            override fun onMessage(message: ReqT) {
                message as GeneratedMessageV3

                try {
                    validateMessage(message)
                    super.onMessage(message)
                } catch (e: ProtobufValidationException) {
                    call.close(Status.INVALID_ARGUMENT.withDescription(e.message), Metadata())
                } catch (e: Exception) {
                    call.close(Status.INTERNAL.withDescription(e.message), Metadata())
                }
            }
        }
    }

    @Throws(ProtobufValidationException::class)
    private fun validateMessage(message: GeneratedMessageV3) {
        message.descriptorForType.fields.forEach { fieldDescriptor ->
            val fieldValue = message.getField(fieldDescriptor)

            validateField(fieldDescriptor.name, fieldValue, fieldDescriptor.options.allFields)

            if (fieldValue is GeneratedMessageV3) {
                this.validateMessage(fieldValue)
                return@forEach
            }
        }
    }

    private fun validateField(fieldName: String, fieldValue: Any, fieldRules: Map<Descriptors.FieldDescriptor, Any>) {
        fieldRules.entries.forEach {
            val isValidated = ValidationContext.getValidator(it.key)?.validate(fieldName, fieldValue, it) ?: true

            if (!isValidated) {
                throw ProtobufValidationException(
                    fieldName = fieldName,
                    fieldValue = fieldValue,
                    message = ValidationContext.getErrorMessage(it) ?: "유효하지 않은 값입니다."
                )
            }
        }
    }
}
