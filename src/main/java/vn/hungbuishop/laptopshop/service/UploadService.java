package vn.hungbuishop.laptopshop.service;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.hungbuishop.laptopshop.repository.RoleRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    public String handleUPloadFile(MultipartFile file, String targetFolder) throws IOException {
        String finalName = "";
        byte[] bytes = file.getBytes();
        String rootPath = this.servletContext.getRealPath("/resources/images");
        File dir = new File(rootPath + File.separator + targetFolder);
        if (!dir.exists())
            dir.mkdirs();
        // Create the file on server
        finalName =
                System.currentTimeMillis() + "-" + file.getOriginalFilename();
        File serverFile = new File(dir.getAbsolutePath() + File.separator +finalName);
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return serverFile.getAbsolutePath();
    }
}
