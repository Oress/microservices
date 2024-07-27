package org.ipan.users.domain.exception;

import org.ipan.common.domain.exception.DomainException;

public class CustomerDomainException extends DomainException {
    public CustomerDomainException(String message) {
        super(message);
    }
}
