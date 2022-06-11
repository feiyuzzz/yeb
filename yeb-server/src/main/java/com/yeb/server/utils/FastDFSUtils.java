package com.yeb.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
@Slf4j
public class FastDFSUtils {


    /*
    客户端初始化
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取文件地址
     * @return
     */
    public static String getFileUrl(){
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storeStorage = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
             storeStorage = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            log.error("文件路径获取失败:{}",e.getMessage());
        }
        return "http://"+storeStorage.getInetSocketAddress().getHostString()+":8888/";
    }

    /**
     * 文件删除
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static Integer deleteFile(String groupName,String remoteFileName){
        StorageClient storageClient = null;
        Integer result = null;
        try {
            storageClient=getStorageClient();
            result = storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            log.error("文件删除失败：{}",e.getMessage());
        }
        return result;
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        InputStream inputStream = null;
        try {
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            inputStream = new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            log.error("文件下载失败：{}", e.getMessage());
        }
        return inputStream;
    }

    /**
     * 获取文件信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        FileInfo fileInfo = null;
        try {
            storageClient = getStorageClient();
            fileInfo = storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            log.error("获取信息文件失败！:{}", e.getMessage());
        }
        return fileInfo;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public static String[] uploadFile(MultipartFile file) {
        String name = file.getOriginalFilename();
        log.info("文件名：{}", name);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try {
            storageClient = getStorageClient();
            storageClient.upload_file(file.getBytes(), name.substring(name.lastIndexOf(".") + 1), null);
        } catch (Exception e) {
            log.error("上传文件失败！:{}", e.getMessage());
        }
        if (null == uploadResults) {
            log.error("上传失败！ERROR_CODE:{}", storageClient.getErrorCode());
        }
        return uploadResults;
    }


    /**
     * 生成storage客户端
     *
     * @return
     * @throws IOException
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 生成tracker服务器
     *
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }
}
