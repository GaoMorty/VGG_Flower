package edu.cczu.service;

import edu.cczu.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 大只
 * @date 2022-11-09
 */
public interface UploadService {
    Result uploadAiImg(MultipartFile file);
}
