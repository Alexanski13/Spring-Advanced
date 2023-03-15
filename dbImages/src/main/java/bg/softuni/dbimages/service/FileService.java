package bg.softuni.dbimages.service;

import bg.softuni.dbimages.model.FileDownloadModel;
import bg.softuni.dbimages.model.FileEntity;
import bg.softuni.dbimages.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public long saveFile(MultipartFile file) throws IOException {
        FileEntity fileEntity = new FileEntity()
                .setFileName(file.getOriginalFilename())
                .setContentType(file.getContentType())
                .setData(file.getBytes());

        return fileRepository.save(fileEntity).getId();
    }

    public Optional<FileDownloadModel> getFileById(long fileId) {
        var file = fileRepository.findById(fileId).orElseThrow();

        return Optional.of(new FileDownloadModel(
                file.getData(),
                file.getContentType(),
                file.getFileName()
        ));
    }
}
