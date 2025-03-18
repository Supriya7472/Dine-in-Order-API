package com.example.dio.service.impl;

import com.example.dio.service.QRCodeService;
import com.example.dio.util.QRCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {
    @Override
    public byte[] generateQRCode(String url) throws Exception {
        return QRCodeGenerator.generateQRCodeImage(url);
    }
}
