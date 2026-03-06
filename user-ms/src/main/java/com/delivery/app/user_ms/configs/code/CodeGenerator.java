package com.delivery.app.user_ms.configs.code;

import java.security.SecureRandom;

public class CodeGenerator {

    private final SecureRandom secureRandom;

    public CodeGenerator(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }

    public int generate6DigitCode() {
        return 100000 + secureRandom.nextInt(900000);
    }
}
