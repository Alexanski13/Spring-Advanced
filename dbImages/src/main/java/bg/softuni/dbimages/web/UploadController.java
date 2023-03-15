package bg.softuni.dbimages.web;

import bg.softuni.dbimages.model.FileUploadModel;
import bg.softuni.dbimages.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class UploadController {

    private final FileService fileService;

    public UploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(FileUploadModel uploadModel) throws IOException {
        return "redirect:/show/" + fileService.saveFile(uploadModel.getImg());
    }
}
