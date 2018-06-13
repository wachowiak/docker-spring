package org.wachowiak.docker;

import org.springframework.stereotype.Service;

@Service
class MathService {

    long add(long a, long b) {
        return a + b;
    }
}
