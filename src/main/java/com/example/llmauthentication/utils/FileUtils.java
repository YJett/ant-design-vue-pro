package com.example.llmauthentication.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    public static String saveFileToLocal(MultipartFile file) {
        // 使用相对路径指定本地文件存储的位置
        String localPath = "C:\\AppData\\uploads\\";

        // 获取文件的原始名称
        String originalFilename = file.getOriginalFilename();

        // 获取文件的扩展名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 生成一个 UUID 作为新的文件名
        String newFilename = UUID.randomUUID().toString() + extension;

        // 创建一个新的文件实例
        File dest = new File(localPath + newFilename);

        // 检查目标文件夹是否存在，如果不存在，则创建它
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        // 将 MultipartFile 的文件数据写入到新的文件中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回文件的路径
        return localPath + newFilename;
    }

}
