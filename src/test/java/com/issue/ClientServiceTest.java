package com.issue;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class ClientServiceTest {
    @Inject
    private ClientService clientService;

    @Test
    public void verifyCleintOk() {
        final String reply = clientService.callStub();
        assertNotNull(reply);
    }
}
