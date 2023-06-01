package com.cyj.service.ipfs;

import org.springframework.web.multipart.MultipartFile;

public interface IPFSService {
    public String saveFile(MultipartFile file);
    public String saveFile2(String content);
    public byte[] loadFile(String hash);

}
