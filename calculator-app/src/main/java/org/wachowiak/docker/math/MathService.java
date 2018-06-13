package org.wachowiak.docker.math;

import org.springframework.stereotype.Service;

@Service
class MathService {

    long add(long a, long b) {
        return a + b;
    }

    long sub(long a, long b) {
        return a - b;
    }

    long mul(long a, long b) {
        return a * b;
    }

    long div(long a, long b) {
        assert b != 0;
        return a / b;
    }
}
