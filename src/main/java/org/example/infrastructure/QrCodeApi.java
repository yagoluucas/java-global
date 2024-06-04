package org.example.infrastructure;

public class QrCodeApi {
    public String createQrCodeUrl(String data) {
        return "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + data;
    }
}
