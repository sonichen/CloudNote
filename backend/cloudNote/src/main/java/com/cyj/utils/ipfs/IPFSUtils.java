package com.cyj.utils.ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multiaddr.MultiAddress;
import io.ipfs.multihash.Multihash;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class IPFSUtils {
    /**
     * ipfs的服务器地址和端口,替换成自己的ip，port
     * /ip4/0.0.0.0/tcp/8081
     */
    private static IPFS IPFS = new IPFS("/ip4/127.0.0.1/tcp/5001");

    public static String upload(String fileName) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(fileName));
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String upload(byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(data);
        MerkleNode addResult = IPFS.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String download(String hash) {
        byte[] data = null;
        String result=null;
        try {
            data = IPFS.cat(Multihash.fromBase58(hash));
            result=new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
//    public static byte[] download(String hash) {
//        byte[] data = null;
//        try {
//            data = IPFS.cat(Multihash.fromBase58(hash));
//            String result=new String(data);
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
    public static void download(String hash, String destFile) {
        byte[] data = null;
        try {
            data = IPFS.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null && data.length > 0) {
            File file = new File(destFile);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    /**
     * 添加新节点
     */

    public void addPeers()throws  IOException{
        MultiAddress multiAddress = new MultiAddress("/ip4/192.168.91.140/tcp/4001/ipfs/QmQJPAqVaURJbSnFLaiA65Zm6fYePFTR7AkWKT2LANp7X5");
        List<MultiAddress> peers =IPFS.bootstrap.add(multiAddress);
        System.out.println(peers.get(0).toString());
    }

    /**
     * 删除节点
     * @throws IOException
     */

    public void rmPeers()throws IOException{
        MultiAddress multiAddress = new MultiAddress("/ip4/192.168.91.140/tcp/4001/ipfs/QmQJPAqVaURJbSnFLaiA65Zm6fYePFTR7AkWKT2LANp7X5");
        List<MultiAddress> rm = IPFS.bootstrap.rm(multiAddress);
        System.out.println(rm.get(0).toString());
    }

}
