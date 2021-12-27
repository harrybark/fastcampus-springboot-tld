package com.example.tldspringboot.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class Encoder implements IEncoder {

    private IEncoder iEncoder;

    /*
    public Encoder(@Qualifier("baseEncoder") IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }
    */

    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    @Override
    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
