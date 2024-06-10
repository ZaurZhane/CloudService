package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.entity.FileEntity;
import ru.netology.repository.FileRepository;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    @Mock
    private FileRepository fileRepository;

    @Mock
    private InMemoryBlackListToken blackListToken;

    @InjectMocks
    private FileService fileService;

    @Test
    public void testUploadFile() throws IOException {

        String token = "validToken";
        String filename = "example.txt";
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        when(blackListToken.isBlacklisted(token)).thenReturn(false);

        fileService.uploadFile(token, filename, file);

        verify(fileRepository).save(any(FileEntity.class));
    }

    @Test
    public void testDeleteFile() {

        String token = "validToken";

        String filename = "example.txt";

        when(blackListToken.isBlacklisted(token)).thenReturn(false);

        fileService.deleteFile(token, filename);

        verify(fileRepository).deleteById(filename);

    }


}
