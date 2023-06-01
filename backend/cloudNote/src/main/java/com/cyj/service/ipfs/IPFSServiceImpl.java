package com.cyj.service.ipfs;

import com.cyj.config.IPFSConfig;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
@Service
public class IPFSServiceImpl implements IPFSService{

    @Autowired
    private IPFSConfig ipfsConfig;


    @Override
    public String saveFile(MultipartFile file) {
       try{
           InputStream stream=new ByteArrayInputStream(file.getBytes());
           NamedStreamable.InputStreamWrapper inputStreamWrapper = new NamedStreamable.InputStreamWrapper(stream);
           IPFS ipfs=ipfsConfig.ipfs;
           MerkleNode merkleNode = ipfs.add(inputStreamWrapper).get(0);
           return merkleNode.hash.toBase58();
       }catch (Exception e){
            throw  new RuntimeException("连接节点失败",e);
       }
    }

    @Override
    public String saveFile2(String content) {
        try{
            InputStream stream=new ByteArrayInputStream(content.getBytes());
            NamedStreamable.InputStreamWrapper inputStreamWrapper = new NamedStreamable.InputStreamWrapper(stream);
            IPFS ipfs=ipfsConfig.ipfs;
            MerkleNode merkleNode = ipfs.add(inputStreamWrapper).get(0);
            return merkleNode.hash.toBase58();
        }catch (Exception e){
            throw  new RuntimeException("连接节点失败",e);
        }
    }

    @Override
    public byte[] loadFile(String hash) {
        try{
            IPFS ipfs=ipfsConfig.ipfs;
            Multihash filePointer =Multihash.fromBase58(hash);
            byte[] fileContents=ipfs.cat(filePointer);
            return fileContents;
        }catch (Exception e){
            throw  new RuntimeException("连接节点失败",e);
        }
    }

}
